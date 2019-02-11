package com.mtgz.xw.api.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.dao.model.Address;
import com.mtgz.xw.api.dao.model.User;
import com.mtgz.xw.api.web.annotation.LoginUser;
import com.mtgz.xw.api.web.service.ApiAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "收货地址")
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/address")
public class ApiAddressController extends ApiBaseAction {
    @Autowired
    private ApiAddressService addressService;

    /**
     * 获取用户的收货地址
     */
    @ApiOperation(value = "获取用户的收货地址接口", response = Map.class)
    @PostMapping("list")
    public Object list(@LoginUser User loginUser) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("user_id", loginUser.getId());
        List<Address> addressEntities = addressService.selectByMap(param);
        return toResponsSuccess(addressEntities);
    }

    /**
     * 获取收货地址的详情
     */
    @ApiOperation(value = "获取收货地址的详情", response = Map.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "收获地址ID", required = true, dataType = "Integer")})
    @PostMapping("detail")
    public Object detail(Integer id, @LoginUser User loginUser) {
        Address entity = addressService.selectByPrimaryKey(id);
        //判断越权行为
        if (!entity.getUserId().equals(loginUser.getId())) {
            return toResponsObject(403, "您无权查看", "");
        }
        return toResponsSuccess(entity);
    }

    /**
     * 添加或更新收货地址
     */
    @ApiOperation(value = "添加或更新收货地址", response = Map.class)
    @PostMapping("save")
    public Object save(@LoginUser User loginUser) {
        JSONObject addressJson = this.getJsonRequest();
        Address entity = new Address();
        if (null != addressJson) {
            entity.setId(addressJson.getInteger("id"));
            entity.setUserId(loginUser.getId());
            if (addressJson.containsKey("userName")) {
                entity.setUserName(addressJson.getString("userName"));
            }
            if (addressJson.containsKey("postalCode")) {
                entity.setPostalCode(addressJson.getString("postalCode"));
            }
            if (addressJson.containsKey("provinceName")) {
                entity.setProvinceName(addressJson.getString("provinceName"));
            }
            if (addressJson.containsKey("cityName")) {
                entity.setCityName(addressJson.getString("cityName"));
            }
            if (addressJson.containsKey("countyName")) {
                entity.setCountyName(addressJson.getString("countyName"));
            }
            if (addressJson.containsKey("detailInfo")) {
                entity.setDetailInfo(addressJson.getString("detailInfo"));
            }
            if (addressJson.containsKey("nationalCode")) {
                entity.setNationalCode(addressJson.getString("nationalCode"));
            }
            if (addressJson.containsKey("telNumber")) {
                entity.setTelNumber(addressJson.getString("telNumber"));
            }
            if (addressJson.containsKey("isDefault")) {
                entity.setIsDefault(addressJson.getInteger("isDefault"));
            }
        }
        if (null == entity.getId() || entity.getId() == 0) {
            entity.setId(null);
            addressService.save(entity);
        } else {
            addressService.update(entity);
        }
        return toResponsSuccess(entity);
    }

    /**
     * 删除指定的收货地址
     */
    @ApiOperation(value = "删除指定的收货地址", response = Map.class)
    @PostMapping("delete")
    public Object delete(@LoginUser User loginUser) {
        JSONObject jsonParam = this.getJsonRequest();
        Integer id = jsonParam.getIntValue("id");

        Address entity = addressService.selectByPrimaryKey(id);
        //判断越权行为
        if (!entity.getUserId().equals(loginUser.getId())) {
            return toResponsObject(403, "您无权删除", "");
        }
        addressService.delete(id);
        return toResponsSuccess("");
    }
}