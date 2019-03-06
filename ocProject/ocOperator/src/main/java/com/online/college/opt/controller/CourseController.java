package com.online.college.opt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.online.college.common.util.CalendarUtil;
import com.online.college.common.util.JsonUtil;
import com.online.college.common.web.JsonView;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;
import com.online.college.core.consts.domain.ConstsClassify;
import com.online.college.core.consts.service.IConstsClassifyService;
import com.online.college.core.course.domain.Course;
import com.online.college.core.course.service.ICourseService;
import com.online.college.core.statics.domain.CourseStudyStaticsDto;
import com.online.college.core.statics.domain.StaticsVO;
import com.online.college.core.statics.service.IStaticsService;
import com.online.college.opt.business.IPortalBusiness;
import com.online.college.opt.vo.ConstsClassifyVO;
import com.online.college.opt.vo.CourseSectionVO;

/**
 * 课程管理
 */
@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Resource
	private ICourseService courseService;
	
	@Autowired
	private IPortalBusiness portalBusiness;
	
	@Autowired
	private IConstsClassifyService constsClassifyService;
	
	@Autowired
	private IAuthUserService authUserService;
	
	@Autowired
	private IStaticsService staticsService;
	
	/**
	 * 课程管理
	 */
	@RequestMapping("/pagelist")
	public ModelAndView list(Course queryEntity,TailPage<Course> page){
		ModelAndView mv = new ModelAndView("cms/course/pagelist");
		
		if(StringUtils.isNotEmpty(queryEntity.getName())){
			queryEntity.setName(queryEntity.getName().trim());
		}else{
			queryEntity.setName(null);
		}
		
		page.setPageSize(5);
		page = courseService.queryPage(queryEntity, page);
		mv.addObject("page", page);
		mv.addObject("queryEntity", queryEntity);
		mv.addObject("curNav", "course");
		return mv;
	}
	
	/**
	 * 课程上下架
	 */
	@RequestMapping("/doSale")
	@ResponseBody
	public String doSale(Course entity){
		courseService.updateSelectivity(entity);
		//更新课程总时长
		
		return new JsonView().toString();
	}
	
	/**
	 * 课程删除
	 */
	@RequestMapping("/doDelete")
	@ResponseBody
	public String doDelete(Course entity){
		courseService.delete(entity);
		return new JsonView().toString();
	}
	
	/**
	 * 根据id获取
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public String getById(Long id){
		return JsonView.render(courseService.getById(id));
	}
	
	/**
	 * 课程详情
	 */
	@RequestMapping("/read")
	public ModelAndView courseRead(Long id){
		Course course = courseService.getById(id);
		if(null == course){
			return new ModelAndView("error/404"); 
		} 
		
		ModelAndView mv = new ModelAndView("cms/course/read");
		mv.addObject("curNav", "course");
		mv.addObject("course", course);
		
		//课程章节
		List<CourseSectionVO> chaptSections = this.portalBusiness.queryCourseSection(id);
		mv.addObject("chaptSections", chaptSections);
		
		//课程分类
		Map<String,ConstsClassifyVO> classifyMap = portalBusiness.queryAllClassifyMap();
		//所有一级分类
		List<ConstsClassifyVO> classifysList = new ArrayList<ConstsClassifyVO>();
		for(ConstsClassifyVO vo : classifyMap.values()){
			classifysList.add(vo);
		}
		mv.addObject("classifys", classifysList);
		
		List<ConstsClassify> subClassifys = new ArrayList<ConstsClassify>();
		for(ConstsClassifyVO vo : classifyMap.values()){
			subClassifys.addAll(vo.getSubClassifyList());
		}
		mv.addObject("subClassifys", subClassifys);//所有二级分类
		
		//获取报表统计信息
		CourseStudyStaticsDto staticsDto = new CourseStudyStaticsDto();
		staticsDto.setCourseId(course.getId());
		staticsDto.setEndDate(new Date());
		staticsDto.setStartDate(CalendarUtil.getPreNDay(new Date(), 7));
		
		StaticsVO staticsVo = staticsService.queryCourseStudyStatistics(staticsDto);
		if(null != staticsVo){
			try {
				mv.addObject("staticsVo", JsonUtil.toJson(staticsVo));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return mv;
	}
	
	/**
	 * 添加、修改课程
	 */
	@RequestMapping("/doMerge")
	@ResponseBody
	public String doMerge(Course entity,@RequestParam MultipartFile pictureImg){
		String key = null;
		try {
			if (null != pictureImg && pictureImg.getBytes().length > 0) {
				key = QiniuStorage.uploadImage(pictureImg.getBytes());//七牛上传图片
				entity.setPicture(key);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//判断教师
		if(StringUtils.isNotEmpty(entity.getUsername())){
			AuthUser user = authUserService.getByUsername(entity.getUsername());
			if(null == user){
				return JsonView.render(1).toString();
			}
		}else{
			return JsonView.render(1).toString();
		}

		if(null != entity.getId()){
			courseService.updateSelectivity(entity);
		}else{
			//判断获取分类
			if(StringUtils.isNotEmpty(entity.getClassify())){
				ConstsClassify classify = this.constsClassifyService.getByCode(entity.getClassify());
				if(null != classify){
					entity.setClassifyName(classify.getName());
				}
			}
			if(StringUtils.isNotEmpty(entity.getSubClassify())){
				ConstsClassify subClassify = this.constsClassifyService.getByCode(entity.getSubClassify());
				if(null != subClassify){
					entity.setSubClassifyName(subClassify.getName());
				}
			}
			courseService.createSelectivity(entity);
		}
		return JsonView.render(entity).toString();
	}
	
	
	/**
	 * 添加课程
	 */
	@RequestMapping("/add")
	public ModelAndView add(){
		ModelAndView mv = new ModelAndView("cms/course/add");
		mv.addObject("curNav", "course");
		Map<String,ConstsClassifyVO> classifyMap = portalBusiness.queryAllClassifyMap();
		//所有一级分类
		List<ConstsClassifyVO> classifysList = new ArrayList<ConstsClassifyVO>();
		for(ConstsClassifyVO vo : classifyMap.values()){
			classifysList.add(vo);
		}
		mv.addObject("classifys", classifysList);
		
		List<ConstsClassify> subClassifys = new ArrayList<ConstsClassify>();
		for(ConstsClassifyVO vo : classifyMap.values()){
			subClassifys.addAll(vo.getSubClassifyList());
		}
		mv.addObject("subClassifys", subClassifys);//所有二级分类
		return mv; 
	}
	
	//继续添加章节
	@RequestMapping("/append")
	public ModelAndView appendSection(Long courseId){
		Course course = courseService.getById(courseId);
		if(null == course)
			return new ModelAndView("error/404"); 
		
		ModelAndView mv = new ModelAndView("cms/course/append");
		mv.addObject("curNav", "course");
		mv.addObject("course", course);
		
		List<CourseSectionVO> chaptSections = this.portalBusiness.queryCourseSection(courseId);
		mv.addObject("chaptSections", chaptSections);
		
		return mv;
	}
	
}
