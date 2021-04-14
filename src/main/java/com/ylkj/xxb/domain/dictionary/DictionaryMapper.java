package com.ylkj.xxb.domain.dictionary;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictionaryMapper {

	Dictionary getByName(@Param("type_code") String typeCode, @Param("name") String name);
	
	Dictionary getByCode(@Param("type_code") String typeCode, @Param("code") String code);

	List<Dictionary> getRootList(@Param("type_code") String typeCode);

	List<Dictionary> getChildrenList(@Param("type_code") String typeCode, @Param("code") String code);

	int save(Dictionary dictionary);

	int update(Dictionary dictionary);

	int remove(@Param("type_code") String typeCode, @Param("code") String code);

}