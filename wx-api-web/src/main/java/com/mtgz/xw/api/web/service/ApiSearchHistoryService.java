package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.SearchHistoryMapper;
import com.mtgz.xw.api.dao.model.SearchHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiSearchHistoryService {
    @Autowired
    private SearchHistoryMapper searchHistoryMapper;


    public SearchHistory selectByPrimaryKey(Integer id) {
        return searchHistoryMapper.selectByPrimaryKey(id);
    }


    public List<SearchHistory> selectByMap(Map<String, Object> map) {
        return searchHistoryMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return searchHistoryMapper.countByMap(map);
    }


    public void save(SearchHistory region) {
        searchHistoryMapper.insertSelective(region);
    }


    public void update(SearchHistory region) {
        searchHistoryMapper.update(region);
    }


    public void delete(Integer id) {
        searchHistoryMapper.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        searchHistoryMapper.deleteBatch(ids);
    }

    public void deleteByUserId(Long userId) {
        searchHistoryMapper.deleteByUserId(userId);
    }
}
