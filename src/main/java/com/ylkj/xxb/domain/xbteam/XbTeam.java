package com.ylkj.xxb.domain.xbteam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value = "用户对象")
@Setter
@Getter
public class XbTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "队伍姓名")
    @NotNull
    @Length(max = 50)
    private String name;

    @ApiModelProperty(value = "队伍编号")
    private String code;


}
