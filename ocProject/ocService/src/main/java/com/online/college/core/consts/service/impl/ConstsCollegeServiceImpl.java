package com.online.college.core.consts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.common.page.TailPage;
import com.online.college.core.consts.dao.ConstsCollegeDao;
import com.online.college.core.consts.domain.ConstsCollege;
import com.online.college.core.consts.service.IConstsCollegeService;


@Service
public class ConstsCollegeServiceImpl implements IConstsCollegeService{

	@Autowired
	private ConstsCollegeDao entityDao;

	/**
	 * 根据id获取
	 */
	public ConstsCollege getById(Long id){
		return entityDao.getById(id);
	}
	
	/**
	 * 根据code获取
	 */
	public ConstsCollege getByCode(String code){
		return entityDao.getByCode(code);
	}

	public List<ConstsCollege> queryAll(ConstsCollege queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public TailPage<ConstsCollege> queryPage(ConstsCollege queryEntity ,TailPage<ConstsCollege> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<ConstsCollege> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(ConstsCollege entity){
		entityDao.create(entity);
	}
	
	/**
	 * 创建网校
	 */
	public void createSelectivity(ConstsCollege entity){
		this.entityDao.createSelectivity(entity);
	}

	public void update(ConstsCollege entity){
		entityDao.update(entity);
	}

	public void updateSelectivity(ConstsCollege entity){
		entityDao.updateSelectivity(entity);
	}

	public void delete(ConstsCollege entity){
		entityDao.delete(entity);
	}

	public void deleteLogic(ConstsCollege entity){
		entityDao.deleteLogic(entity);
	}



}

