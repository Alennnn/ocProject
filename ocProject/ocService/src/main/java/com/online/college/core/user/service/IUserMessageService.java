package com.online.college.core.user.service;

import java.util.List;
import com.online.college.common.page.TailPage;
import com.online.college.core.user.domain.UserMessage;


public interface IUserMessageService {

	/**
	*根据id获取
	**/
	public UserMessage getById(Long id);

	/**
	*获取所有
	**/
	public List<UserMessage> queryAll(UserMessage queryEntity);

	/**
	*分页获取
	**/
	public TailPage<UserMessage> queryPage(UserMessage queryEntity ,TailPage<UserMessage> page);

	/**
	*创建
	**/
	public void create(UserMessage entity);

	/**
	*根据id更新
	**/
	public void update(UserMessage entity);

	/**
	*根据id 进行可选性更新
	**/
	public void updateSelectivity(UserMessage entity);

	/**
	*物理删除
	**/
	public void delete(UserMessage entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(UserMessage entity);



}

