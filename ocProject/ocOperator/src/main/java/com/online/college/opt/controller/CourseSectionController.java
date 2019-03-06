package com.online.college.opt.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.online.college.common.web.JsonView;
import com.online.college.core.course.domain.CourseSection;
import com.online.college.core.course.service.ICourseSectionService;
import com.online.college.opt.business.ICourseSectionBusiness;
import com.online.college.opt.vo.CourseSectionVO;


@Controller
@RequestMapping("/courseSection")
public class CourseSectionController{

	@Autowired
	private ICourseSectionService entityService;
	
	@Resource
	private ICourseSectionBusiness courseSectionBusiness;
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public String getById(Long id){
		return JsonView.render(entityService.getById(id));
	}

	@RequestMapping(value = "/doMerge")
	@ResponseBody
	public String doMerge(CourseSection entity){
		entityService.updateSelectivity(entity);
		return new JsonView().toString();
	}

	//交换排序位置
	@RequestMapping(value = "/sortSection")
	@ResponseBody
	public String sortSection(CourseSection entity, Integer sortType){
		CourseSection curCourseSection = entityService.getById(entity.getId());
		if(null != curCourseSection){
			CourseSection tmpCourseSection = null;
			if(Integer.valueOf(1).equals(sortType)){//降序
				//比当前sort大的，正序排序的第一个
				tmpCourseSection = entityService.getSortSectionMax(curCourseSection);
			}else{
				//比当前sort小的，倒序排序的第一个
				tmpCourseSection = entityService.getSortSectionMin(curCourseSection);
			}
			
			if(null != tmpCourseSection){
				Integer tmpSort = curCourseSection.getSort();
				curCourseSection.setSort(tmpCourseSection.getSort());
				entityService.updateSelectivity(curCourseSection);
				
				tmpCourseSection.setSort(tmpSort);
				entityService.updateSelectivity(tmpCourseSection);
			}
			
		}
		return new JsonView().toString();
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(CourseSection entity){
		entityService.delete(entity);
		return new JsonView().toString();
	}

	@RequestMapping(value = "/deleteLogic")
	@ResponseBody
	public String deleteLogic(CourseSection entity){
		entityService.deleteLogic(entity);
		return new JsonView().toString();
	}
	
	//批量添加章节
	@RequestMapping(value = "/batchAdd")
	@ResponseBody
	public String batchAdd(@RequestBody List<CourseSectionVO> batchSections){
		courseSectionBusiness.batchAdd(batchSections);
		return new JsonView().toString();
	}
	
	/**
	 * 导入excel
	 */
	@RequestMapping("/doImport")
	@ResponseBody
	public String doImport(Long courseId ,@RequestParam(value="courseSectionExcel",required=true)MultipartFile excelFile){
		try {
			if (null != excelFile && excelFile.getBytes().length > 0) {
				InputStream is = excelFile.getInputStream();
				courseSectionBusiness.batchImport(courseId, is);//批量导入
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new JsonView().toString();
	}
	
}

