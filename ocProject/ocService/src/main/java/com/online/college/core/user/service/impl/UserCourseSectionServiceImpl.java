package com.online.college.core.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.core.user.dao.UserCourseSectionDao;
import com.online.college.core.user.domain.UserCourseSection;
import com.online.college.core.user.domain.UserCourseSectionDto;
import com.online.college.core.user.service.IUserCourseSectionService;


@Service
public class UserCourseSectionServiceImpl implements IUserCourseSectionService{

	@Autowired
	private UserCourseSectionDao entityDao;

	public UserCourseSection getById(Long id){
		return entityDao.getById(id);
	}

	public List<UserCourseSection> queryAll(UserCourseSection queryEntity){
		return entityDao.queryAll(queryEntity);
	}
	
	public UserCourseSection queryLatest(UserCourseSection queryEntity){
		return entityDao.queryLatest(queryEntity);
	}

	public TailPage<UserCourseSectionDto> queryPage(UserCourseSection queryEntity , TailPage<UserCourseSectionDto> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<UserCourseSectionDto> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void createSelectivity(UserCourseSection entity){
		entityDao.createSelectivity(entity);
	}

	public void update(UserCourseSection entity){
		entityDao.update(entity);
	}

	public void updateSelectivity(UserCourseSection entity){
		entityDao.updateSelectivity(entity);
	}

	public void delete(UserCourseSection entity){
		entityDao.delete(entity);
	}

	public void deleteLogic(UserCourseSection entity){
		entityDao.deleteLogic(entity);
	}



}

