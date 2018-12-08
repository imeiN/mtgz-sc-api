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
    private GoodsSpecificationMapper goodsDao;


    public GoodsSpecification selectByPrimaryKey(Integer id) {
        return goodsDao.selectByPrimaryKey(id);
    }


    public List<GoodsSpecification> selectByMap(Map<String, Object> map) {
        return goodsDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return goodsDao.countByMap(map);
    }


    public void save(GoodsSpecification goods) {
        goodsDao.save(goods);
    }


    public void update(GoodsSpecification goods) {
        goodsDao.update(goods);
    }


    public void delete(Integer id) {
        goodsDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsDao.deleteBatch(ids);
    }

}
