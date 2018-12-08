package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.OrderGoodsMapper;
import com.mtgz.xw.api.dao.model.OrderGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiOrderGoodsService {
    @Autowired
    private OrderGoodsMapper orderGoodsDao;


    public OrderGoods selectByPrimaryKey(Integer id) {
        return orderGoodsDao.selectByPrimaryKey(id);
    }


    public List<OrderGoods> selectByMap(Map<String, Object> map) {
        return orderGoodsDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return orderGoodsDao.countByMap(map);
    }


    public void save(OrderGoods order) {
        orderGoodsDao.save(order);
    }


    public void update(OrderGoods order) {
        orderGoodsDao.update(order);
    }


    public void delete(Integer id) {
        orderGoodsDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        orderGoodsDao.deleteBatch(ids);
    }

}
