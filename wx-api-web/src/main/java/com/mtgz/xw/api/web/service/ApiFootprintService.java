package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.FootprintMapper;
import com.mtgz.xw.api.dao.model.Footprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiFootprintService {
    @Autowired
    private FootprintMapper footprintMapper;


    public Footprint selectByPrimaryKey(Integer id) {
        return footprintMapper.selectByPrimaryKey(id);
    }


    public List<Footprint> selectByMap(Map<String, Object> map) {
        return footprintMapper.selectByMap(map);
    }
    public List<Footprint> selectByMapFootprint(String userid) {
    	return footprintMapper.selectByMapFootprint(userid);
    }

    public List<Footprint> shareList(Map<String, Object> map) {
        return footprintMapper.shareList(map);
    }

    public int countByMap(Map<String, Object> map) {
        return footprintMapper.countByMap(map);
    }


    public void save(Footprint footprint) {
        footprintMapper.save(footprint);
    }


    public void update(Footprint footprint) {
        footprintMapper.updateByPrimaryKeySelective(footprint);
    }


    public void delete(Integer id) {
        footprintMapper.deleteByPrimaryKey(id);
    }

    public void deleteByParam(Map<String, Object> map) {
        footprintMapper.deleteByParam(map);
    }

    public void deleteBatch(Integer[] ids) {
        footprintMapper.deleteBatch(ids);
    }

}
