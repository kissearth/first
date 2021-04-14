package com.ylkj.xxb.controller;

import com.ylkj.xxb.domain.xbteam.XbTeam;
import com.ylkj.xxb.domain.xbteam.XbTeamMapper;
import com.ylkj.xxb.util.Pagination;
import com.ylkj.xxb.util.concurrent.ConcurrentExecutor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = "测试接口")
@RestController
public class TestController {

    @Autowired
    private XbTeamMapper xbTeamMapper;

    @Autowired
    private ConcurrentExecutor executor;

    @GetMapping("/test")
    public String test() {
        return "hello world!";
    }

    @ApiOperation(value = "测试数据库连通性")
    @GetMapping("/test1")
    public Pagination<XbTeam> test1(@RequestParam("pageNum") int pageNum,
                                    @RequestParam("pageSize") int pageSize) {
        Pagination page = new Pagination(xbTeamMapper.testFunc(new RowBounds(pageNum, pageSize)));
        return page;
    }

    @GetMapping("/test2")
    public Object test2() {
        List<Map<String, Object>> maps = xbTeamMapper.testFunc();
        return maps;
    }

}
