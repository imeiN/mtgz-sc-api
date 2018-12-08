package com.mtgz.xw.api.client;

import com.mtgz.xw.api.common.AppConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by linyisheng on 2018/11/19.
 */
@FeignClient(value = AppConstants.SERVICE_NAME)
public interface TestClient {

    @RequestMapping(path = AppConstants.BASE_PATH + "/users", method = RequestMethod.POST)
    String addUsers();

}
