package com.online.college.common.web;


/**
 * Session中setAttribute的对象，加入了超时属性
 * 时间从创建对象开始；
 * @author Brain
 */
public class SessionTimeObj {

	private Object value;//值对象
	private Long overtime;//超时时间,毫秒
	private long currentTime;//加入value时的当前时间
	
	public SessionTimeObj(Object value,Long overtime){
		this.value = value;
		this.overtime = overtime;
		currentTime = System.currentTimeMillis();
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Long getOvertime() {
		return overtime;
	}
	public void setOvertime(Long overtime) {
		this.overtime = overtime;
	}
	
	//超时返回true
	public boolean isOvertime(){
		return System.currentTimeMillis() - currentTime > overtime;
	}
	
}
