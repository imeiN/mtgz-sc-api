package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.UserLevelMapper;
import com.mtgz.xw.api.dao.mapper.UserMapper;
import com.mtgz.xw.api.dao.model.SmsLog;
import com.mtgz.xw.api.dao.model.User;
import com.mtgz.xw.api.dao.model.UserLevel;
import com.mtgz.common.service.common.exp.RRException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ApiUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserLevelMapper userLevelMapper;

    public User selectByPrimaryKey(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public User queryByOpenId(String openId) {
        return userMapper.queryByOpenId(openId);
    }

    public List<User> selectByMap(Map<String, Object> map) {
        return userMapper.selectByMap(map);
    }

    public int countByMap(Map<String, Object> map) {
        return userMapper.countByMap(map);
    }

    public void save(String mobile, String password) {
        User user = new User();
        user.setMobile(mobile);
        user.setUsername(mobile);
        user.setPassword(DigestUtils.sha256Hex(password));
        user.setRegisterTime(new Date());
        userMapper.save(user);
    }

    public void save(User userVo) {
        userMapper.save(userVo);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void delete(Long userId) {
        userMapper.delete(userId);
    }

    public void deleteBatch(Long[] userIds) {
        userMapper.deleteBatch(userIds);
    }

    public User queryByMobile(String mobile) {
        return userMapper.queryByMobile(mobile);
    }

    public long login(String mobile, String password) {
        User user = queryByMobile(mobile);
        Assert.isNull(user, "手机号或密码错误");

        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            throw new RRException("手机号或密码错误");
        }

        return user.getId();
    }

    public SmsLog querySmsCodeByUserId(Long user_id) {
        return userMapper.querySmsCodeByUserId(user_id);
    }


    public int saveSmsCodeLog(SmsLog smsLogVo) {
        return userMapper.saveSmsCodeLog(smsLogVo);
    }

    public String getUserLevel(User loginUser) {
        String result = "普通用户";
        UserLevel userLevelVo = userLevelMapper.selectByPrimaryKey(loginUser.getUserLevelId());
        if (null != userLevelVo) {
            result = userLevelVo.getName();
        }
        return result;
    }
}
