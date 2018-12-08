package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 会员ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 收货人姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 手机
     */
    @Column(name = "tel_number")
    private String telNumber;

    /**
     * 邮编
     */
    @Column(name = "postal_Code")
    private String postalCode;

    /**
     * 收货地址国家码
     */
    @Column(name = "national_Code")
    private String nationalCode;

    /**
     * 省
     */
    @Column(name = "province_Name")
    private String provinceName;

    /**
     * 市
     */
    @Column(name = "city_Name")
    private String cityName;

    /**
     * 区
     */
    @Column(name = "county_Name")
    private String countyName;

    /**
     * 详细收货地址信息
     */
    @Column(name = "detail_Info")
    private String detailInfo;

    @Column(name = "is_default")
    private Integer isDefault;

}