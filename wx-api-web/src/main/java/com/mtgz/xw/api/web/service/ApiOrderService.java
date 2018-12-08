package com.mtgz.xw.api.web.service;

import com.alibaba.fastjson.JSONObject;
import com.mtgz.common.service.client.CacheClient;
import com.mtgz.common.service.common.util.CommonUtil;
import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.dao.mapper.*;
import com.mtgz.xw.api.dao.model.*;
import com.mtgz.xw.api.web.vo.BuyGoodsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


@Service
public class ApiOrderService {
    @Autowired
    private OrderMapper orderDao;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private ApiProductService productService;

    @Autowired
    CacheClient cacheClient;

    public Order selectByPrimaryKey(Integer id) {
        return orderDao.selectWithShopCodeByPrimaryKey(id);
    }


    public List<Order> selectByMap(Map<String, Object> map) {
        return orderDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return orderDao.countByMap(map);
    }


    public void save(Order order) {
        orderDao.save(order);
    }


    public int update(Order order) {
        return orderDao.update(order);
    }


    public void delete(Integer id) {
        orderDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        orderDao.deleteBatch(ids);
    }


    @Transactional
    public Map<String, Object> submit(JSONObject jsonParam, User loginUser) {
        Map<String, Object> resultObj = new HashMap<String, Object>();

        Integer couponId = jsonParam.getInteger("couponId");
        String type = jsonParam.getString("type");
        String postscript = jsonParam.getString("postscript");
//        Address address = jsonParam.getObject("checkedAddress",Address.class);
        Address address = addressMapper.selectByPrimaryKey(jsonParam.getInteger("addressId"));


        Integer freightPrice = 0;

        // * 获取要购买的商品
        List<Cart> checkedGoodsList = new ArrayList<>();
        BigDecimal goodsTotalPrice;
        if (type.equals("cart")) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("user_id", loginUser.getId());
            param.put("session_id", 1);
            param.put("checked", 1);
            checkedGoodsList = cartMapper.selectByMap(param);
            if (null == checkedGoodsList) {
                resultObj.put("errno", 400);
                resultObj.put("errmsg", "请选择商品");
                return resultObj;
            }
            //统计商品总价
            goodsTotalPrice = new BigDecimal(0.00);
            for (Cart cartItem : checkedGoodsList) {
                goodsTotalPrice = goodsTotalPrice.add(cartItem.getRetailPrice().multiply(new BigDecimal(cartItem.getNumber())));
            }
        } else {
            BuyGoodsVo goods = (BuyGoodsVo) cacheClient.get(AppConstants.SHOP_CACHE_NAME, "goods" + loginUser.getId());
            Product productInfo = productService.selectByPrimaryKey(goods.getProductId());
            //计算订单的费用
            //商品总价
            goodsTotalPrice = productInfo.getRetailPrice().multiply(new BigDecimal(goods.getNumber()));

            Cart cart = new Cart();
            BeanUtils.copyProperties(productInfo, cart);
            cart.setNumber(goods.getNumber());
            cart.setProductId(goods.getProductId());
            checkedGoodsList.add(cart);
        }


        //获取订单使用的优惠券
        BigDecimal couponPrice = new BigDecimal(0.00);
        Coupon coupon = null;
        if (couponId != null && couponId != 0) {
            coupon = couponMapper.selectByPrimaryKey(couponId);
            if (coupon != null && coupon.getCouponStatus() == 1) {
                couponPrice = coupon.getTypeMoney();
            }
        }

        //订单价格计算
        BigDecimal orderTotalPrice = goodsTotalPrice.add(new BigDecimal(freightPrice)); //订单的总价

        BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice);  //减去其它支付的金额后，要实际支付的金额

        Long currentTime = System.currentTimeMillis() / 1000;

        //
        Order orderInfo = new Order();
        orderInfo.setOrderSn(CommonUtil.generateOrderNumber());
        orderInfo.setUserId(loginUser.getId());
        //收货地址和运费
        orderInfo.setConsignee(address.getUserName());
        orderInfo.setMobile(address.getTelNumber());
        orderInfo.setCountry(address.getNationalCode());
        orderInfo.setProvince(address.getProvinceName());
        orderInfo.setCity(address.getCityName());
        orderInfo.setDistrict(address.getCountyName());
        orderInfo.setAddress(address.getDetailInfo());
        //
        orderInfo.setFreightPrice(freightPrice);
        //留言
        orderInfo.setPostscript(postscript);
        //使用的优惠券
        orderInfo.setCouponId(couponId);
        orderInfo.setCouponPrice(couponPrice);
        orderInfo.setAddTime(new Date());
        orderInfo.setGoodsPrice(goodsTotalPrice);
        orderInfo.setOrderPrice(orderTotalPrice);
        orderInfo.setActualPrice(actualPrice);
        // 待付款
        orderInfo.setOrderStatus(0);
        orderInfo.setShippingStatus(0);
        orderInfo.setPayStatus(0);
        orderInfo.setShippingId(Byte.valueOf("0"));
        orderInfo.setShippingFee(new BigDecimal(0));
        orderInfo.setIntegral(0);
        orderInfo.setIntegralMoney(new BigDecimal(0));
        if (type.equals("cart")) {
            orderInfo.setOrderType("1");
        } else {
            orderInfo.setOrderType("4");
        }

        //开启事务，插入订单信息和订单商品
        orderMapper.save(orderInfo);
        if (null == orderInfo.getId()) {
            resultObj.put("errno", 1);
            resultObj.put("errmsg", "订单提交失败");
            return resultObj;
        }
        //统计商品总价
        List<OrderGoods> orderGoodsData = new ArrayList<OrderGoods>();
        for (Cart goodsItem : checkedGoodsList) {
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(orderInfo.getId());
            orderGoods.setGoodsId(goodsItem.getGoodsId());
            orderGoods.setGoodsSn(goodsItem.getGoodsSn());
            orderGoods.setProductId(goodsItem.getProductId());
            orderGoods.setGoodsName(goodsItem.getGoodsName());
            orderGoods.setListPicUrl(goodsItem.getListPicUrl());
            orderGoods.setMarketPrice(goodsItem.getMarketPrice());
            orderGoods.setRetailPrice(goodsItem.getRetailPrice());
            orderGoods.setNumber(goodsItem.getNumber());
            orderGoods.setGoodsSpecifitionNameValue(goodsItem.getGoodsSpecifitionNameValue());
            orderGoods.setGoodsSpecifitionIds(goodsItem.getGoodsSpecifitionIds());
            orderGoodsData.add(orderGoods);
            orderGoodsMapper.save(orderGoods);
        }

        //清空已购买的商品
        cartMapper.deleteByCart(loginUser.getId(), 1, 1);
        resultObj.put("errno", 0);
        resultObj.put("errmsg", "订单提交成功");
        //
        Map<String, Order> orderInfoMap = new HashMap<String, Order>();
        orderInfoMap.put("orderInfo", orderInfo);
        //
        resultObj.put("data", orderInfoMap);
        // 优惠券标记已用
        if (coupon != null && coupon.getCouponStatus() == 1) {
            coupon.setCouponStatus(2);
            couponMapper.updateUserCoupon(coupon);
        }

        return resultObj;
    }

}
