package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.GoodsGalleryMapper;
import com.mtgz.xw.api.dao.model.GoodsGallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiGoodsGalleryService {
    @Autowired
    private GoodsGalleryMapper goodsGalleryDao;


    public GoodsGallery selectByPrimaryKey(Integer id) {
        return goodsGalleryDao.selectByPrimaryKey(id);
    }


    public List<GoodsGallery> selectByMap(Map<String, Object> map) {
        return goodsGalleryDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return goodsGalleryDao.countByMap(map);
    }


    public void save(GoodsGallery goods) {
        goodsGalleryDao.insertSelective(goods);
    }


    public void update(GoodsGallery goods) {
        goodsGalleryDao.updateByPrimaryKey(goods);
    }


    public void delete(Integer id) {
        goodsGalleryDao.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsGalleryDao.deleteBatch(ids);
    }

}
