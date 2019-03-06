package com.online.college.opt.controller;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.storage.ThumbModel;
import com.online.college.common.web.JsonView;
import com.online.college.core.consts.domain.ConstsSiteCarousel;
import com.online.college.core.consts.service.IConstsSiteCarouselService;

/**
 * 轮播配置
 */
@Controller
@RequestMapping("/carousel")
public class SiteCarouselController{
	
	@Autowired
	private IConstsSiteCarouselService entityService;

	@RequestMapping(value = "/queryPage")
	public  ModelAndView queryPage(ConstsSiteCarousel queryEntity , TailPage<ConstsSiteCarousel> page){
		ModelAndView mv = new ModelAndView("cms/carousel/pagelist");
		mv.addObject("curNav", "carousel");
		page.setPageSize(5);//每页5条数据
		page = entityService.queryPage(queryEntity,page);
		mv.addObject("page",page);
		mv.addObject("queryEntity",queryEntity);
		return mv;
	}

	@RequestMapping(value = "/toMerge")
	public ModelAndView toMerge(ConstsSiteCarousel entity){
		ModelAndView mv = new ModelAndView("cms/carousel/merge");
		mv.addObject("curNav", "carousel");
		
		if(entity.getId() != null){
			entity = entityService.getById(entity.getId());
			if(null != entity && StringUtils.isNotEmpty(entity.getPicture())){
				String pictureUrl = QiniuStorage.getUrl(entity.getPicture(),ThumbModel.THUMB_128);
				entity.setPicture(pictureUrl);
			}
		}
		mv.addObject("entity",entity);
		return mv;
	}

	@RequestMapping(value = "/doMerge")
	public ModelAndView doMerge(ConstsSiteCarousel entity,@RequestParam MultipartFile pictureImg){
		String key = null;
		try {
			if (null != pictureImg && pictureImg.getBytes().length > 0) {
				key = QiniuStorage.uploadImage(pictureImg.getBytes());
				entity.setPicture(key);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(entity.getId() == null){
			entityService.createSelectivity(entity);
		}else{
			entityService.updateSelectivity(entity);
		}
		return new ModelAndView("redirect:/carousel/queryPage.html");
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(ConstsSiteCarousel entity){
		entityService.delete(entity);
		return new JsonView().toString();
	}
	
}

