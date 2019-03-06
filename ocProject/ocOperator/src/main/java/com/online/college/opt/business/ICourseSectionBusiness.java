package com.online.college.opt.business;

import java.io.InputStream;
import java.util.List;

import com.online.college.opt.vo.CourseSectionVO;

public interface ICourseSectionBusiness {

	/**
	 * 批量添加
	 * @param courseSections
	 */
	void batchAdd(List<CourseSectionVO> courseSections);
	
	/**
	 * 批量导入
	 */
	void batchImport(Long courseId, InputStream is);
	
}
