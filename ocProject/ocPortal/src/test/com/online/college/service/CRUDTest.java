package com.online.college.service;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.online.college.common.page.TailPage;
import com.online.college.common.web.SpringBeanFactory;
import com.online.college.core.auth.domain.AuthUser;
import com.online.college.core.auth.service.IAuthUserService;

/**
 * 数据库 增删查改 ，分页 测试用例
 */
public class CRUDTest extends TestCase {
	
	Logger log = Logger.getLogger(CRUDTest.class);
	
	/**
	 * 测试创建
	 */
	public void testCreate() {
		IAuthUserService authUserService = (IAuthUserService) SpringBeanFactory.getBean("authUserServiceImpl");
		log.info("------------------ create  start ");
		AuthUser authUser = new AuthUser();
		authUser.setUsername("test0001");
		authUser.setPassword("test0001");
		authUser.setRealname("用户0001");
		authUserService.createSelectivity(authUser);
		System.out.println("id = " + authUser.getId());
		log.info("------------------ create  end ");
	}
	
	/**
	 * 测试根据id获取
	 */
	public void testGet() {
		log.info("------------------ get  start ");
		IAuthUserService authUserService = (IAuthUserService) SpringBeanFactory.getBean("authUserServiceImpl");
		AuthUser authUser = authUserService.getById(12L);
		if(null != authUser){
			System.out.println("authUser.username = " + authUser.getUsername());
		}
		log.info("------------------ get  end ");
	}
	
	/**
	 * 测试更新
	 */
	public void testUpdate() {
		log.info("------------------ update  start ");
		IAuthUserService authUserService = (IAuthUserService) SpringBeanFactory.getBean("authUserServiceImpl");
		AuthUser authUser = authUserService.getById(12L);
		authUser.setRealname("新的test00001");
		authUserService.updateSelectivity(authUser);
		log.info("------------------ update  end ");
	}
	
	/**
	 * 测试分页
	 */
	public void testPage() {
		log.info("------------------ page  start ");
		IAuthUserService authUserService = (IAuthUserService) SpringBeanFactory.getBean("authUserServiceImpl");
		TailPage<AuthUser> page = new TailPage<AuthUser>();
		page = authUserService.queryPage(new AuthUser(), page);
		System.out.println("page.size" +  page.getItems().size());
		for(AuthUser item : page.getItems()){
			System.out.println("item.username = " + item.getUsername());
		}
		log.info("------------------ page  end ");
	}
	
	/**
	 * 测试删除
	 */
	public void testDelete() {
		IAuthUserService authUserService = (IAuthUserService) SpringBeanFactory.getBean("authUserServiceImpl");
		AuthUser authUser = authUserService.getById(12L);
		if(null != authUser){
			authUserService.delete(authUser);
		}
	}
	
}

