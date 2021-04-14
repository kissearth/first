package com.ylkj.xxb.domain.dictionary.type;

import java.util.List;

import javax.validation.Valid;

import com.ylkj.xxb.domain.dictionary.Dictionary;
import com.ylkj.xxb.domain.dictionary.DictionaryMapper;
import com.ylkj.xxb.util.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class DictionaryTypeService {

	@Autowired
	private DictionaryTypeMapper dictionaryTypeMapper;

	@Autowired
	private DictionaryMapper dictionaryMapper;

	public DictionaryType save(@Valid DictionaryType dictionaryType) {
		
		// name
		DictionaryType savedName = dictionaryTypeMapper.getByName(dictionaryType.getName());
		if (savedName != null) {
			throw new ServiceException("操作失败，字典类型名称已存在！");
		}
		
		// code
		DictionaryType savedCode = dictionaryTypeMapper.getByCode(dictionaryType.getCode());
		if (savedCode != null) {
			throw new ServiceException("操作失败，字典类型代码已存在！");
		}
		
		// sn
		
		dictionaryTypeMapper.save(dictionaryType);
		return dictionaryType;
	}

	public DictionaryType update(@Valid DictionaryType dictionaryType) {
		
		DictionaryType saved = dictionaryTypeMapper.getByCode(dictionaryType.getCode());
		if (saved == null) {
			throw new ServiceException("操作失败，字典类型不存在！");
		}
		
		// name
		DictionaryType savedName = dictionaryTypeMapper.getByName(dictionaryType.getName());
		if (savedName != null && !savedName.getCode().equals(dictionaryType.getCode())) {
			throw new ServiceException("操作失败，字典类型名称已存在！");
		}
		saved.setName(dictionaryType.getName());
		
		// code 为主键不能修改
		
		// sn
		saved.setSn(dictionaryType.getSn());
		
		dictionaryTypeMapper.update(saved);
		return saved;
	}

	public void remove(String code) {
		List<Dictionary> dictionaryRoots = dictionaryMapper.getRootList(code);
		if (dictionaryRoots != null && dictionaryRoots.size() > 0) {
			throw new ServiceException("操作失败，该字典类型含有字典数据！");
		}
		dictionaryTypeMapper.remove(code);
	}

}
