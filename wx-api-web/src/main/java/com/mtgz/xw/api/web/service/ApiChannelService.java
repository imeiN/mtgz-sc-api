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
    private ChannelMapper channelMapper;


    public Channel selectByPrimaryKey(Integer id) {
        return channelMapper.selectByPrimaryKey(id);
    }


    public List<Channel> selectByMap(Map<String, Object> map) {
        return channelMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return channelMapper.countByMap(map);
    }


    public void save(Channel order) {
        channelMapper.insertSelective(order);
    }


    public void update(Channel order) {
        channelMapper.updateByPrimaryKeySelective(order);
    }


    public void delete(Integer id) {
        channelMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        channelMapper.deleteBatch(ids);
    }

}
