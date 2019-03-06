package com.online.college.common.storage;



/**
 * 七牛云存储管理器
 * 上传图像和查看图像
 */
public class QiniuStorage {
	
	/**
	 * 上传单张图片；返回上传图片的key
	 * @param buff
	 */
	public static String uploadImage(byte[] buff){
		String key = QiniuKeyGenerator.generateKey();
		key = QiniuWrapper.upload(buff, key,false);
		return key;
	}
	
	/**
	 * 上传单张图片；返回上传图片的url，此url会过期，切记不要存储在数据库中；
	 * @param buff
	 * @param img
	 * @return QiniuImg
	 */
	public static QiniuImg uploadImage(byte[] buff,QiniuImg img){
		String key = QiniuWrapper.upload(buff, img.getUploadKey(),false);
		img.setKey(key);
		return img;
	}
	
	
	/**
	 * 上传多个图片
	 * @param imageBuffs 图片字节数组
	 * @param img 分组图片的属性
	 * @return
	 */
	public static QiniuImg[] uploadImages(byte[][] imageBuffs,QiniuImg img){
		QiniuImg[] images = new QiniuImg[imageBuffs.length];
		for(int i = 0; i < imageBuffs.length; i++){
			QiniuImg newImg = new QiniuImg();
			uploadImage(imageBuffs[i],img);
			newImg.setKey(img.getKey());
			images[i] = newImg;
		}
		return images;
	}
	
	
	/**
	 * 获取图片链接
	 * @param key
	 * @return
	 */
	public static String getUrl(String key){
		return QiniuWrapper.getUrl(key);
	}
	
	/**
	 * 获取有有效期的图片链接
	 * @param key
	 * @param expires 单位：秒
	 * @return
	 */
	public static String getUrl(String key,long expires){
		return QiniuWrapper.getUrl(key,expires);
	}
	
	
	/**
	 * 获取图片链接
	 * @param key
	 * @return
	 */
	public static String getUrl(String key,ThumbModel model){
		return QiniuWrapper.getUrl(key,model.getValue());
	}
	
	/**
	 * 获取有有效期的图片链接
	 * @param key
	 * @param expires 单位：秒
	 * @return
	 */
	public static String getUrl(String key,ThumbModel model,long expires){
		return QiniuWrapper.getUrl(key,model.getValue(),expires);
	}
	
}
