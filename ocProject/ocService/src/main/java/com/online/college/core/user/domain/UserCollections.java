package com.online.college.core.user.domain;

import com.online.college.common.orm.BaseEntity;


public class UserCollections extends BaseEntity{

	private static final long serialVersionUID = -3909997252117758595L;

	/**
	*用户id
	**/
	private Long userId;

	/**
	*用户收藏分类
	**/
	private Integer classify;
	
	/**
	 * 用户收藏id
	 */
	private Long objectId;

	/**
	*用户收藏备注
	**/
	private String tips;
	
	/**
	 * 收藏名称
	 */
	private String name;

	public Long getUserId(){
		return userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Integer getClassify(){
		return classify;
	}
	public void setClassify(Integer classify){
		this.classify = classify;
	}

	public String getTips(){
		return tips;
	}
	public void setTips(String tips){
		this.tips = tips;
	}
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

