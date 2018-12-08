package com.mtgz.xw.api.web.controller;

import com.mtgz.common.service.common.AppConstants;
import com.mtgz.common.service.common.validator.Assert;
import com.mtgz.xw.api.web.annotation.IgnoreAuth;
import com.mtgz.xw.api.web.service.ApiUserService;
import com.mtgz.common.service.common.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-26 17:27
 */
@Api(tags = "注册")
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/register")
public class ApiRegisterController {
    @Autowired
    private ApiUserService userService;

    /**
     * 注册
     */
    @ApiOperation(value = "注册")
    @IgnoreAuth
    @PostMapping("register")
    public R register(String mobile, String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        userService.save(mobile, password);

        return R.ok();
    }
}
