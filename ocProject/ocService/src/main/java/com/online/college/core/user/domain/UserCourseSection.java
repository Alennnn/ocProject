package com.online.college.core.user.domain;

import com.online.college.common.orm.BaseEntity;


public class UserCourseSection extends BaseEntity{

	private static final long serialVersionUID = 5447461555053008202L;

	/**
	*用户id
	**/
	private Long userId;
	
	/**
	*课程id
	**/
	private Long courseId;
	
	/**
	*章节id
	**/
	private Long sectionId;
	
	/**
	*状态：0-学习中；1-学习结束
	**/
	private Integer status;
	
	/**
	 * 进度
	 */
	private Integer rate;

	public Long getUserId(){
		return userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getCourseId(){
		return courseId;
	}
	public void setCourseId(Long courseId){
		this.courseId = courseId;
	}

	public Long getSectionId(){
		return sectionId;
	}
	public void setSectionId(Long sectionId){
		this.sectionId = sectionId;
	}

	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}

}

