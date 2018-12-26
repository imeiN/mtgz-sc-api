package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.GoodsMapper;
import com.mtgz.xw.api.dao.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;


    public Goods selectByPrimaryKey(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }


    public List<Goods> selectByMap(Map<String, Object> map) {
        return goodsMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return goodsMapper.countByMap(map);
    }


    public void save(Goods goods) {
        goodsMapper.save(goods);
    }


    public void update(Goods goods) {
        goodsMapper.update(goods);
    }


    public void delete(Integer id) {
        goodsMapper.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsMapper.deleteBatch(ids);
    }

    public List<Goods> queryHotGoodsList(Map<String, Object> map) {
        return goodsMapper.queryHotGoodsList(map);
    }

    public List<Goods> queryCatalogProductList(Map<String, Object> map) {
        return goodsMapper.queryCatalogProductList(map);
    }
}
