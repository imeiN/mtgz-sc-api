package com.mtgz.xw.api.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.dao.model.Collect;
import com.mtgz.xw.api.dao.model.Goods;
import com.mtgz.xw.api.dao.model.User;
import com.mtgz.xw.api.web.annotation.LoginUser;
import com.mtgz.xw.api.web.service.ApiCollectService;
import com.mtgz.xw.api.web.service.ApiGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "用户收藏")
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/collect")
public class ApiCollectController extends ApiBaseAction {
    @Autowired
    private ApiCollectService collectService;

    @Autowired
    private ApiGoodsService apiGoodsService;

    /**
     * 获取用户收藏
     */
    @ApiOperation(value = "获取用户收藏")
    @PostMapping("list")
    public Object list(@LoginUser User loginUser, Integer typeId) {

        Map param = new HashMap();
        param.put("user_id", loginUser.getId());
        param.put("type_id", typeId);
        List<Collect> collectEntities = collectService.selectByMap(param);

        collectEntities = Optional.ofNullable(collectEntities)
                .orElse(new ArrayList<>())
                .stream()
                .map(collect -> {
                    if (collect.getTypeId() == 0) {
                        Goods goods = apiGoodsService.selectByPrimaryKey(collect.getValueId());
                        collect.setObj(goods);
                    } else {
                        // do nothing
                    }
                    return collect;
                }).collect(Collectors.toList());

        return toResponsSuccess(collectEntities);
    }

    /**
     * 获取用户收藏
     */
    @ApiOperation(value = "添加取消收藏")
    @PostMapping("addordelete")
    public Object addordelete(@LoginUser User loginUser) {
        JSONObject jsonParam = getJsonRequest();
        Integer typeId = jsonParam.getInteger("typeId");
        Integer valueId = jsonParam.getInteger("valueId");

        Map param = new HashMap();
        param.put("user_id", loginUser.getId());
        param.put("type_id", typeId);
        param.put("value_id", valueId);
        List<Collect> collectEntities = collectService.selectByMap(param);
        //
        Integer collectRes = null;
        String handleType = "add";
        if (null == collectEntities || collectEntities.size() < 1) {
            Collect collectEntity = new Collect();
            collectEntity.setAddTime(System.currentTimeMillis() / 1000);
            collectEntity.setTypeId(typeId);
            collectEntity.setValueId(valueId);
            collectEntity.setIsAttention(false);
            collectEntity.setUserId(loginUser.getId());
            //添加收藏
            collectRes = collectService.save(collectEntity);
        } else {
            //取消收藏
            collectRes = collectService.delete(collectEntities.get(0).getId());
            handleType = "delete";
        }

        if (collectRes > 0) {
            Map data = new HashMap();
            data.put("type", handleType);
            return toResponsSuccess(data);
        }
        return toResponsFail("操作失败");
    }
}