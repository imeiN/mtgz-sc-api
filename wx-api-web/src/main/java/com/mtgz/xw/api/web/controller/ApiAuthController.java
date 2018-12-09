package com.mtgz.xw.api.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.dao.model.User;
import com.mtgz.xw.api.web.annotation.IgnoreAuth;
import com.mtgz.xw.api.web.config.WxConfig;
import com.mtgz.xw.api.web.service.ApiUserService;
import com.mtgz.xw.api.web.service.TokenService;
import com.mtgz.common.service.common.util.*;
import com.mtgz.xw.api.web.vo.FullUserInfo;
import com.mtgz.xw.api.web.vo.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.collections.MapUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * API登录授权
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:31
 */
@Slf4j
@Api(tags = "API登录授权接口")
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/auth")
public class ApiAuthController extends ApiBaseAction {
    
    @Autowired
    private ApiUserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private WxConfig wxConfig;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("login")
    @ApiOperation(value = "登录接口")
    public R login(String mobile, String password) {
        Assert.hasText(mobile, "手机号不能为空");
        Assert.hasText(password, "密码不能为空");

        //用户登录
        long userId = userService.login(mobile, password);

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);

        return R.ok(map);
    }

    /**
     * 登录
     */
    @ApiOperation(value = "登录")
    @IgnoreAuth
    @PostMapping("login_by_weixin")
    public Object loginByWeixin() {
        JSONObject jsonParam = this.getJsonRequest();
        String code = jsonParam.getString("code");
        log.info("login code: " + code);

        FullUserInfo fullUserInfo = jsonParam.getObject("userInfo", FullUserInfo.class);

        log.info("log userinfo: " + fullUserInfo);
        if (null == fullUserInfo) {
            return toResponsFail("登录失败");
        }

        Map<String, Object> resultObj = new HashMap<String, Object>();
        //
        UserInfo userInfo = fullUserInfo.getUserInfo();

        //获取openid
        String requestUrl = ApiUserUtils.getWebAccess(code,
                wxConfig.getWebAccessTokenhttps(),
                wxConfig.getAppId(),
                wxConfig.getSecret());//通过自定义工具类组合出小程序需要的登录凭证 code

        log.info("》》》组合token为：" + requestUrl);
        JSONObject sessionData = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null == sessionData || StringUtils.isNullOrEmpty(sessionData.getString("openid"))) {
            return toResponsFail("登录失败");
        }
        //验证用户信息完整性
        String sha1 = CommonUtil.getSha1(fullUserInfo.getRawData() + sessionData.getString("session_key"));
        if (!fullUserInfo.getSignature().equals(sha1)) {
            return toResponsFail("登录失败");
        }
        Date nowTime = new Date();
        User userVo = userService.queryByOpenId(sessionData.getString("openid"));
        if (null == userVo) {
            userVo = new User();
            userVo.setUsername("微信用户" + CharUtil.getRandomString(12));
            userVo.setPassword(sessionData.getString("openid"));
            userVo.setRegisterTime(nowTime);
            userVo.setRegisterIp(this.getClientIp());
            userVo.setLastLoginIp(userVo.getRegisterIp());
            userVo.setLastLoginTime(userVo.getRegisterTime());
            userVo.setWeixinOpenid(sessionData.getString("openid"));
            userVo.setAvatar(userInfo.getAvatarUrl());
            userVo.setGender(userInfo.getGender()); // //性别 0：未知、1：男、2：女
            userVo.setNickname(userInfo.getNickName());
            userService.save(userVo);
        } else {
            userVo.setLastLoginIp(this.getClientIp());
            userVo.setLastLoginTime(nowTime);
            userService.update(userVo);
        }

        Map<String, Object> tokenMap = tokenService.createToken(userVo.getId());
        String token = MapUtils.getString(tokenMap, "token");

        if (null == userInfo || StringUtils.isNullOrEmpty(token)) {
            return toResponsFail("登录失败");
        }

        resultObj.put("token", token);
        resultObj.put("userInfo", userInfo);
        resultObj.put("userId", userVo.getId());
        return toResponsSuccess(resultObj);
    }
}
