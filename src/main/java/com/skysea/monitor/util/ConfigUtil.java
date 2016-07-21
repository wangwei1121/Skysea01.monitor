package com.skysea.monitor.util;

import java.util.ResourceBundle;

public class ConfigUtil {

	private static final ResourceBundle config = ResourceBundle.getBundle("config");
	
	private static final ResourceBundle messgae = ResourceBundle.getBundle("i18n/message");
	

	public static final String get(String key) {
		return config.getString(key);
	}

	public static final String getMessgae(String key) {
		return messgae.getString(key);
	}
	
}
