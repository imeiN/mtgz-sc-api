package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.TopicMapper;
import com.mtgz.xw.api.dao.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiTopicService {
    @Autowired
    private TopicMapper topicMapper;


    public Topic selectByPrimaryKey(Integer id) {
        return topicMapper.selectByPrimaryKey(id);
    }


    public List<Topic> selectByMap(Map<String, Object> map) {
        return topicMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return topicMapper.countByMap(map);
    }


    public void save(Topic topic) {
        topicMapper.save(topic);
    }


    public void update(Topic topic) {
        topicMapper.update(topic);
    }


    public void delete(Integer id) {
        topicMapper.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        topicMapper.deleteBatch(ids);
    }

}
