package com.mtgz.xw.api.dao.mapper;

import com.mtgz.xw.api.dao.config.MyMapper;
import com.mtgz.xw.api.dao.model.Cart;
import org.apache.ibatis.annotations.Param;

public interface CartMapper extends MyMapper<Cart> {
    void updateCheck(@Param("productIds") String[] productIds,
                     @Param("isChecked") Integer isChecked, @Param("userId") Long userId);

    void deleteByProductIds(@Param("productIds") String[] productIds);

    void deleteByUserAndProductIds(@Param("user_id") Long user_id,@Param("productIds") String[] productIds);

    void deleteByCart(@Param("user_id") Long user_id, @Param("session_id") Integer session_id, @Param("checked") Integer checked);
}