package com.online.college.wechat.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.core.consts.CourseEnum;
import com.online.college.core.course.domain.CourseSection;
import com.online.college.core.course.service.ICourseSectionService;
import com.online.college.wechat.business.IPortalBusiness;
import com.online.college.wechat.vo.CourseSectionVO;

/**
 * 业务层
 */
@Service
public class PortalBusinessImpl implements IPortalBusiness {
	
	@Autowired
	private ICourseSectionService courseSectionService;
	
	/**
	 * 获取课程章节
	 */
	@Override
	public List<CourseSectionVO> queryCourseSection(Long courseId){
		List<CourseSectionVO> resultList = new ArrayList<CourseSectionVO>();
		CourseSection queryEntity = new CourseSection();
		queryEntity.setCourseId(courseId);
		queryEntity.setOnsale(CourseEnum.ONSALE.value());//上架
		
		Map<Long,CourseSectionVO> tmpMap = new LinkedHashMap<Long,CourseSectionVO>();
		Iterator<CourseSection> it = courseSectionService.queryAll(queryEntity).iterator();
		while(it.hasNext()){
			CourseSection item = it.next();
			if(Long.valueOf(0).equals(item.getParentId())){//章
				CourseSectionVO vo = new CourseSectionVO();
				BeanUtils.copyProperties(item, vo);
				tmpMap.put(vo.getId(), vo);
			}else{
				tmpMap.get(item.getParentId()).getSections().add(item);//小节添加到大章中
			}
		}
		for(CourseSectionVO vo : tmpMap.values()){
			resultList.add(vo);
		}
		return resultList;
	}
	
}
