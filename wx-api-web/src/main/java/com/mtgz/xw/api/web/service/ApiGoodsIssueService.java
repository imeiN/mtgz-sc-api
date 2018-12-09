package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.GoodsIssueMapper;
import com.mtgz.xw.api.dao.model.GoodsIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiGoodsIssueService {
    @Autowired
    private GoodsIssueMapper goodsIssueDao;


    public GoodsIssue selectByPrimaryKey(Integer id) {
        return goodsIssueDao.selectByPrimaryKey(id);
    }


    public List<GoodsIssue> selectByMap(Map<String, Object> map) {
        return goodsIssueDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return goodsIssueDao.countByMap(map);
    }


    public void save(GoodsIssue goods) {
        goodsIssueDao.insertSelective(goods);
    }


    public void update(GoodsIssue goods) {
        goodsIssueDao.updateByPrimaryKey(goods);
    }


    public void delete(Integer id) {
        goodsIssueDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsIssueDao.deleteBatch(ids);
    }

}
