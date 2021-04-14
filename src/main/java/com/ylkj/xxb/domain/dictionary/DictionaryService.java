package com.ylkj.xxb.domain.dictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import com.ylkj.xxb.domain.dictionary.type.DictionaryType;
import com.ylkj.xxb.domain.dictionary.type.DictionaryTypeMapper;
import com.ylkj.xxb.util.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class DictionaryService {

    @Autowired
    private DictionaryTypeMapper dictionaryTypeMapper;

    @Autowired
    private DictionaryMapper dictionaryMapper;

    public DictionaryChain getChain(String typeCode, String code) {
        if (StringUtils.isEmpty(code)) {
            return new DictionaryChain();
        } else {
            Dictionary dictionary = dictionaryMapper.getByCode(typeCode, code);
            if (dictionary == null) {
                return new DictionaryChain();
            }
            List<Dictionary> parents = getParents(typeCode, code);
            // codes, names
            List<String> codes = new ArrayList<>();
            List<String> names = new ArrayList<>();
            for (Dictionary parent : parents) {
                codes.add(parent.getCode());
                names.add(parent.getName());
            }
            codes.add(dictionary.getCode());
            names.add(dictionary.getName());
            DictionaryChain chain = new DictionaryChain();
            chain.setCodes(codes);
            chain.setNames(names);
            chain.setNamesChain(String.join(" > ", names));
            return chain;
        }
    }

    public List<Dictionary> getParents(String typeCode, String code) {
        List<Dictionary> list = new ArrayList<>();
        Dictionary child = dictionaryMapper.getByCode(typeCode, code);
        if (child == null) {
            return list;
        }
        Dictionary parent = dictionaryMapper.getByCode(typeCode, child.getParent_code());
        while (parent != null) {
            list.add(parent);
            child = parent;
            parent = dictionaryMapper.getByCode(typeCode, parent.getParent_code());
        }
        Collections.reverse(list);
        return list;
    }

    public Dictionary save(@Valid Dictionary dictionary) {

        // typeCode
        DictionaryType savedTypeCode = dictionaryTypeMapper.getByCode(dictionary.getType_code());
        if (savedTypeCode == null) {
            throw new ServiceException("操作失败，字典数据类型不存在！");
        }

        // code
        Dictionary savedCode = dictionaryMapper.getByCode(dictionary.getType_code(), dictionary.getCode());
        if (savedCode != null) {
            throw new ServiceException("操作失败，字典数据代码已存在！");
        }

        // parentCode
        if (StringUtils.isEmpty(dictionary.getParent_code())) {
            dictionary.setParent_code(null);
        } else {
            Dictionary savedParentCode = dictionaryMapper.getByCode(dictionary.getType_code(), dictionary.getParent_code());
            if (savedParentCode == null) {
                throw new ServiceException("操作失败，上级字典数据不存在！");
            }
        }

        // name
        Dictionary savedName = dictionaryMapper.getByName(dictionary.getType_code(), dictionary.getName());
        if (savedName != null) {
            throw new ServiceException("操作失败，字典数据名称已存在！");
        }

        // sn

        dictionaryMapper.save(dictionary);
        return dictionary;
    }

    public Dictionary update(@Valid Dictionary dictionary) {

        Dictionary saved = dictionaryMapper.getByCode(dictionary.getType_code(), dictionary.getCode());

        if (saved == null) {
            throw new ServiceException("操作失败，字典数据不存在！");
        }

        // typeCode 字典数据类型，不允许修改

        // code 为主键不允许修改

        // parentCode
        if (StringUtils.isEmpty(dictionary.getParent_code())) {
            saved.setParent_code(null);
        } else {
            Dictionary savedParentCode = dictionaryMapper.getByCode(dictionary.getType_code(), dictionary.getParent_code());
            if (savedParentCode == null) {
                throw new ServiceException("操作失败，上级字典数据不存在！");
            }
            saved.setParent_code(dictionary.getParent_code());
        }

        // name
        Dictionary savedName = dictionaryMapper.getByName(dictionary.getType_code(), dictionary.getName());
        if (savedName != null && !savedName.getCode().equals(dictionary.getCode())) {
            throw new ServiceException("操作失败，字典数据名称已存在！");
        }
        saved.setName(dictionary.getName());

        // sn
        saved.setSn(dictionary.getSn());

        dictionaryMapper.update(saved);
        return saved;
    }

    public void remove(String typeCode, String code) {
        List<Dictionary> children = dictionaryMapper.getChildrenList(typeCode, code);
        if (children != null && children.size() > 0) {
            throw new ServiceException("操作失败，该字典数据包含下级！");
        }
        dictionaryMapper.remove(typeCode, code);
    }

}
