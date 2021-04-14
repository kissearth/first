package com.ylkj.xxb.domain.dictionary.type;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.pagehelper.Page;

import java.util.List;

@Mapper
public interface DictionaryTypeMapper {
	
	DictionaryType getByName(@Param("name") String name);
	
	DictionaryType getByCode(@Param("code") String code);

	Page<DictionaryType> getPage(@Param("search") String search, RowBounds rowBounds);

	int save(DictionaryType dictionaryType);

	int update(DictionaryType dictionaryType);

	int remove(@Param("code") String code);

}