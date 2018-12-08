package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_brand")
public class Brand {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 图片
     */
    @Column(name = "list_pic_url")
    private String listPicUrl;

    /**
     * 描述
     */
    @Column(name = "simple_desc")
    private String simpleDesc;

    /**
     * 图片
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 排序
     */
    @Column(name = "sort_order")
    private Byte sortOrder;

    /**
     * 显示
     */
    @Column(name = "is_show")
    private Boolean isShow;

    @Column(name = "floor_price")
    private BigDecimal floorPrice;

    /**
     * app显示图片
     */
    @Column(name = "app_list_pic_url")
    private String appListPicUrl;

    /**
     * 新品牌
     */
    @Column(name = "is_new")
    private Boolean isNew;

    /**
     * 图片
     */
    @Column(name = "new_pic_url")
    private String newPicUrl;

    /**
     * 排序
     */
    @Column(name = "new_sort_order")
    private Byte newSortOrder;


}