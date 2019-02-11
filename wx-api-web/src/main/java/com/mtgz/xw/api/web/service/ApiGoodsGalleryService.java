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
    private GoodsGalleryMapper goodsGalleryMapper;


    public GoodsGallery selectByPrimaryKey(Integer id) {
        return goodsGalleryMapper.selectByPrimaryKey(id);
    }


    public List<GoodsGallery> selectByMap(Map<String, Object> map) {
        return goodsGalleryMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return goodsGalleryMapper.countByMap(map);
    }


    public void save(GoodsGallery goods) {
        goodsGalleryMapper.insertSelective(goods);
    }


    public void update(GoodsGallery goods) {
        goodsGalleryMapper.updateByPrimaryKeySelective(goods);
    }


    public void delete(Integer id) {
        goodsGalleryMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsGalleryMapper.deleteBatch(ids);
    }

}
