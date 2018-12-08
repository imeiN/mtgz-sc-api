package com.mtgz.xw.api.dao.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@Slf4j
@Configuration
@EnableTransactionManagement
@ImportResource(value = "classpath:/mybatis.xml")
@MapperScan(basePackages = "com.mtgz.xw.api.dao.mapper")
public class MybatisConfig {
}