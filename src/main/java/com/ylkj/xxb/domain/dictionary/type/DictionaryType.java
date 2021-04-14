package com.ylkj.xxb.domain.dictionary.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "字典类型实体")
@Getter
@Setter
public class DictionaryType {

	@ApiModelProperty(value = "名称")
	@NotNull
	@NotBlank
	private String name;

	@ApiModelProperty(value = "代码")
	@NotNull
	@NotBlank
	private String code;

	@ApiModelProperty(value = "序号")
	@NotNull
	@NotBlank
	private String sn;

}
