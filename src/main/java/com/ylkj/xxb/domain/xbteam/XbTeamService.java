package com.ylkj.xxb.domain.xbteam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class XbTeamService {

    @Autowired
    private XbTeamMapper xbTeamMapper;


}
