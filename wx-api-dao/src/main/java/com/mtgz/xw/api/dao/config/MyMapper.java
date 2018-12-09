package com.mtgz.xw.api.dao.config;

import com.alibaba.fastjson.JSON;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;


public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    default List<T> selectByMap(Map<String, Object> map) {
        return select((T) JSON.parse(JSON.toJSONString(map)));
    }

    default int countByMap(Map<String, Object> map) {
        return selectCount((T) JSON.parse(JSON.toJSONString(map)));
    }

    default int deleteBatch(Object[] ids) {
        int rows = 0;
        if (null != ids && ids.length > 0) {
            for (Object id: ids) {
                rows += deleteByPrimaryKey(id);
            }
        }
        return rows;
    }

    default int delete(Integer id){
        return deleteByPrimaryKey(id);
    }

    default int delete(Long id){
        return deleteByPrimaryKey(id);
    }

    default int save(T t){
        return insertSelective(t);
    }

    default int update(T t){
        return updateByPrimaryKeySelective(t);
    }
}
