package com.ylkj.xxb.support;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorAttributes {
	
	private int code;
	
	private int satus;
	
	private String error;
	
	private String message;
	
	private String path;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date timestamp;

}
