package com.online.college.portal.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.core.consts.CourseEnum;
import com.online.college.core.user.domain.UserCollections;
import com.online.college.core.user.service.IUserCollectionsService;

/**
 * 用户收藏
 */
@Controller
@RequestMapping("/collections")
public class CollectionsController{

	@Autowired
	private IUserCollectionsService userCollectionsService;

	@RequestMapping(value = "/doCollection")
	@ResponseBody
	public String doCollection(Long courseId){
		//获取当前用户
		Long curUserId = SessionContext.getUserId(); 
		UserCollections userCollections = new UserCollections();
		
		userCollections.setUserId(curUserId);
		userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());//课程收藏
		userCollections.setObjectId(courseId);
		List<UserCollections> list = userCollectionsService.queryAll(userCollections);
		
		if(CollectionUtils.isNotEmpty(list)){
			userCollectionsService.delete(list.get(0));
			return new JsonView(0).toString();
		}else{
			userCollections.setCreateTime(new Date());
			userCollectionsService.createSelectivity(userCollections);
			return new JsonView(1).toString();//已经收藏
		}
	}
	
	/**
	 * 是否已经收藏
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/isCollection")
	@ResponseBody
	public String isCollection(Long courseId){
		//获取当前用户
		Long curUserId = SessionContext.getUserId(); 
		UserCollections userCollections = new UserCollections();
		
		userCollections.setUserId(curUserId);
		userCollections.setClassify(CourseEnum.COLLECTION_CLASSIFY_COURSE.value());//课程收藏
		userCollections.setObjectId(courseId);
		List<UserCollections> list = userCollectionsService.queryAll(userCollections);
		
		if(CollectionUtils.isNotEmpty(list)){//已经收藏
			return new JsonView(1).toString();
		}else{
			return new JsonView(0).toString();
		}
	}
	
}

