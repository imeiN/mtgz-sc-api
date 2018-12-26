package com.mtgz.xw.api.dao.mapper;

import com.mtgz.xw.api.dao.config.MyMapper;
import com.mtgz.xw.api.dao.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderMapper extends MyMapper<Order> {
    Order selectWithShopCodeByPrimaryKey(Integer id);

}