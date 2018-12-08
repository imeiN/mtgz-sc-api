package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.ChannelMapper;
import com.mtgz.xw.api.dao.model.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiChannelService {
    @Autowired
    private ChannelMapper channelDao;


    public Channel selectByPrimaryKey(Integer id) {
        return channelDao.selectByPrimaryKey(id);
    }


    public List<Channel> selectByMap(Map<String, Object> map) {
        return channelDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return channelDao.countByMap(map);
    }


    public void save(Channel order) {
        channelDao.insert(order);
    }


    public void update(Channel order) {
        channelDao.updateByPrimaryKey(order);
    }


    public void delete(Integer id) {
        channelDao.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        channelDao.deleteBatch(ids);
    }

}
