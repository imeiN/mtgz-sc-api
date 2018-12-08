package com.mtgz.xw.api.dao.mapper;

import com.mtgz.xw.api.dao.config.MyMapper;
import com.mtgz.xw.api.dao.model.Coupon;

import java.util.List;
import java.util.Map;

public interface CouponMapper extends MyMapper<Coupon> {
    List<Coupon> queryUserCoupons(Map<String, Object> map);

    /**
     * 按条件查询用户优惠券
     *
     * @param params
     * @return
     */
    Coupon getUserCoupon(Integer id);

    /**
     * 按类型查询
     *
     * @param params
     * @return
     */
    Coupon queryMaxUserEnableCoupon(Map<String, Object> params);

    /**
     * sendType = 1或4 的优惠券
     *
     * @param params
     * @return
     */
    List<Coupon> queryUserCouponList(Map<String, Object> params);

    int updateUserCoupon(Coupon couponVo);
}