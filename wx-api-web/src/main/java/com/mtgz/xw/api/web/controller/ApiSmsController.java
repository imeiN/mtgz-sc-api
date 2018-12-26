package com.mtgz.xw.api.web.controller;

import com.mtgz.common.service.client.CommonClient;
import com.mtgz.common.service.common.CommonAppConstants;
import com.mtgz.common.service.common.entity.SysSmsLogEntity;
import com.mtgz.common.service.common.exp.RRException;
import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.web.annotation.IgnoreAuth;
import com.mtgz.common.service.common.util.*;
import com.mtgz.xw.api.web.config.SmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 发送短信接口Controller
 *
 * @author liepngjun
 * @email 939961241@qq.com
 * @date 2018-06-05 13:58:47
 */
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/sms")
public class ApiSmsController {
    @Autowired
    private CommonClient commonClient;
    @Autowired
    private SmsConfig smsConfig;

    /**
     * 发送短信
     *
     * @param request request
     * @param params 请求参数{mobile：电话号码字符串，中间用英文逗号间隔,content：内容字符串,stime：追加发送时间，可为空，为空为及时发送}
     * @return R
     */
    @IgnoreAuth
    @RequestMapping("/sendSms")
    public R sendSms(HttpServletRequest request, @RequestParam Map<String, String> params) {
        SysSmsLogEntity smsLog = new SysSmsLogEntity();
        String validIP = RequestUtil.getIpAddrByRequest(request);
        if (smsConfig.getValidIp().indexOf(validIP) < 0) {
            throw new RRException("非法IP请求！");
        }
        smsLog.setMobile(params.get("mobile"));
        smsLog.setContent(params.get("content"));
        String stime = params.get("stime");
        if (StringUtils.isNotEmpty(stime)) {
            smsLog.setStime(DateUtils.convertStringToDate(stime));
        }
        SysSmsLogEntity sysSmsLogEntity = commonClient.sendSms(smsLog);
        return R.ok().put("result", sysSmsLogEntity);
    }
}
