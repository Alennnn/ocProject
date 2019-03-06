package com.online.college.core.course.service;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.domain.CourseQueryDto;

/**
 * 课程服务层
 */
public interface ICourseService {

	/**
	*根据id获取
	**/
	public Course getById(Long id);

	/**
	*获取所有
	**/
	public List<Course> queryList(CourseQueryDto queryEntity);

	/**
	*分页获取
	**/
	public TailPage<Course> queryPage(Course queryEntity ,TailPage<Course> page);

	/**
	*创建
	**/
	public void createSelectivity(Course entity);

	/**
	*根据id 进行可选性更新
	**/
	public void updateSelectivity(Course entity);

	/**
	*物理删除
	**/
	public void delete(Course entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(Course entity);
	
}

