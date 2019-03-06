package com.online.college.portal.vo;

import java.util.ArrayList;
import java.util.List;

import com.online.college.core.consts.domain.ConstsClassify;
import com.online.college.core.course.domain.Course;

/**
 * 页面展示 value object
 */
public class ConstsClassifyVO extends ConstsClassify {
	
	private static final long serialVersionUID = -6898939223836635781L;

	//子分类列表
	private List<ConstsClassify> subClassifyList = new ArrayList<ConstsClassify>();

	//课程推荐列表
	private List<Course> recomdCourseList ;
	
	public List<ConstsClassify> getSubClassifyList() {
		return subClassifyList;
	}
	
	public void setSubClassifyList(List<ConstsClassify> subClassifyList) {
		this.subClassifyList = subClassifyList;
	}

	public List<Course> getRecomdCourseList() {
		return recomdCourseList;
	}

	public void setRecomdCourseList(List<Course> recomdCourseList) {
		this.recomdCourseList = recomdCourseList;
	}
	
}
