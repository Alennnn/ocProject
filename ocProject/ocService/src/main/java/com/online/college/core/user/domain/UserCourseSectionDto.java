package com.online.college.core.user.domain;

/**
 * 用户学习课程dto
 */
public class UserCourseSectionDto extends UserCourseSection {
	
	private static final long serialVersionUID = 608405844566660424L;

	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 课程名
	 */
	private String courseName;
	
	/**
	 * 章节名
	 */
	private String sectionName;
	
	/**
	 * 用户头像
	 */
	private String header;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
}
