package com.mtgz.xw.api.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.mtgz.common.service.client.CacheClient;
import com.mtgz.common.service.common.util.CharUtil;
import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.dao.model.*;
import com.mtgz.xw.api.web.annotation.LoginUser;
import com.mtgz.xw.api.web.service.*;
import com.mtgz.xw.api.web.vo.BuyGoodsVo;
import com.mtgz.xw.api.web.vo.SmsLogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * API优惠券管理
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:31
 */
@Api(tags = "优惠券")
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/coupon")
public class ApiCouponController extends ApiBaseAction {
    @Autowired
    private ApiUserService apiUserService;
    @Autowired
    private ApiCouponService apiCouponService;
    @Autowired
    private ApiUserCouponService apiUserCouponService;
    @Autowired
    private ApiProductService apiProductService;
    @Autowired
    private ApiCartService apiCartService;

    @Autowired
    CacheClient cacheClient;

    /**
     * 获取优惠券列表
     */
    @ApiOperation(value = "获取优惠券列表")
    @PostMapping("/list")
    public Object list(@LoginUser User loginUser) {
        Map param = new HashMap();
        param.put("user_id", loginUser.getId());
        List<Coupon> couponVos = apiCouponService.queryUserCoupons(param);
        return toResponsSuccess(couponVos);
    }

    /**
     * 根据商品获取可用优惠券列表
     */
    @ApiOperation(value = "根据商品获取可用优惠券列表")
    @PostMapping("/listByGoods")
    public Object listByGoods(@RequestParam(defaultValue = "cart") String type, @LoginUser User loginUser) {
        BigDecimal goodsTotalPrice = new BigDecimal(0.00);
        if (type.equals("cart")) {
            Map param = new HashMap();
            param.put("user_id", loginUser.getId());
            List<Cart> cartList = apiCartService.selectByMap(param);
            //获取购物车统计信息
            for (Cart cartItem : cartList) {
                if (null != cartItem.getChecked() && cartItem.getChecked()) {
                    goodsTotalPrice = goodsTotalPrice.add(cartItem.getRetailPrice().multiply(new BigDecimal(cartItem.getNumber())));
                }
            }
        } else { // 是直接购买的
            BuyGoodsVo goodsVo = (BuyGoodsVo) cacheClient.get(AppConstants.SHOP_CACHE_NAME, "goods" + loginUser.getId() + "");
            Product productInfo = apiProductService.selectByPrimaryKey(goodsVo.getProductId());
            //商品总价
            goodsTotalPrice = productInfo.getRetailPrice().multiply(new BigDecimal(goodsVo.getNumber()));
        }

        // 获取可用优惠券
        Map param = new HashMap();
        param.put("user_id", loginUser.getId());
        param.put("coupon_status", 1);
        List<Coupon> couponVos = apiCouponService.queryUserCoupons(param);
        List<Coupon> useCoupons = new ArrayList<>();
        List<Coupon> notUseCoupons = new ArrayList<>();
        for (Coupon couponVo : couponVos) {
            if (goodsTotalPrice.compareTo(couponVo.getMinGoodsAmount()) >= 0) { // 可用优惠券
                couponVo.setEnabled(1);
                useCoupons.add(couponVo);
            } else {
                couponVo.setEnabled(0);
                notUseCoupons.add(couponVo);
            }
        }
        useCoupons.addAll(notUseCoupons);
        return toResponsSuccess(useCoupons);
    }

    /**
     * 兑换优惠券
     */
    @ApiOperation(value = "领券优惠券")
    @PostMapping("exchange")
    public Object exchange(@LoginUser User loginUser) {
        JSONObject jsonParam = getJsonRequest();
        String coupon_number = jsonParam.getString("coupon_number");
        if (StringUtils.isBlank(coupon_number)) {
            return toResponsFail("当前优惠码无效");
        }
        //
        Map param = new HashMap();
        param.put("coupon_number", coupon_number);
        List<UserCoupon> couponVos = apiUserCouponService.selectByMap(param);
        UserCoupon userCoupon = null;
        if (null == couponVos || couponVos.size() == 0) {
            return toResponsFail("当前优惠码无效");
        }
        userCoupon = couponVos.get(0);
        if (null != userCoupon.getUserId() && !userCoupon.getId().equals(0L)) {
            return toResponsFail("当前优惠码已经兑换");
        }
        Coupon couponVo = apiCouponService.selectByPrimaryKey(userCoupon.getCouponId());
        if (null == couponVo || null == couponVo.getUseEndDate() || couponVo.getUseEndDate().before(new Date())) {
            return toResponsFail("当前优惠码已经过期");
        }
        userCoupon.setUserId(loginUser.getId());
        userCoupon.setAddTime(new Date());
        apiUserCouponService.update(userCoupon);
        return toResponsSuccess(userCoupon);
    }

    /**
     * 　　填写手机号码，领券
     */
    @ApiOperation(value = "领券优惠券")
    @PostMapping("newuser")
    public Object newuser(@LoginUser User loginUser) {
        JSONObject jsonParam = getJsonRequest();
        //
        String phone = jsonParam.getString("phone");
        String smscode = jsonParam.getString("smscode");
        // 校验短信码
        SmsLog smsLogVo = apiUserService.querySmsCodeByUserId(loginUser.getId());
        if (null != smsLogVo && !smsLogVo.getContent().equals(smscode)) {
            return toResponsFail("短信校验失败");
        }
        // 更新手机号码
        if (!StringUtils.isBlank(phone)) {
            if (phone.equals(loginUser.getMobile())) {
                loginUser.setMobile(phone);
                apiUserService.update(loginUser);
            }
        }
        // 判断是否是新用户
        if (!StringUtils.isBlank(loginUser.getMobile())) {
            return toResponsFail("当前优惠券只能新用户领取");
        }
        // 是否领取过了
        Map params = new HashMap();
        params.put("user_id", loginUser.getId());
        params.put("send_type", 4);
        List<Coupon> couponVos = apiCouponService.queryUserCoupons(params);
        if (null != couponVos && couponVos.size() > 0) {
            return toResponsFail("已经领取过，不能重复领取");
        }
        // 领取
        Map couponParam = new HashMap();
        couponParam.put("send_type", 4);
        Coupon newCouponConfig = apiCouponService.queryMaxUserEnableCoupon(couponParam);
        if (null != newCouponConfig) {
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setAddTime(new Date());
            userCoupon.setCouponId(newCouponConfig.getId());
            userCoupon.setCouponNumber(CharUtil.getRandomString(12));
            userCoupon.setUserId(loginUser.getId());
            apiUserCouponService.save(userCoupon);
            return toResponsSuccess(userCoupon);
        } else {
            return toResponsFail("领取失败");
        }
    }

    /**
     * 　　转发领取红包
     */
    @ApiOperation(value = "转发领取红包")
    @PostMapping("transActivit")
    public Object transActivit(@LoginUser User loginUser, String sourceKey, Integer referrer) {
        JSONObject jsonParam = getJsonRequest();
        // 是否领取过了
        Map params = new HashMap();
        params.put("user_id", loginUser.getId());
        params.put("send_type", 2);
        params.put("source_key", sourceKey);
        List<Coupon> couponVos = apiCouponService.queryUserCoupons(params);
        if (null != couponVos && couponVos.size() > 0) {
            return toResponsObject(2, "已经领取过", couponVos);
        }
        // 领取
        Map couponParam = new HashMap();
        couponParam.put("send_type", 2);
        Coupon newCouponConfig = apiCouponService.queryMaxUserEnableCoupon(couponParam);
        if (null != newCouponConfig) {
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setAddTime(new Date());
            userCoupon.setCouponId(newCouponConfig.getId());
            userCoupon.setCouponNumber(CharUtil.getRandomString(12));
            userCoupon.setUserId(loginUser.getId());
            userCoupon.setSourceKey(sourceKey);
            userCoupon.setReferrer(referrer);
            apiUserCouponService.save(userCoupon);
            //
            List<UserCoupon> userCoupons = new ArrayList();
            userCoupons.add(userCoupon);
            //
            params = new HashMap();
            params.put("user_id", loginUser.getId());
            params.put("send_type", 2);
            params.put("source_key", sourceKey);
            couponVos = apiCouponService.queryUserCoupons(params);
            return toResponsSuccess(couponVos);
        } else {
            return toResponsFail("领取失败");
        }
    }
}
