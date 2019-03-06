package com.online.college.core.statics.domain;

import java.util.List;

/**
 * 报表分析VO
 */
public class StaticsVO {
	
	/**
	 * 主标题
	 */
	private String title;
	
	/**
	 * 副标题
	 */
	private String subTitle;
	
	/**
	 * 分类
	 */
	private List<String> categories;
	
	/**
	 * 数据
	 */
	private List<Integer> data;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}
}
