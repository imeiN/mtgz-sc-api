//package com.mtgz.xw.api.web.components.swagger;
//
//import com.mtgz.xw.api.web.components.swagger.handler.SwaggerResourceHandler;
//import com.mtgz.xw.api.web.components.swagger.handler.SwaggerSecurityHandler;
//import com.mtgz.xw.api.web.components.swagger.handler.SwaggerUiHandler;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.server.RequestPredicates;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//
///**
// * Created by linyisheng on 2018/11/19.
// */
//@Slf4j
//@Configuration
//@AllArgsConstructor
//public class SwaggerRouterFunction {
//
//    private final SwaggerResourceHandler swaggerResourceHandler;
//    private final SwaggerSecurityHandler swaggerSecurityHandler;
//    private final SwaggerUiHandler swaggerUiHandler;
//
//    @Bean
//    public RouterFunction<?> routerFunction() {
//        return RouterFunctions.route(
//                RequestPredicates.GET("/swagger-resources")
//                        .and(RequestPredicates.accept(MediaType.ALL)),swaggerResourceHandler)
//                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/ui")
//                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerUiHandler)
//                .andRoute(RequestPredicates.GET("/swagger-resources/configuration/security")
//                        .and(RequestPredicates.accept(MediaType.ALL)), swaggerSecurityHandler);
//
//
//    }
//}
