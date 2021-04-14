package com.ylkj.xxb.controller;

import com.ylkj.xxb.domain.dictionary.Dictionary;
import com.ylkj.xxb.domain.dictionary.DictionaryMapper;
import com.ylkj.xxb.domain.dictionary.DictionaryService;
import com.ylkj.xxb.domain.dictionary.type.DictionaryType;
import com.ylkj.xxb.domain.dictionary.type.DictionaryTypeMapper;
import com.ylkj.xxb.domain.dictionary.type.DictionaryTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "添加字典类型controller")
@RestController
public class DictionaryController {

    @Autowired
    private DictionaryTypeMapper dictionaryTypeMapper;

    @Autowired
    private DictionaryTypeService dictionaryTypeService;

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private DictionaryService dictionaryService;

    @ApiOperation(value = "添加一种字典类型")
    @PostMapping("/dictionary-type/save")
    public void saveDictionaryType(@RequestBody DictionaryType dictionaryType) {

        dictionaryTypeService.save(dictionaryType);
    }

    @ApiOperation(value = "添加一个字典")
    @PostMapping("/dictionary/save")
    public void saveDictionary(@RequestBody Dictionary dictionary) {

        dictionaryService.save(dictionary);
    }

    @ApiOperation(value = "根据字典类型获取字典列表")
    @GetMapping("/dictionary-list/by-type")
    public List<Dictionary> getByDictionaryType(@RequestParam("typeCode") String typeCode) {

        List<Dictionary> rootList = dictionaryMapper.getRootList(typeCode);
        return rootList;
    }

}
