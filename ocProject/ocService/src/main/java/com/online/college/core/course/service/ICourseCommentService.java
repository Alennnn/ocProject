package com.online.college.core.course.service;

import java.util.List;
import com.online.college.common.page.TailPage;
import com.online.college.core.course.domain.CourseComment;


public interface ICourseCommentService {

	/**
	*根据id获取
	**/
	public CourseComment getById(Long id);

	/**
	*获取所有
	**/
	public List<CourseComment> queryAll(CourseComment queryEntity);

	/**
	*分页获取
	**/
	public TailPage<CourseComment> queryPage(CourseComment queryEntity ,TailPage<CourseComment> page);

	/**
	 * 分页获取我的所有课程的qa
	 */
	public TailPage<CourseComment> queryMyQAItemsPage(CourseComment queryEntity ,TailPage<CourseComment> page);
	
	/**
	*创建
	**/
	public void create(CourseComment entity);
	
	/**
	 * 创建
	 */
	public void createSelectivity(CourseComment entity);

	/**
	*根据id更新
	**/
	public void update(CourseComment entity);

	/**
	*根据id 进行可选性更新
	**/
	public void updateSelectivity(CourseComment entity);

	/**
	*物理删除
	**/
	public void delete(CourseComment entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(CourseComment entity);



}

