package com.online.college.portal.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.online.college.common.page.TailPage;
import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.web.JsonView;
import com.online.college.common.web.SessionContext;
import com.online.college.core.course.domain.CourseComment;
import com.online.college.core.course.domain.CourseSection;
import com.online.college.core.course.service.ICourseCommentService;
import com.online.college.core.course.service.ICourseSectionService;

/**
 * 课程评论管理
 */
@Controller
@RequestMapping("/courseComment")
public class CourseCommentController {
	
	@Autowired
	private ICourseCommentService courseCommentService;
	
	@Autowired
	private ICourseSectionService courseSectionService;
	
	/**
	 * 加载评论&答疑
	 * sectionId：章节id
	 * courseId ：课程id
	 * type : 类型
	 * @return
	 */
	@RequestMapping("/segment")
	public ModelAndView segment(CourseComment queryEntity , TailPage<CourseComment> page){
		if(null == queryEntity.getCourseId() || queryEntity.getType() == null)
			return new ModelAndView("error/404"); 
		
		ModelAndView mv = new ModelAndView("commentSegment");
		TailPage<CourseComment> commentPage = this.courseCommentService.queryPage(queryEntity, page);
		
		//处理用户头像
		for(CourseComment item : commentPage.getItems()){
			if(StringUtils.isNotEmpty(item.getHeader())){
				item.setHeader(QiniuStorage.getUrl(item.getHeader()));
			}
		}
		
		mv.addObject("page", commentPage);
		return mv;
	}
	
	/**
	 * 发表评论
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/doComment")
	@ResponseBody
	public String doComment(HttpServletRequest request, CourseComment entity,String indeityCode){
		
		//验证码判断
		if(null == indeityCode || 
				(indeityCode != null && !indeityCode.equalsIgnoreCase(SessionContext.getIdentifyCode(request)))){
			return new JsonView(2).toString();//验证码错误
		}
		
		//文字太长
		if(entity.getContent().trim().length() > 200 || entity.getContent().trim().length() == 0){
			return new JsonView(3).toString();//文字太长或者为空
		}
		
		if(null != entity.getRefId()){//来自于个人中心评论
			CourseComment refComment = this.courseCommentService.getById(entity.getRefId());
			if(null != refComment){
				CourseSection courseSection = courseSectionService.getById(refComment.getSectionId());
				if(null != courseSection){
					entity.setRefContent(refComment.getContent());
					entity.setRefId(entity.getRefId());
					entity.setCourseId(refComment.getCourseId());
					entity.setSectionId(refComment.getSectionId());
					entity.setSectionTitle(courseSection.getName());
					
					entity.setToUsername(refComment.getUsername());//引用的评论的username
					entity.setUsername(SessionContext.getUsername());
					entity.setCreateTime(new Date());
					entity.setCreateUser(SessionContext.getUsername());
					entity.setUpdateTime(new Date());
					entity.setUpdateUser(SessionContext.getUsername());
					
					this.courseCommentService.createSelectivity(entity);
					return new JsonView(0).toString();
				}
			}
		}else{
			CourseSection courseSection = courseSectionService.getById(entity.getSectionId());
			if(null != courseSection){
				entity.setSectionTitle(courseSection.getName());
				entity.setToUsername(entity.getCreateUser());//toUsername可以作为页面入参
				entity.setUsername(SessionContext.getUsername());
				entity.setCreateTime(new Date());
				entity.setCreateUser(SessionContext.getUsername());
				entity.setUpdateTime(new Date());
				entity.setUpdateUser(SessionContext.getUsername());
				
				this.courseCommentService.createSelectivity(entity);
				return new JsonView(0).toString();
			}
		}
		return new JsonView(1).toString();
	}
	
}

