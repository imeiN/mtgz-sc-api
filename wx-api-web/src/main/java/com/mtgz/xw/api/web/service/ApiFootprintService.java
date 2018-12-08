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
    private FootprintMapper footprintDao;


    public Footprint selectByPrimaryKey(Integer id) {
        return footprintDao.selectByPrimaryKey(id);
    }


    public List<Footprint> selectByMap(Map<String, Object> map) {
        return footprintDao.selectBy(map);
    }
    public List<Footprint> selectByMapFootprint(String userid) {
    	return footprintDao.selectByMapFootprint(userid);
    }

    public List<Footprint> shareList(Map<String, Object> map) {
        return footprintDao.shareList(map);
    }

    public int countByMap(Map<String, Object> map) {
        return footprintDao.countByMap(map);
    }


    public void save(Footprint footprint) {
        footprintDao.insert(footprint);
    }


    public void update(Footprint footprint) {
        footprintDao.updateByPrimaryKey(footprint);
    }


    public void delete(Integer id) {
        footprintDao.deleteByPrimaryKey(id);
    }

    public void deleteByParam(Map<String, Object> map) {
        footprintDao.deleteByParam(map);
    }

    public void deleteBatch(Integer[] ids) {
        footprintDao.deleteBatch(ids);
    }

}
