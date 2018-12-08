package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.CouponMapper;
import com.mtgz.xw.api.dao.model.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class ApiCouponService {
    @Autowired
    private CouponMapper apiCouponMapper;

    public Coupon selectByPrimaryKey(Integer couponId) {
        return apiCouponMapper.selectByPrimaryKey(couponId);
    }

    public List<Coupon> selectByMap(Map<String, Object> map) {
        return apiCouponMapper.selectByMap(map);
    }

    public int countByMap(Map<String, Object> map) {
        return apiCouponMapper.countByMap(map);
    }


    public void save(Coupon userVo) {
        apiCouponMapper.insert(userVo);
    }

    public void update(Coupon user) {
        apiCouponMapper.updateByPrimaryKey(user);
    }

    public void delete(Long userId) {
        apiCouponMapper.deleteByPrimaryKey(userId);
    }

    public void deleteBatch(Long[] userIds) {
        apiCouponMapper.deleteBatch(userIds);
    }

    public List<Coupon> queryUserCoupons(Map<String, Object> map) {
        // 检查优惠券是否过期
        List<Coupon> couponVos = apiCouponMapper.queryUserCoupons(map);
        for (Coupon couponVo : couponVos) {
            if (couponVo.getCouponStatus()==1) {
                // 检查是否过期
                if(couponVo.getUseEndDate().before(new Date())) {
                    couponVo.setCouponStatus(3);
                    apiCouponMapper.updateUserCoupon(couponVo);
                }
            }
            if (couponVo.getCouponStatus()==3) {
                // 检查是否不过期
                if(couponVo.getUseEndDate().after(new Date())) {
                    couponVo.setCouponStatus(1);
                    apiCouponMapper.updateUserCoupon(couponVo);
                }
            }
        }

        return couponVos;
    }

    public Coupon queryMaxUserEnableCoupon(Map<String, Object> map) {
        return apiCouponMapper.queryMaxUserEnableCoupon(map);
    }

    public List<Coupon> queryUserCouponList(Map<String, Object> map) {
        return apiCouponMapper.queryUserCouponList(map);
    }
}
