package com.mtgz.xw.api.web.components.swagger;

import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.web.components.BeanContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by @author changsheng on 2017/12/18.
 */
@Configuration
@EnableAsync
@EnableSwagger2
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public BeanContextAware beanContextAware() {
        return new BeanContextAware();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/(" + AppConstants.SERVICE_NAME + "/).*"))
                .build()
                .apiInfo(apiInfo())
                .enable(true)
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("framework", "http://www.framework.com/", "framework@qq.com");
        return new ApiInfoBuilder()
                .title("framework")
                .description("framework")
                .termsOfServiceUrl("http://www.framework.com/")
                .contact(contact)
                .version("0.1")
                .build();
    }

    @Bean
    public Executor storeDataAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(128);
        executor.setQueueCapacity(8);
        executor.setThreadNamePrefix("Async-Thread");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
