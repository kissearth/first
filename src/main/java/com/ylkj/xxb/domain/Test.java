package com.ylkj.xxb.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@ApiModel(value = "测试对象")
@Setter
@Getter
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型code")
    private String typeCode;

    public Test(String name) {
        this.name = name;
    }

}
