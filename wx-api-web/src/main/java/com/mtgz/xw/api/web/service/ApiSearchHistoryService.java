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
    private SearchHistoryMapper searchHistoryDao;


    public SearchHistory selectByPrimaryKey(Integer id) {
        return searchHistoryDao.selectByPrimaryKey(id);
    }


    public List<SearchHistory> selectByMap(Map<String, Object> map) {
        return searchHistoryDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return searchHistoryDao.countByMap(map);
    }


    public void save(SearchHistory region) {
        searchHistoryDao.save(region);
    }


    public void update(SearchHistory region) {
        searchHistoryDao.update(region);
    }


    public void delete(Integer id) {
        searchHistoryDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        searchHistoryDao.deleteBatch(ids);
    }

    public void deleteByUserId(Long userId) {
        searchHistoryDao.deleteByUserId(userId);
    }
}
