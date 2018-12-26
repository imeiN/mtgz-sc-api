package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.TokenMapper;
import com.mtgz.xw.api.dao.model.Token;
import com.mtgz.common.service.common.util.CharUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class TokenService {
    @Autowired
    private TokenMapper tokenMapper;
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    public Token queryByUserId(Long userId) {
        return tokenMapper.queryByUserId(userId);
    }

    public Token queryByToken(String token) {
        return tokenMapper.queryByToken(token);
    }

    public void save(Token token) {
        tokenMapper.save(token);
    }

    public void update(Token token) {
        tokenMapper.update(token);
    }

    public Map<String, Object> createToken(long userId) {
        //生成一个token
        String token = CharUtil.getRandomString(32);
        //当前时间
        Date now = new Date();

        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        Token tokenEntity = queryByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new Token();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            update(tokenEntity);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", EXPIRE);

        return map;
    }
}
