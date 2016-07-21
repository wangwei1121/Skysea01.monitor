package com.skysea.monitor.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5加密工具类
 * 
 */
public class MD5Util {
	
	private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d","e", "f" };

	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToString(md.digest(resultString.getBytes()));
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return resultString;
	}
	
	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b 字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));// 若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
			// resultSb.append(byteToNumString(b[i]));//使用本函数则返回加密结果的10进制数字字串，即全数字形式
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}


	/**
	 * 进行md5字符中的比较
	 * 
	 * @param md5a
	 *            String
	 * @param md5b
	 *            String
	 * @return boolean
	 */
	public static boolean compareMD5(String md5a, String md5b) {
		boolean flag = false;
		if (md5a == md5b) {
			return true;
		}
		try {
			flag = MessageDigest.isEqual(md5a.getBytes(), md5b.getBytes());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	public static void main(String args[]) {
		String m = MD5Util.MD5Encode("admin");
		System.out.println(m);
	}

}
