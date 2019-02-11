package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_coupon")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "type_money")
    private BigDecimal typeMoney;

    @Column(name = "send_type")
    private Byte sendType;

    @Column(name = "min_amount")
    private BigDecimal minAmount;

    @Column(name = "max_amount")
    private BigDecimal maxAmount;

    @Column(name = "send_start_date")
    private Date sendStartDate;

    @Column(name = "send_end_date")
    private Date sendEndDate;

    @Column(name = "use_start_date")
    private Date useStartDate;

    @Column(name = "use_end_date")
    private Date useEndDate;

    @Column(name = "min_goods_amount")
    private BigDecimal minGoodsAmount;

    /**
     * 转发次数
     */
    @Column(name = "min_transmit_num")
    private Integer minTransmitNum;

    //优惠券状态 1 可用 2 已用 3 过期
    @Transient
    private Integer couponStatus = 1;

    //可用 1:可用 0：不可用
    @Transient
    private Integer enabled = 1;

}