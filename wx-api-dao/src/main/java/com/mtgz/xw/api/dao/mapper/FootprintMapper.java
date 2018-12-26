package com.mtgz.xw.api.dao.mapper;

import com.mtgz.xw.api.dao.config.MyMapper;
import com.mtgz.xw.api.dao.model.Footprint;

import java.util.List;
import java.util.Map;

public interface FootprintMapper extends MyMapper<Footprint> {
    int deleteByParam(Map<String, Object> map);

    List<Footprint> shareList(Map<String, Object> map);

    List<Footprint> selectByMapFootprint(String userid);
}