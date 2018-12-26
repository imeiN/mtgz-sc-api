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
    private HelpTypeMapper helpTypeMapper;

    public HelpType selectByPrimaryKey(Integer id) {
        return helpTypeMapper.selectByPrimaryKey(id);
    }

    public List<HelpType> selectByMap(Map<String, Object> map) {
        return helpTypeMapper.selectByMap(map);
    }

    public int countByMap(Map<String, Object> map) {
        return helpTypeMapper.countByMap(map);
    }

    public int save(HelpType helpType) {
        return helpTypeMapper.save(helpType);
    }

    public int update(HelpType helpType) {
        return helpTypeMapper.update(helpType);
    }

    public int delete(Integer id) {
        return helpTypeMapper.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return helpTypeMapper.deleteBatch(ids);
    }
}
