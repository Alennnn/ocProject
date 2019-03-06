package com.online.college.service;

import java.io.File;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.online.college.common.storage.QiniuStorage;
import com.online.college.common.storage.ThumbModel;
import com.online.college.common.util.CommonUtil;

public class QiNiuTest extends TestCase {
	Logger log = Logger.getLogger(AppTest.class);
	
	public void testImages() {
		//测试上传图片
		byte[] buff = CommonUtil.getFileBytes(new File("E://c4.jpg"));
		String key = QiniuStorage.uploadImage(buff);
		System.out.println("key = " + key);
		
		//String key = "/default/all/0/0755ffd19e3e416db0a69ca3b23d744a.jpeg";
		//测试下载图片
		String url = QiniuStorage.getUrl(key);
		System.out.println("url = " + url);
		
		//测试下载不同大小的图片
		url = QiniuStorage.getUrl(key,ThumbModel.THUMB_256);
		System.out.println("url = " + url);
		
	}
}

