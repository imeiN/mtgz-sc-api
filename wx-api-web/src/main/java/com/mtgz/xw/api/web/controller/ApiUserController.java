package com.mtgz.xw.api.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mtgz.common.service.client.SysClient;
import com.mtgz.common.service.common.CommonAppConstants;
import com.mtgz.common.service.common.entity.SmsConfig;
import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.dao.model.SmsLog;
import com.mtgz.xw.api.dao.model.User;
import com.mtgz.xw.api.web.annotation.LoginUser;
import com.mtgz.xw.api.web.service.ApiUserService;
import com.mtgz.common.service.common.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "会员验证")
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/user")
public class ApiUserController extends ApiBaseAction {
    @Autowired
    private ApiUserService userService;
    @Autowired
    private SysClient sysClient;

    /**
     * 发送短信
     */
    @ApiOperation(value = "发送短信")
    @PostMapping("smscode")
    public Object smscode(@LoginUser User loginUser) {
        JSONObject jsonParams = getJsonRequest();
        String phone = jsonParams.getString("phone");
        // 一分钟之内不能重复发送短信
        SmsLog smsLogVo = userService.querySmsCodeByUserId(loginUser.getId());
        if (null != smsLogVo && (System.currentTimeMillis() / 1000 - smsLogVo.getLog_date()) < 1 * 60) {
            return toResponsFail("短信已发送");
        }
        //生成验证码
        String sms_code = CharUtil.getRandomNum(4);
        String msgContent = "您的验证码是：" + sms_code + "，请在页面中提交验证码完成验证。";
        // 发送短信
        String result = "";
        //获取云存储配置信息
        SmsConfig config = sysClient.getConfigObject(Constant.SMS_CONFIG_KEY, SmsConfig.class);
        if (StringUtils.isNullOrEmpty(config)) {
            return toResponsFail("请先配置短信平台信息");
        }
        if (StringUtils.isNullOrEmpty(config.getName())) {
            return toResponsFail("请先配置短信平台用户名");
        }
        if (StringUtils.isNullOrEmpty(config.getPwd())) {
            return toResponsFail("请先配置短信平台密钥");
        }
        if (StringUtils.isNullOrEmpty(config.getSign())) {
            return toResponsFail("请先配置短信平台签名");
        }
        try {
            /**
             * 状态,发送编号,无效号码数,成功提交数,黑名单数和消息，无论发送的号码是多少，一个发送请求只返回一个sendid，如果响应的状态不是“0”，则只有状态和消息
             */
            result = SmsUtil.crSendSms(config.getName(), config.getPwd(), phone, msgContent, config.getSign(),
                    DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"), "");
        } catch (Exception e) {

        }
        String arr[] = result.split(",");

        if ("0".equals(arr[0])) {
            smsLogVo = new SmsLog();
            smsLogVo.setLog_date(System.currentTimeMillis() / 1000);
            smsLogVo.setUserId(loginUser.getId());
            smsLogVo.setMobile(phone);
            smsLogVo.setContent(sms_code);
            smsLogVo.setContent(msgContent);
            userService.saveSmsCodeLog(smsLogVo);
            return toResponsSuccess("短信发送成功");
        } else {
            return toResponsFail("短信发送失败");
        }
    }

    /**
     * 获取当前会员等级
     *
     * @param loginUser
     * @return
     */
    @ApiOperation(value = "获取当前会员等级")
    @PostMapping("getUserLevel")
    public Object getUserLevel(@LoginUser User loginUser) {
        String userLevel = userService.getUserLevel(loginUser);
        return toResponsSuccess(userLevel);
    }

    /**
     * 绑定手机
     */
    @ApiOperation(value = "绑定手机")
    @PostMapping("bindMobile")
    public Object bindMobile(@LoginUser User loginUser) {
        JSONObject jsonParams = getJsonRequest();
        SmsLog smsLogVo = userService.querySmsCodeByUserId(loginUser.getId());

        String mobile_code = jsonParams.getString("mobile_code");
        String mobile = jsonParams.getString("mobile");

        if (!mobile_code.equals(smsLogVo.getContent())) {
            return toResponsFail("验证码错误");
        }
        User userVo = userService.selectByPrimaryKey(loginUser.getId());
        userVo.setMobile(mobile);
        userService.update(userVo);
        return toResponsSuccess("手机绑定成功");
    }
}