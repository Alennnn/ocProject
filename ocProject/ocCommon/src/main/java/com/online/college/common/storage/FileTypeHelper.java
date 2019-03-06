package com.online.college.common.storage;

import java.util.Arrays;
import org.springframework.util.Assert;

public class FileTypeHelper {
	
	public static FileType getType(byte[] buff){
		Assert.isTrue(buff!=null &&buff.length>28);
		byte[] bytes= Arrays.copyOfRange(buff, 0, 28);
		String magic = bytesToHex(bytes);
		
		for(FileType type:FileType.values()){
			if(magic.startsWith(type.getValue())) return type;
		}
		return null;
	}
	
	/**
	 * 判断是否为图片，是图片返回true，否则返回false
	 * 
	 * @param buff
	 * @return 图片返回true，否则返回false
	 */
	public static boolean isImage(byte[] buff){
		FileType type = getType(buff);
		return FileType.JPEG.equals(type)||FileType.PNG.equals(type);
	}
	
	private static String bytesToHex(byte[] src) {  
		StringBuilder stringBuilder = new StringBuilder();  
		if (src == null || src.length <= 0) return null;  
		for (int i = 0; i < src.length; i++) {  
			int v = src[i] & 0xFF;  
			String hv = Integer.toHexString(v);  
			if (hv.length() < 2) {  
			stringBuilder.append(0);  
			}  
			stringBuilder.append(hv);  
		}  
		return stringBuilder.toString().toUpperCase();  
	} 

}
