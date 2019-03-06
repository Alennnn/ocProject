package com.online.college.common.storage;

/**
 * 定义文件类型及其对应魔数
 */
public enum FileType {
	JPEG("FFD8FF"),

	PNG("89504E47"),

	GIF("47494638");
	
	private String value = "";

	private FileType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}