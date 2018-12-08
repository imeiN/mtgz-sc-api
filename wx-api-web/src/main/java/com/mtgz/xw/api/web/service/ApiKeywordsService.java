package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.KeywordsMapper;
import com.mtgz.xw.api.dao.model.Keywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiKeywordsService {
    @Autowired
    private KeywordsMapper keywordsDao;


    public Keywords selectByPrimaryKey(Integer id) {
        return keywordsDao.selectByPrimaryKey(id);
    }


    public List<Keywords> selectByMap(Map<String, Object> map) {
        return keywordsDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return keywordsDao.countByMap(map);
    }


    public void save(Keywords goods) {
        keywordsDao.save(goods);
    }


    public void update(Keywords goods) {
        keywordsDao.update(goods);
    }


    public void delete(Integer id) {
        keywordsDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        keywordsDao.deleteBatch(ids);
    }

    public List<Map> hotKeywordList(Map<String, Object> map) {
        return keywordsDao.hotKeywordList(map);
    }
}
