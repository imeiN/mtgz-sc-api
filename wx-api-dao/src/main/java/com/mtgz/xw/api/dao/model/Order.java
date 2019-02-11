package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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


    @Transient
    private Integer goodsCount; //订单的商品
    @Transient
    private String orderStatusText;//订单状态的处理
//    @Transient
//    private Map handleOption; //可操作的选项
    @Transient
    private BigDecimal full_cut_price; //订单满减
    @Transient
    private String fullRegion;//区县
    @Transient
    private String order_type; // 订单状态
    //快递公司code
    @Transient
    private String shipping_code;

    private List<OrderGoods> goodsList;


    Map<String, Boolean> handleOption;


    public Map getHandleOption() {
        handleOption = new HashMap();
        handleOption.put("cancel", false);//取消操作
        handleOption.put("delete", false);//删除操作
        handleOption.put("pay", false);//支付操作
        handleOption.put("comment", false);//评论操作
        handleOption.put("delivery", false);//确认收货操作
        handleOption.put("confirm", false);//完成订单操作
        handleOption.put("return", false); //退换货操作
        handleOption.put("buy", false); //再次购买

        //订单流程：　下单成功－》支付订单－》发货－》收货－》评论
        //订单相关状态字段设计，采用单个字段表示全部的订单状态
        //1xx 表示订单取消和删除等状态 0订单创建成功等待付款，　101订单已取消，　102订单已删除
        //2xx 表示订单支付状态　201订单已付款，等待发货
        //3xx 表示订单物流相关状态　300订单已发货， 301用户确认收货
        //4xx 表示订单退换货相关的状态　401 没有发货，退款　402 已收货，退款退货

        //如果订单已经取消或是已完成，则可删除和再次购买
        if (orderStatus == 101) {
//            handleOption.put("delete", true);
            handleOption.put("buy", true);
        }

        //如果订单没有被取消，且没有支付，则可支付，可取消
        if (orderStatus == 0) {
            handleOption.put("cancel", true);
            handleOption.put("pay", true);
        }

        //如果订单已付款，没有发货，则可退款操作
        if (orderStatus == 201) {
            handleOption.put("cancel", true);
        }

        //如果订单已经发货，没有收货，则可收货操作和退款、退货操作
        if (orderStatus == 300) {
//            handleOption.put("cancel", true);
            handleOption.put("confirm", true);
//            handleOption.put("return", true);
        }

        //如果订单已经支付，且已经收货，则可完成交易、评论和再次购买
        if (orderStatus == 301) {
            handleOption.put("comment", true);
            handleOption.put("buy", true);
        }
        return handleOption;
    }

    public String getOrderStatusText() {
        if (null != orderStatus && StringUtils.isEmpty(orderStatusText)) {
            orderStatusText = "未付款";
            switch (orderStatus) {
                case 0:
                    orderStatusText = "未付款";
                    break;
                case 201:
                    orderStatusText = "等待发货";
                    break;
                case 300:
                    orderStatusText = "待收货";
                    break;
                case 301:
                    orderStatusText = "已完成";
                    break;
                case 200:
                    orderStatusText = "已付款";
                    break;
                case 101:
                    orderStatusText = "已取消";
                    break;
                case 401:
                    orderStatusText = "已取消";
                    break;
                case 402:
                    orderStatusText = "已退货";
                    break;
            }
        }
        return orderStatusText;
    }

    public String getFullRegion() {
        //    return full_region;
        if (StringUtils.isNotEmpty(this.fullRegion)){
            return fullRegion;
        } else{
            StringBuffer strBuff = new StringBuffer();
            if (StringUtils.isNotEmpty(this.country)){
                strBuff.append(this.country).append(" ");
            }
            if(StringUtils.isNotEmpty(this.province)){
                strBuff.append(this.province).append(" ");
            }
            if (StringUtils.isNotEmpty(this.city)){
                strBuff.append(this.city).append(" ");
            }
            if (StringUtils.isNotEmpty(this.district)){
                strBuff.append(this.district).append(" ");
            }
            this.fullRegion = strBuff.toString();
            return this.fullRegion;
        }
    }


}