package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.FeedbackMapper;
import com.mtgz.xw.api.dao.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiFeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;


    public Feedback selectByPrimaryKey(Integer id) {
        return feedbackMapper.selectByPrimaryKey(id);
    }


    public List<Feedback> selectByMap(Map<String, Object> map) {
        return feedbackMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return feedbackMapper.countByMap(map);
    }


    public void save(Feedback feedbackVo) {
        feedbackMapper.insertSelective(feedbackVo);
    }


    public void update(Feedback feedbackVo) {
        feedbackMapper.updateByPrimaryKeySelective(feedbackVo);
    }


    public void delete(Integer id) {
        feedbackMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        feedbackMapper.deleteBatch(ids);
    }

}
