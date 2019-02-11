package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.CartMapper;
import com.mtgz.xw.api.dao.model.Cart;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ApiCartService {
    @Autowired
    private CartMapper cartMapper;

    public Cart selectByPrimaryKey(Integer id) {
        return cartMapper.selectByPrimaryKey(id);
    }


    public List<Cart> selectByMap(Map<String, Object> map) {
        return cartMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return cartMapper.countByMap(map);
    }


    public void save(Cart cart) {
        cartMapper.insertSelective(cart);
        // 更新购物车搭配减价
        // 判断购物车中是否存在此规格商品
        Map cartParam = new HashMap();
        cartParam.put("user_id", cart.getUserId());
        List<Cart> cartInfoList = cartMapper.selectByMap(cartParam);
        Map crashParam = new HashMap();
        List<Integer> goods_ids = new ArrayList();
        List<Cart> cartUpdateList = new ArrayList();
        for (Cart cartItem : cartInfoList) {
            if (null != cartItem.getChecked() && cartItem.getChecked()) {
                goods_ids.add(cartItem.getGoodsId());
            }
            if (!cartItem.getRetailPrice().equals(cartItem.getRetailProductPrice())) {
                cartItem.setRetailPrice(cartItem.getRetailProductPrice());
                cartUpdateList.add(cartItem);
            }
        }
        crashParam.put("goods_ids", goods_ids);
        for (Cart cartItem : cartInfoList) {
            // 存在原始的
            if (null != cartItem.getChecked() && cartItem.getChecked()) {
                for (Cart cartCrash : cartInfoList) {
                    if (!cartCrash.getId().equals(cartItem.getId())) {
                        cartUpdateList.add(cartItem);
                    }
                }
            }
        }
        if (null != cartUpdateList && cartUpdateList.size() > 0) {
            for (Cart cartItem : cartUpdateList) {
                cartMapper.updateByPrimaryKey(cartItem);
            }
        }
    }

    public void update(Cart cart) {
        cartMapper.updateByPrimaryKeySelective(cart);
    }


    public void delete(Integer id) {
        cartMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        cartMapper.deleteBatch(ids);
    }

    public void updateCheck(String[] productIds, Integer isChecked, Long userId) {
        cartMapper.updateCheck(productIds, isChecked, userId);

        // 判断购物车中是否存在此规格商品
        Map cartParam = new HashMap();
        cartParam.put("user_id", userId);
        List<Cart> cartInfoList = cartMapper.selectByMap(cartParam);
        Map crashParam = new HashMap();
        List<Integer> goods_ids = new ArrayList();
        List<Cart> cartUpdateList = new ArrayList();
        for (Cart cartItem : cartInfoList) {
            if (null != cartItem.getChecked() && cartItem.getChecked()) {
                goods_ids.add(cartItem.getGoodsId());
            }
            if (!cartItem.getRetailPrice().equals(cartItem.getRetailProductPrice())) {
                cartItem.setRetailPrice(cartItem.getRetailProductPrice());
                cartUpdateList.add(cartItem);
            }
        }
        if (null != goods_ids && goods_ids.size() > 0) {
            crashParam.put("goods_ids", goods_ids);
            for (Cart cartItem : cartInfoList) {
                // 存在原始的
                if (null != cartItem.getChecked() && cartItem.getChecked()) {
                    for (Cart cartCrash : cartInfoList) {
                        if (null != cartItem.getChecked() && cartItem.getChecked() && !cartCrash.getId().equals(cartItem.getId())) {
                            cartUpdateList.add(cartCrash);
                            break;
                        }
                    }
                }
            }
        }
        if (null != cartUpdateList && cartUpdateList.size() > 0) {
            for (Cart cartItem : cartUpdateList) {
                cartMapper.updateByPrimaryKey(cartItem);
            }
        }
    }

    public void deleteByProductIds(String[] productIds) {
        cartMapper.deleteByProductIds(productIds);
    }

    public void deleteByUserAndProductIds(Long userId, String[] productIds) {
        cartMapper.deleteByUserAndProductIds(userId, productIds);
    }

    public void deleteByCart(Long user_id, Integer session_id, Integer checked) {
        cartMapper.deleteByCart(user_id, session_id, checked);
    }

}
