//package com.mtgz.xw.api.web.components.swagger;
//
//import com.mtgz.xw.api.common.AppConstants;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by linyisheng on 2018/11/19.
// */
//@Component
//@Primary
//@AllArgsConstructor
//public class SwaggerProvider implements SwaggerResourcesProvider {
//
//    @Override
//    public List<SwaggerResource> get() {
//        List<SwaggerResource> resources = new ArrayList<>();
//        resources.add(this.swaggerResource(AppConstants.SERVICE_NAME, AppConstants.BASE_PATH + "/users"));
//        return resources;
//    }
//
//    private SwaggerResource swaggerResource(String name, String location) {
//        SwaggerResource swaggerResource = new SwaggerResource();
//        swaggerResource.setName(name);
//        swaggerResource.setLocation(location);
//        swaggerResource.setSwaggerVersion("1.0");
//        return swaggerResource;
//    }
//}