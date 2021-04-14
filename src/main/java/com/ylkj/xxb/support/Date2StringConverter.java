package com.ylkj.xxb.support;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Date2StringConverter implements Converter<Date, String> {

	@Override
	public String convert(Date source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(source);
	}

}
