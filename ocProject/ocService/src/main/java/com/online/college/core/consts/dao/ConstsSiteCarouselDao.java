package com.online.college.core.consts.dao;

import java.util.List;
import com.online.college.common.page.TailPage;
import com.online.college.core.consts.domain.ConstsSiteCarousel;


public interface ConstsSiteCarouselDao {

	/**
	*根据id获取
	**/
	public ConstsSiteCarousel getById(Long id);
	
	/**
	 * 获取轮播
	 */
	public List<ConstsSiteCarousel> queryCarousels(Integer count);

	/**
	*获取所有
	**/
	public List<ConstsSiteCarousel> queryAll(ConstsSiteCarousel queryEntity);

	/**
	*获取总数量
	**/
	public Integer getTotalItemsCount(ConstsSiteCarousel queryEntity);

	/**
	*分页获取
	**/
	public List<ConstsSiteCarousel> queryPage(ConstsSiteCarousel queryEntity , TailPage<ConstsSiteCarousel> page);

	/**
	*创建新记录
	**/
	public void create(ConstsSiteCarousel entity);
	
	/**
	 * 创建新记录
	 */
	public void createSelectivity(ConstsSiteCarousel entity);

	/**
	*根据id更新
	**/
	public void update(ConstsSiteCarousel entity);

	/**
	*根据id选择性更新自动
	**/
	public void updateSelectivity(ConstsSiteCarousel entity);

	/**
	*物理删除
	**/
	public void delete(ConstsSiteCarousel entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(ConstsSiteCarousel entity);



}

