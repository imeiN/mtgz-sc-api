package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.AttributeMapper;
import com.mtgz.xw.api.dao.model.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiAttributeService {
    @Autowired
    private AttributeMapper attributeMapper;


    public Attribute selectByPrimaryKey(Integer id) {
        return attributeMapper.selectByPrimaryKey(id);
    }


    public List<Attribute> selectByMap(Map<String, Object> map) {
        return attributeMapper.selectByMap(map);
    }

    public int countByMap(Map<String, Object> map) {
        return attributeMapper.countByMap(map);
    }


    public void save(Attribute goods) {
        attributeMapper.insertSelective(goods);
    }


    public void update(Attribute goods) {
        attributeMapper.updateByPrimaryKey(goods);
    }


    public void delete(Integer id) {
        attributeMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        attributeMapper.deleteBatch(ids);
    }

}
