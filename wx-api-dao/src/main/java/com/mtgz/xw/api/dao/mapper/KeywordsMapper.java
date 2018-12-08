package com.mtgz.xw.api.dao.mapper;

import com.mtgz.xw.api.dao.config.MyMapper;
import com.mtgz.xw.api.dao.model.Keywords;

import java.util.List;
import java.util.Map;

public interface KeywordsMapper extends MyMapper<Keywords> {
    List<Map> hotKeywordList(Map param);
}