package com.mtgz.xw.api.dao.mapper;

import com.mtgz.xw.api.dao.config.MyMapper;
import com.mtgz.xw.api.dao.model.SmsLog;
import com.mtgz.xw.api.dao.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends MyMapper<User> {
    User queryByMobile(@Param("mobile") String mobile);

    User queryByOpenId(@Param("openId") String openId);

    List<User> selectBy(Map<String, Object> map);

    /**
     * 获取用户最后一条短信
     *
     * @param user_id
     * @return
     */
    SmsLog querySmsCodeByUserId(@Param("user_id") Long user_id);

    /**
     * 保存短信
     *
     * @param smsLogVo
     * @return
     */
    int saveSmsCodeLog(SmsLog smsLogVo);
}