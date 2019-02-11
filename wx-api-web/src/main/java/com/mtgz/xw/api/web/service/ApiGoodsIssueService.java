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
    private GoodsIssueMapper goodsIssueMapper;


    public GoodsIssue selectByPrimaryKey(Integer id) {
        return goodsIssueMapper.selectByPrimaryKey(id);
    }


    public List<GoodsIssue> selectByMap(Map<String, Object> map) {
        return goodsIssueMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return goodsIssueMapper.countByMap(map);
    }


    public void save(GoodsIssue goods) {
        goodsIssueMapper.insertSelective(goods);
    }


    public void update(GoodsIssue goods) {
        goodsIssueMapper.updateByPrimaryKeySelective(goods);
    }


    public void delete(Integer id) {
        goodsIssueMapper.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        goodsIssueMapper.deleteBatch(ids);
    }

}
