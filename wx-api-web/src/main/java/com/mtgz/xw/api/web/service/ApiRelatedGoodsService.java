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
    private RelatedGoodsMapper relatedGoodsDao;


    public RelatedGoods selectByPrimaryKey(Integer id) {
        return relatedGoodsDao.selectByPrimaryKey(id);
    }


    public List<RelatedGoods> selectByMap(Map<String, Object> map) {
        return relatedGoodsDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return relatedGoodsDao.countByMap(map);
    }

    public int queryhasPicTotal(Map<String, Object> map) {
        return relatedGoodsDao.countByMap(map);
    }

    public int save(RelatedGoods comment) {
        return relatedGoodsDao.save(comment);
    }


    public void update(RelatedGoods comment) {
        relatedGoodsDao.update(comment);
    }


    public void delete(Integer id) {
        relatedGoodsDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        relatedGoodsDao.deleteBatch(ids);
    }

}
