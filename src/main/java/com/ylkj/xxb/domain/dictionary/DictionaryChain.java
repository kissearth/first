package com.ylkj.xxb.domain.dictionary;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DictionaryChain {

    private List<String> codes = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private String namesChain = "";

}

