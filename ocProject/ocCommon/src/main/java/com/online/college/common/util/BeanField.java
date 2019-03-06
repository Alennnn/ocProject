package com.online.college.common.util;

import java.lang.reflect.Field;

public class BeanField {
	private String columnName;
	private Field field;
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	
}
