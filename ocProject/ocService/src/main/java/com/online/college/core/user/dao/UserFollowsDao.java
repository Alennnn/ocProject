package com.online.college.core.user.dao;

import java.util.List;

import com.online.college.common.page.TailPage;
import com.online.college.core.user.domain.UserFollowStudyRecord;
import com.online.college.core.user.domain.UserFollows;


public interface UserFollowsDao {

	/**
	*根据id获取
	**/
	public UserFollows getById(Long id);

	/**
	*获取所有
	**/
	public List<UserFollows> queryAll(UserFollows queryEntity);

	/**
	*获取总数量
	**/
	public Integer getTotalItemsCount(UserFollows queryEntity);

	/**
	*分页获取
	**/
	public List<UserFollows> queryPage(UserFollows queryEntity , TailPage<UserFollows> page);

	/**
	*获取总数量
	**/
	public Integer getFollowStudyRecordCount(UserFollowStudyRecord queryEntity);

	/**
	*分页获取
	**/
	public List<UserFollowStudyRecord> queryFollowStudyRecord(UserFollowStudyRecord queryEntity , TailPage<UserFollowStudyRecord> page);
	
	/**
	*创建新记录
	**/
	public void createSelectivity(UserFollows entity);

	/**
	*根据id更新
	**/
	public void update(UserFollows entity);

	/**
	*根据id选择性更新自动
	**/
	public void updateSelectivity(UserFollows entity);

	/**
	*物理删除
	**/
	public void delete(UserFollows entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(UserFollows entity);



}

