//package com.mtgz.xw.api.web.components.swagger.handler;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.server.HandlerFunction;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Mono;
//import springfox.documentation.swagger.web.UiConfiguration;
//
///**
// * Created by linyisheng on 2018/11/19.
// */
//@Component
//public class SwaggerUiHandler implements HandlerFunction {
//
//    @Override
//    public Mono<ServerResponse> handle(ServerRequest request) {
//        return ServerResponse.status(HttpStatus.OK)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .body(BodyInserters.fromObject(
//                        new UiConfiguration(null)));//这个地方如果不是null后果是什么？请大家看看swager-ui-html的右下角就知道了！在springmvc模式的"/"中很好的解释了这一点
//    }
//
//}
