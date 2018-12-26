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
    private KeywordsMapper keywordsMapper;


    public Keywords selectByPrimaryKey(Integer id) {
        return keywordsMapper.selectByPrimaryKey(id);
    }


    public List<Keywords> selectByMap(Map<String, Object> map) {
        return keywordsMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return keywordsMapper.countByMap(map);
    }


    public void save(Keywords goods) {
        keywordsMapper.save(goods);
    }


    public void update(Keywords goods) {
        keywordsMapper.update(goods);
    }


    public void delete(Integer id) {
        keywordsMapper.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        keywordsMapper.deleteBatch(ids);
    }

    public List<Map> hotKeywordList(Map<String, Object> map) {
        return keywordsMapper.hotKeywordList(map);
    }
}
