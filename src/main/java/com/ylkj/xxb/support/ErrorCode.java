package com.ylkj.xxb.support;

public class ErrorCode {

	/**
	 * 账号不存在
	 */
	public static final int LOGIN_FAILED_USERNAME_NOT_FOUND = 10101;

	/**
	 * 用户名或密码错误
	 */
	public static final int LOGIN_FAILED_BAD_CREDENTIALS = 10102;

	/**
	 * 账号已禁用
	 */
	public static final int LOGIN_FAILED_DISABLED = 10103;

	/**
	 * 验证码错误
	 */
	public static final int LOGIN_FAILED_VCODE_INVALID = 10104;

	/**
	 * 账号已存在
	 */
	public static final int REGISTER_FAILED_USERNAME_EXISTS = 10300;

	/**
	 * 用户名不合法
	 */
	public static final int REGISTER_FAILED_USERNAME_ILLEGAL = 10301;

	/**
	 * 数据校验错误
	 */
	public static final int DATA_VALIDATION_ERROR = 10400;

	/**
	 * 身份认证错误
	 */
	public static final int UNAUTHORIZED_ERROR = 10401;

	/**
	 * 禁止访问错误
	 */
	public static final int ACCESS_FORBIDDEN_ERROR = 10403;

	/**
	 * 不允许的http方法异常
	 */
	public static final int HTTP_METHOD_NOT_SUPPORTED = 10405;

	/**
	 * 服务异常
	 */
	public static final int SERVICE_EXCEPTION = 10500;

	/**
	 * 应用异常
	 */
	public static final int APP_EXCEPTION = 10501;

	/**
	 * 认证服务异常
	 */
	public static final int AUTHENTICATION_SERVICE_EXCEPTION = 10505;

	/**
	 * 未知错误
	 */
	public static final int UNKNOWN_ERROR = 10999;

	/**
	 * 109XX 自定义错误
	 */

}
