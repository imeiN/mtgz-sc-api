package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.GoodsSpecificationMapper;
import com.mtgz.xw.api.dao.model.GoodsSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiGoodsSpecificationService {
    @Autowired
    private GoodsSpecificationMapper goodsMapper;


    public GoodsSpecification selectByPrimaryKey(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }


    public List<GoodsSpecification> selectByMap(Map<String, Object> map) {
        return goodsMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return goodsMapper.countByMap(map);
    }


    public void save(GoodsSpecification goods) {
        goodsMapper.save(goods);
    }


    public void update(GoodsSpecification goods) {
        goodsMapper.update(goods);
    }


    public void delete(Integer id) {
        goodsMapper.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsMapper.deleteBatch(ids);
    }

}
