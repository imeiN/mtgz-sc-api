package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_sn")
    private String orderSn;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "shipping_status")
    private Integer shippingStatus;

    @Column(name = "pay_status")
    private Integer payStatus;

    private String consignee;

    private String country;

    private String province;

    private String city;

    private String district;

    private String address;

    private String mobile;

    private String postscript;

    @Column(name = "shipping_id")
    private Byte shippingId;

    @Column(name = "shipping_name")
    private String shippingName;

    @Column(name = "pay_id")
    private String payId;

    @Column(name = "pay_name")
    private String payName;

    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;

    /**
     * 实际需要支付的金额
     */
    @Column(name = "actual_price")
    private BigDecimal actualPrice;

    private Integer integral;

    @Column(name = "integral_money")
    private BigDecimal integralMoney;

    /**
     * 订单总价
     */
    @Column(name = "order_price")
    private BigDecimal orderPrice;

    /**
     * 商品总价
     */
    @Column(name = "goods_price")
    private BigDecimal goodsPrice;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "confirm_time")
    private Date confirmTime;

    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 配送费用
     */
    @Column(name = "freight_price")
    private Integer freightPrice;

    /**
     * 使用的优惠券id
     */
    @Column(name = "coupon_id")
    private Integer couponId;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "coupon_price")
    private BigDecimal couponPrice;

    @Column(name = "callback_status")
    private String callbackStatus;

    @Column(name = "shipping_no")
    private String shippingNo;

    @Column(name = "full_cut_price")
    private BigDecimal fullCutPrice;

    @Column(name = "order_type")
    private String orderType;


    private Integer goodsCount; //订单的商品
    private String order_status_text;//订单状态的处理
    private Map handleOption; //可操作的选项
    private BigDecimal full_cut_price; //订单满减
    private String full_region;//区县
    private String order_type; // 订单状态
    //快递公司code
    private String shipping_code;

}