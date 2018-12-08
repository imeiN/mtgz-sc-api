package com.mtgz.xw.api.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mtgz.common.service.client.CacheClient;
import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.dao.model.User;
import com.mtgz.xw.api.web.annotation.LoginUser;
import com.mtgz.xw.api.web.vo.BuyGoodsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "商品购买")
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/buy")
public class ApiBuyController extends ApiBaseAction {

    @Autowired
    CacheClient cacheClient;

    @ApiOperation(value = "商品添加")
    @PostMapping("/add")
    public Object addBuy(@LoginUser User loginUser) {
        JSONObject jsonParam = getJsonRequest();
        Integer goodsId = jsonParam.getInteger("goodsId");
        Integer productId = jsonParam.getInteger("productId");
        Integer number = jsonParam.getInteger("number");
        BuyGoodsVo goodsVo = new BuyGoodsVo();
        goodsVo.setGoodsId(goodsId);
        goodsVo.setProductId(productId);
        goodsVo.setNumber(number);
        cacheClient.add(AppConstants.SHOP_CACHE_NAME, "goods" + loginUser.getId() + "", goodsVo);
        return toResponsMsgSuccess("添加成功");
    }
}
