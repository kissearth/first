package com.ylkj.xxb.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -7480022450501760611L;

    private String code;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable throwable) {
        super(throwable);
    }

    public ServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
