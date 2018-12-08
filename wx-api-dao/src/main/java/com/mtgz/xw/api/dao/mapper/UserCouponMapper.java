package com.mtgz.xw.api.dao.mapper;

import com.mtgz.xw.api.dao.config.MyMapper;
import com.mtgz.xw.api.dao.model.UserCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserCouponMapper extends MyMapper<UserCoupon> {
    UserCoupon queryByCouponNumber(@Param("coupon_number") String coupon_number);

    List<UserCoupon> selectBy(Map<String, Object> map);
}