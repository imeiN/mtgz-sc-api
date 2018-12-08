package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_keywords")
public class Keywords {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 热销
     */
    @Column(name = "is_hot")
    private Boolean isHot;

    /**
     * 默认
     */
    @Column(name = "is_default")
    private Boolean isDefault;

    /**
     * 显示
     */
    @Column(name = "is_show")
    private Boolean isShow;

    /**
     * 排序
     */
    @Column(name = "sort_order")
    private Integer sortOrder;

    /**
     * 关键词的跳转链接
     */
    @Column(name = "scheme_url")
    private String schemeUrl;

    /**
     * 类型
     */
    private Integer type;

}