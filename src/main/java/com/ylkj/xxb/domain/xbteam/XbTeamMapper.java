package com.ylkj.xxb.domain.xbteam;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

@Mapper
public interface XbTeamMapper {

    Page<Map<String, Object>> testFunc(RowBounds rowBounds);

    List<Map<String, Object>> testFunc();

}
