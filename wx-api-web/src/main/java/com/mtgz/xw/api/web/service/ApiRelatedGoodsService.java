package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.RelatedGoodsMapper;
import com.mtgz.xw.api.dao.model.RelatedGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiRelatedGoodsService {
    @Autowired
    private RelatedGoodsMapper relatedGoodsMapper;


    public RelatedGoods selectByPrimaryKey(Integer id) {
        return relatedGoodsMapper.selectByPrimaryKey(id);
    }


    public List<RelatedGoods> selectByMap(Map<String, Object> map) {
        return relatedGoodsMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return relatedGoodsMapper.countByMap(map);
    }

    public int queryhasPicTotal(Map<String, Object> map) {
        return relatedGoodsMapper.countByMap(map);
    }

    public int save(RelatedGoods comment) {
        return relatedGoodsMapper.save(comment);
    }


    public void update(RelatedGoods comment) {
        relatedGoodsMapper.update(comment);
    }


    public void delete(Integer id) {
        relatedGoodsMapper.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        relatedGoodsMapper.deleteBatch(ids);
    }

}
