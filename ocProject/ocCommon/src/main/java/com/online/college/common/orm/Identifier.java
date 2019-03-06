package com.online.college.common.orm;

import java.io.Serializable;

/**
 * @param <KEY>
 */
public interface Identifier<KEY extends Serializable> {

	public KEY getId(); 
	
}
