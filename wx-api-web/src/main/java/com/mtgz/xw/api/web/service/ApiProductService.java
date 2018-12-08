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
    private ProductMapper productDao;


    public Product selectByPrimaryKey(Integer id) {
        return productDao.selectById(id);
    }


    public List<Product> selectByMap(Map<String, Object> map) {
        return productDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return productDao.countByMap(map);
    }


    public void save(Product goods) {
        productDao.save(goods);
    }


    public void update(Product goods) {
        productDao.update(goods);
    }


    public void delete(Integer id) {
        productDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        productDao.deleteBatch(ids);
    }

}
