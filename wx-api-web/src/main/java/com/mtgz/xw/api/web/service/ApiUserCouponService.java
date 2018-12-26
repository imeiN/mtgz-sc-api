package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.UserCouponMapper;
import com.mtgz.xw.api.dao.model.UserCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiUserCouponService {
    @Autowired
    private UserCouponMapper userCouponMapper;


    public UserCoupon selectByPrimaryKey(Integer id) {
        return userCouponMapper.selectByPrimaryKey(id);
    }

    public UserCoupon queryByCouponNumber(String couponNumber) {
        return userCouponMapper.queryByCouponNumber(couponNumber);
    }

    public List<UserCoupon> selectByMap(Map<String, Object> map) {
        return userCouponMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return userCouponMapper.countByMap(map);
    }


    public void save(UserCoupon goods) {
        userCouponMapper.save(goods);
    }


    public void update(UserCoupon goods) {
        userCouponMapper.update(goods);
    }


    public void delete(Integer id) {
        userCouponMapper.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        userCouponMapper.deleteBatch(ids);
    }

}
