package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_cart")
public class Cart {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 会员Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * sessionId
     */
    @Column(name = "session_id")
    private String sessionId;

    /**
     * 商品Id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 商品序列号
     */
    @Column(name = "goods_sn")
    private String goodsSn;

    /**
     * 产品Id
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 产品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 市场价
     */
    @Column(name = "market_price")
    private BigDecimal marketPrice;

    /**
     * 零售价格
     */
    @Column(name = "retail_price")
    private BigDecimal retailPrice;

    /**
     * 数量
     */
    private Integer number;

    /**
     * product表对应的goods_specifition_ids
     */
    @Column(name = "goods_specifition_ids")
    private String goodsSpecifitionIds;

    private Boolean checked;

    /**
     * 商品图片
     */
    @Column(name = "list_pic_url")
    private String listPicUrl;

    /**
     * 规格属性组成的字符串，用来显示用
     */
    @Column(name = "goods_specifition_name_value")
    private String goodsSpecifitionNameValue;

    //product表中的零售价格
    @Transient
    private BigDecimal retailProductPrice;

}