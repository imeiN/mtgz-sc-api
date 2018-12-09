package com.mtgz.xw.api.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by linyisheng on 2018/12/9.
 */
@Component
@ConfigurationProperties(prefix = "sms")
@Data
public class SmsConfig {

    private String validIp;

}
