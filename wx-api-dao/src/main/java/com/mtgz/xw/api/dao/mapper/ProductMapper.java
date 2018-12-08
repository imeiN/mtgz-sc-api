package com.mtgz.xw.api.dao.mapper;

import com.mtgz.xw.api.dao.config.MyMapper;
import com.mtgz.xw.api.dao.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductMapper extends MyMapper<Product> {
    Product selectById(Integer id);

    List<Product> selectBy(Map<String, Object> map);
}