package com.skysea.monitor.web.support;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Date convert(String source) {
		if(StringUtils.isBlank(source)){
			return null;
		}
		try {
			if(source.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")){
				return DateUtils.parseDate(source, "yyyy-MM-dd");
			}else if(source.matches("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}")){
				return DateUtils.parseDate(source, "yyyy-MM-dd HH:mm:ss");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
}