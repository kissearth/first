package com.ylkj.xxb.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ylkj.xxb.util.ServiceException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

public class ErrorMvcAttributes extends DefaultErrorAttributes {

    private static final String ERROR_DATA_VALIDATION = "dataValidationError";

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Throwable error = super.getError(webRequest);
        //父类方法的error值，可供查看参考
        Map<String, Object> attributes = super.getErrorAttributes(webRequest, options);
        if (error != null) {
            try {
                if (error instanceof HttpMessageNotReadableException && error.getCause() instanceof InvalidFormatException) {
                    attributes.put("code", ErrorCode.DATA_VALIDATION_ERROR);
                    attributes.put("error", ERROR_DATA_VALIDATION);
                    attributes.put("message", contert((InvalidFormatException) error.getCause()));
                } else if (error instanceof HttpRequestMethodNotSupportedException) {
                    attributes.put("code", ErrorCode.HTTP_METHOD_NOT_SUPPORTED);
                    attributes.put("error", "应用异常");
                    attributes.put("message", error.getMessage());
                } else if (error instanceof MissingServletRequestParameterException) {
                    attributes.put("code", ErrorCode.SERVICE_EXCEPTION);
                    attributes.put("error", "参数" + ((MissingServletRequestParameterException) error).getParameterName() + "不存在！");
                    attributes.put("message", error.getMessage());
                } else if (error instanceof ServiceException) {
                    attributes.put("code", ErrorCode.SERVICE_EXCEPTION);
                    attributes.put("error", "服务异常");
                    attributes.put("message", error.getMessage());
                } else {
                    attributes.put("code", ErrorCode.UNKNOWN_ERROR);
                    attributes.put("error", "应用异常");
                    attributes.put("message", error.getMessage());
                }
            } catch (Exception e) {
                attributes.put("code", ErrorCode.UNKNOWN_ERROR);
                attributes.put("error", "应用异常");
                attributes.put("message", e.getMessage());
            }
        } else {
            if (HttpStatus.FORBIDDEN.value() == (Integer) attributes.get("status")) {
                attributes.put("code", ErrorCode.ACCESS_FORBIDDEN_ERROR);
                attributes.put("error", "应用异常");
                attributes.put("message", "禁止访问！");
            } else {
                attributes.put("code", ErrorCode.UNKNOWN_ERROR);
                attributes.put("error", "应用异常");
                attributes.put("message", "未知错误！");
            }
        }
        return attributes;
    }

    private String contert(InvalidFormatException invalidFormatException) throws JsonProcessingException {
        String fieldName = invalidFormatException.getPath().get(0).getFieldName();
        String fieldTypeName = translate(invalidFormatException.getTargetType());
        Map<String, String> errors = new HashMap<String, String>();
        errors.put(fieldName, "必须为" + fieldTypeName);
        return new ObjectMapper().writeValueAsString(errors);
    }

    private String translate(Class<?> clazz) {
        if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
            return "整数";
        } else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
            return "浮点数";
        } else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
            return "双精度浮点数";
        } else if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
            return "布尔型";
        } else {
            return clazz.getSimpleName();
        }
    }
}
