package com.online.college.opt.business.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.web.SessionContext;
import com.online.college.core.course.domain.CourseSection;
import com.online.college.core.course.service.ICourseSectionService;
import com.online.college.opt.business.ICourseSectionBusiness;
import com.online.college.opt.vo.CourseSectionVO;

@Service
public class CourseSectionBusinessImpl implements ICourseSectionBusiness {

	@Autowired
	private ICourseSectionService entityService;
	
	/**
	 * 循环添加和批量添加
	 */
	@Override
	public void batchAdd(List<CourseSectionVO> courseSections){
		if(CollectionUtils.isNotEmpty(courseSections)){
			//先获取最大的排序id
			Integer maxSort = entityService.getMaxSort(courseSections.get(0).getCourseId());
			
			for(int i = 0; i < courseSections.size(); i++){
				CourseSectionVO tmpVO = courseSections.get(i);
				if(null == maxSort){
					maxSort = 0;
				}
				maxSort += (i + 1);
				CourseSection courseSection = new CourseSection();
				courseSection.setCourseId(tmpVO.getCourseId());
				courseSection.setName(tmpVO.getName());
				courseSection.setParentId(0L);//章的parentId固定为0
				courseSection.setSort(maxSort);
				courseSection.setOnsale(1);
				courseSection.setCreateTime(new Date());
				courseSection.setUpdateTime(new Date());
				courseSection.setCreateUser(SessionContext.getUsername());
				courseSection.setUpdateUser(SessionContext.getUsername());
				//创建章
				entityService.createSelectivity(courseSection);
				
				List<CourseSection> subCourseSections = tmpVO.getSections();
				if(CollectionUtils.isNotEmpty(subCourseSections)){
					String totalTime = "00:00";
					for(int j = 0; j < subCourseSections.size(); j++){
						CourseSection courseSectionTmp = subCourseSections.get(j);
						courseSectionTmp.setCourseId(courseSection.getCourseId());
						courseSectionTmp.setParentId(courseSection.getId());
						courseSectionTmp.setSort(j+1);
						
						courseSectionTmp.setCreateTime(new Date());
						courseSectionTmp.setCreateUser(SessionContext.getUsername());
						courseSectionTmp.setUpdateTime(new Date());
						courseSectionTmp.setUpdateUser(SessionContext.getUsername());
						courseSectionTmp.setOnsale(1);
						
						Pattern p = Pattern.compile("^([0-5][0-9]):([0-5][0-9])$");
						if(!p.matcher(courseSectionTmp.getTime()).find()){//正则表达式匹配不成功
							courseSectionTmp.setTime("00:00");
						}
						if(null == courseSectionTmp.getVideoUrl()){
							courseSectionTmp.setVideoUrl("");
						}
						//计算节的总时间
						totalTime = appendCourseSectionTime(totalTime,courseSectionTmp.getTime());
					}
					//批量创建 节
					entityService.createList(subCourseSections);
					
					//更新时间
					courseSection.setTime(totalTime);
					entityService.updateSelectivity(courseSection);
				}
				
			}
		}
	}
	
	//时间合计
	private String appendCourseSectionTime(String time1, String time2){
		String[] time1Arr = time1.split(":");
		String[] time2Arr = time2.split(":");
		Integer second1 = Integer.parseInt(time1Arr[0]) * 60 + Integer.parseInt(time1Arr[1]);
		Integer second2 = Integer.parseInt(time2Arr[0]) * 60 + Integer.parseInt(time2Arr[1]);
		Integer secondTotal = second1 + second2;
		Integer minute = secondTotal/60;
		String minuteStr = minute + "";
		if(minute < 10){
			minuteStr = "0"+minute;
		}
		Integer secode = secondTotal%60;
		String secodeStr = secode + "";
		if(secode < 10){
			secodeStr = "0"+secode;
		}
		return minuteStr + ":" + secodeStr;
	}
	
	/**
	 * 批量导入
	 */
	@Override
	public void batchImport(Long courseId, InputStream is){
		try {
			Workbook wb = WorkbookFactory.create(is);
			// 得到总行数
			Sheet sheet = wb.getSheetAt(0);
			sheet.removeRow(sheet.getRow(0));//第一行（title移除掉）
			
			List<CourseSectionVO> courseSections = new ArrayList<CourseSectionVO>();
			//遍历行
			for (Row row : sheet) {
				Cell cell0 = row.getCell(0, Row.CREATE_NULL_AS_BLANK);//章标题
				Cell cell1 = row.getCell(1, Row.CREATE_NULL_AS_BLANK);//节标题
				Cell cell2 = row.getCell(2, Row.CREATE_NULL_AS_BLANK);//节视频url
				Cell cell3 = row.getCell(3, Row.CREATE_NULL_AS_BLANK);//节时长
				
				if(cell0.getCellType() == Cell.CELL_TYPE_STRING){//如果有数据，新建一章
					
					if("end".equals(cell0.getStringCellValue().trim())){//结束了，跳出循环
						break;
					}
					//章
					CourseSectionVO vo = new CourseSectionVO();
					vo.setCourseId(courseId);
					vo.setName(cell0.getStringCellValue().trim());
					
					//节
					CourseSection cs = new CourseSection();
					cs.setCourseId(courseId);
					cs.setName(cell1.getStringCellValue().trim());
					cs.setVideoUrl(cell2.getStringCellValue().trim());
					cs.setTime(cell3.getStringCellValue().trim());
					
					vo.getSections().add(cs);
					courseSections.add(vo);
					
				}else if(cell0.getCellType() == Cell.CELL_TYPE_BLANK){//空的，继续添加节
					if(courseSections.size() > 0){
						CourseSectionVO lastVo = courseSections.get(courseSections.size()-1);
						
						//节
						CourseSection cs = new CourseSection();
						cs.setCourseId(courseId);
						cs.setName(cell1.getStringCellValue().trim());
						cs.setVideoUrl(cell2.getStringCellValue().trim());
						cs.setTime(cell3.getStringCellValue().trim());
						
						lastVo.getSections().add(cs);
					}
				}
			}
			
			//批量插入
			if(courseSections.size() > 0){
				this.batchAdd(courseSections);
			}
			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

