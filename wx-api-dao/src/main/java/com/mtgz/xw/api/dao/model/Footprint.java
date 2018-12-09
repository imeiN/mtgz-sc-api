package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_footprint")
public class Footprint {
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
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 记录时间
     */
    @Column(name = "add_time")
    private Long addTime;

    /**
     * 转发人
     */
    private Long referrer;

    @Transient
    private String name;
    @Transient
    private String list_pic_url;
    @Transient
    private String goods_brief;
    //
    @Transient
    private BigDecimal retail_price;
    // 会员
    @Transient
    private String nickname;
    @Transient
    private String avatar;


}