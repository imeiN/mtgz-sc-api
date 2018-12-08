package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.HelpTypeMapper;
import com.mtgz.xw.api.dao.model.HelpType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-11-07 11:04:20
 */
@Service
public class ApiHelpTypeService {
    @Autowired
    private HelpTypeMapper helpTypeDao;

    public HelpType selectByPrimaryKey(Integer id) {
        return helpTypeDao.selectByPrimaryKey(id);
    }

    public List<HelpType> selectByMap(Map<String, Object> map) {
        return helpTypeDao.selectByMap(map);
    }

    public int countByMap(Map<String, Object> map) {
        return helpTypeDao.countByMap(map);
    }

    public int save(HelpType helpType) {
        return helpTypeDao.save(helpType);
    }

    public int update(HelpType helpType) {
        return helpTypeDao.update(helpType);
    }

    public int delete(Integer id) {
        return helpTypeDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return helpTypeDao.deleteBatch(ids);
    }
}
