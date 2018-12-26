package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.ProductMapper;
import com.mtgz.xw.api.dao.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiProductService {
    @Autowired
    private ProductMapper productMapper;


    public Product selectByPrimaryKey(Integer id) {
        return productMapper.selectById(id);
    }


    public List<Product> selectByMap(Map<String, Object> map) {
        return productMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return productMapper.countByMap(map);
    }


    public void save(Product goods) {
        productMapper.save(goods);
    }


    public void update(Product goods) {
        productMapper.update(goods);
    }


    public void delete(Integer id) {
        productMapper.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        productMapper.deleteBatch(ids);
    }

}
