package com.mtgz.xw.api.dao.mapper;

import com.mtgz.xw.api.dao.config.MyMapper;
import com.mtgz.xw.api.dao.model.SearchHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SearchHistoryMapper extends MyMapper<SearchHistory> {
    int deleteByUserId(@Param("user_id") Long userId);
}