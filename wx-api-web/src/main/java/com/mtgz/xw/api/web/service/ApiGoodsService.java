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
    private GoodsMapper goodsDao;


    public Goods selectByPrimaryKey(Integer id) {
        return goodsDao.selectByPrimaryKey(id);
    }


    public List<Goods> selectByMap(Map<String, Object> map) {
        return goodsDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return goodsDao.countByMap(map);
    }


    public void save(Goods goods) {
        goodsDao.save(goods);
    }


    public void update(Goods goods) {
        goodsDao.update(goods);
    }


    public void delete(Integer id) {
        goodsDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsDao.deleteBatch(ids);
    }

    public List<Goods> queryHotGoodsList(Map<String, Object> map) {
        return goodsDao.queryHotGoodsList(map);
    }

    public List<Goods> queryCatalogProductList(Map<String, Object> map) {
        return goodsDao.queryCatalogProductList(map);
    }
}
