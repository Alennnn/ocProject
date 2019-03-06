package com.online.college.core.user.domain;

import com.online.college.common.orm.BaseEntity;


public class UserMessage extends BaseEntity{

	private static final long serialVersionUID = -5308032957532522065L;
	
	/**
	*消息接收用户id
	**/
	private Long userId;

	/**
	*消息发起用户id
	**/
	private Long sendUserId;

	/**
	*消息发起用户名称
	**/
	private String sendUserName;

	/**
	*引用id
	**/
	private String refId;

	/**
	*引用内容
	**/
	private String refContent;

	/**
	*通知的类型，1-评论，2-关注，3-答疑
	**/
	private Integer type;

	/**
	*未读（0）、已读（1）
	**/
	private Integer status;

	public Long getUserId(){
		return userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getSendUserId(){
		return sendUserId;
	}
	public void setSendUserId(Long sendUserId){
		this.sendUserId = sendUserId;
	}

	public String getSendUserName(){
		return sendUserName;
	}
	public void setSendUserName(String sendUserName){
		this.sendUserName = sendUserName;
	}

	public String getRefId(){
		return refId;
	}
	public void setRefId(String refId){
		this.refId = refId;
	}

	public String getRefContent(){
		return refContent;
	}
	public void setRefContent(String refContent){
		this.refContent = refContent;
	}

	public Integer getType(){
		return type;
	}
	public void setType(Integer type){
		this.type = type;
	}

	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}



}

