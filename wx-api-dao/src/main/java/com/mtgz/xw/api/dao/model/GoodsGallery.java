package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_goods_gallery")
public class GoodsGallery {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 描述
     */
    @Column(name = "img_desc")
    private String imgDesc;

    /**
     * 排序
     */
    @Column(name = "sort_order")
    private Integer sortOrder;

    /**
     * 图片
     */
    @Column(name = "img_url")
    private String imgUrl;

}