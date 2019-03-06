package com.online.college.core.consts.domain;

import com.online.college.common.orm.BaseEntity;

public class ConstsCollege extends BaseEntity{

	private static final long serialVersionUID = -7643904360103197835L;

	/**
	*名称
	**/
	private String name;

	/**
	*编码
	**/
	private String code;

	/**
	*图片
	**/
	private String picture;

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code = code;
	}

	public String getPicture(){
		return picture;
	}
	public void setPicture(String picture){
		this.picture = picture;
	}



}

