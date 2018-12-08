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
    private TopicMapper topicDao;


    public Topic selectByPrimaryKey(Integer id) {
        return topicDao.selectByPrimaryKey(id);
    }


    public List<Topic> selectByMap(Map<String, Object> map) {
        return topicDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return topicDao.countByMap(map);
    }


    public void save(Topic topic) {
        topicDao.save(topic);
    }


    public void update(Topic topic) {
        topicDao.update(topic);
    }


    public void delete(Integer id) {
        topicDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        topicDao.deleteBatch(ids);
    }

}
