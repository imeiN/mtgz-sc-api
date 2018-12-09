package com.mtgz.xw.api.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by linyisheng on 2018/12/9.
 */
@Component
@ConfigurationProperties(prefix = "wx")
@Data
public class WxConfig {

    private String appId;
    //小程序密钥
    private String secret;
    //商户号
    private String mchId;
    //支付签名
    private String paySignKey;
    //交易类型
    private String tradeType;
    //证书名称，对应不同的商户号
    private String certName;
    //支付回调地址
    private String notifyUrl;
    //获取code的请求地址
    private String getCode;
    //获取Web_access_tokenhttps的请求地址
    private String webAccessTokenhttps;
    //拉取用户信息的请求地址
    private String userMessage;
    //微信统一下单接口路径
    private String uniformorder;
    //退款地址
    private  String refundUrl;
    //退款查询地址
    private String refundqueryUrl;
    //微信查询订单状态
    private String orderquery;
}
