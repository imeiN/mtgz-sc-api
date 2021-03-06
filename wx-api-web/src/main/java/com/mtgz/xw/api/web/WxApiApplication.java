package com.mtgz.xw.api.web;

import com.mtgz.xw.api.web.resolver.LoginUserHandlerMethodArgumentResolver;
import com.mtgz.xw.api.web.service.ApiUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.mtgz", "tech.fullink"})
@Slf4j
@EnableFeignClients(value = {"com.mtgz", "tech.fullink"})
@EnableDiscoveryClient
@EnableScheduling
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class WxApiApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(WxApiApplication.class, args);
	}

	@Autowired
	ApiUserService apiUserService;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		super.addArgumentResolvers(argumentResolvers);

		LoginUserHandlerMethodArgumentResolver resolver = new LoginUserHandlerMethodArgumentResolver();
		resolver.setUserService(apiUserService);
		argumentResolvers.add(resolver);
	}
}