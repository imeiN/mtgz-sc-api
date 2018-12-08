package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_order_goods")
public class OrderGoods {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单Id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

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
     * 商品数量
     */
    private Integer number;

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
     * 虚拟商品
     */
    @Column(name = "is_real")
    private Boolean isReal;

    /**
     * 商品规格Ids
     */
    @Column(name = "goods_specifition_ids")
    private String goodsSpecifitionIds;

    /**
     * 图片链接
     */
    @Column(name = "list_pic_url")
    private String listPicUrl;

    /**
     * 商品规格详情
     */
    @Column(name = "goods_specifition_name_value")
    private String goodsSpecifitionNameValue;

}