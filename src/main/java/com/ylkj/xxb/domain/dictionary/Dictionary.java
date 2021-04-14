package com.ylkj.xxb.domain.dictionary;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "字段类型信息实体")
@Setter
@Getter
@NoArgsConstructor
public class Dictionary {

	@NotNull
	@NotBlank
	private String type_code;

	@NotNull
	@NotBlank
	private String code;

	private String parent_code;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String sn;

}
