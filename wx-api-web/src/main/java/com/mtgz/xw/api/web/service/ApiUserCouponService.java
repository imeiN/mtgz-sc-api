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
    private UserCouponMapper userCouponDao;


    public UserCoupon selectByPrimaryKey(Integer id) {
        return userCouponDao.selectByPrimaryKey(id);
    }

    public UserCoupon queryByCouponNumber(String couponNumber) {
        return userCouponDao.queryByCouponNumber(couponNumber);
    }

    public List<UserCoupon> selectByMap(Map<String, Object> map) {
        return userCouponDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return userCouponDao.countByMap(map);
    }


    public void save(UserCoupon goods) {
        userCouponDao.save(goods);
    }


    public void update(UserCoupon goods) {
        userCouponDao.update(goods);
    }


    public void delete(Integer id) {
        userCouponDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        userCouponDao.deleteBatch(ids);
    }

}
