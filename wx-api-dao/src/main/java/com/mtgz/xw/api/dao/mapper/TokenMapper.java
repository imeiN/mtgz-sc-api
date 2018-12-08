package com.mtgz.xw.api.dao.mapper;

import com.mtgz.xw.api.dao.config.MyMapper;
import com.mtgz.xw.api.dao.model.Token;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TokenMapper extends MyMapper<Token> {
    Token queryByUserId(@Param("userId") Long userId);

    Token queryByToken(@Param("token") String token);

    List<Token> selectBy(Map<String, Object> map);
}