package com.online.college.core.course.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.course.domain.CourseComment;


public interface CourseCommentDao {

	/**
	*根据id获取
	**/
	public CourseComment getById(Long id);

	/**
	*获取所有
	**/
	public List<CourseComment> queryAll(CourseComment queryEntity);

	/**
	*获取总数量
	**/
	public Integer getTotalItemsCount(CourseComment queryEntity);

	/**
	*分页获取
	**/
	public List<CourseComment> queryPage(CourseComment queryEntity , TailPage<CourseComment> page);
	
	
	/**
	*获取总数量
	**/
	public Integer getMyQAItemsCount(CourseComment queryEntity);

	/**
	*分页获取
	**/
	public List<CourseComment> queryMyQAItemsPage(CourseComment queryEntity , TailPage<CourseComment> page);
	
	/**
	*创建新记录
	**/
	public void create(CourseComment entity);
	
	/**
	 * 创建新记录
	 */
	public void createSelectivity(CourseComment entity);

	/**
	*根据id更新
	**/
	public void update(CourseComment entity);

	/**
	*根据id选择性更新自动
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

