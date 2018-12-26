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
    private OrderGoodsMapper orderGoodsMapper;


    public OrderGoods selectByPrimaryKey(Integer id) {
        return orderGoodsMapper.selectByPrimaryKey(id);
    }


    public List<OrderGoods> selectByMap(Map<String, Object> map) {
        return orderGoodsMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return orderGoodsMapper.countByMap(map);
    }


    public void save(OrderGoods order) {
        orderGoodsMapper.save(order);
    }


    public void update(OrderGoods order) {
        orderGoodsMapper.update(order);
    }


    public void delete(Integer id) {
        orderGoodsMapper.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        orderGoodsMapper.deleteBatch(ids);
    }

}
