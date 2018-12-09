package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_product")
public class Product {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品Id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 商品规格ids
     */
    @Column(name = "goods_specification_ids")
    private String goodsSpecificationIds;

    /**
     * 商品序列号
     */
    @Column(name = "goods_sn")
    private String goodsSn;

    /**
     * 商品编码
     */
    @Column(name = "goods_number")
    private Integer goodsNumber;

    /**
     * 零售价格
     */
    @Column(name = "retail_price")
    private BigDecimal retailPrice;

    /**
     * 价格
     */
    @Column(name = "market_price")
    private BigDecimal marketPrice;

    //商品名称
    @Transient
    private String goodsName;

    //商品图片
    @Transient
    private String listPicUrl;


}