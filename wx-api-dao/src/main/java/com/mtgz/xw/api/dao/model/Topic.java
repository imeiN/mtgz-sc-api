package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_topic")
public class Topic {
    /**
     * 主键
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 活动主题
     */
    private String title;

    /**
     * 化名
     */
    private String avatar;

    /**
     * 活动条例图片
     */
    @Column(name = "item_pic_url")
    private String itemPicUrl;

    /**
     * 子标题
     */
    private String subtitle;

    /**
     * 活动类别
     */
    @Column(name = "topic_category_id")
    private Integer topicCategoryId;

    /**
     * 活动价格
     */
    @Column(name = "price_info")
    private BigDecimal priceInfo;

    @Column(name = "read_count")
    private String readCount;

    /**
     * 场景图片链接
     */
    @Column(name = "scene_pic_url")
    private String scenePicUrl;

    /**
     * 活动模板Id
     */
    @Column(name = "topic_template_id")
    private Integer topicTemplateId;

    /**
     * 活动标签Id
     */
    @Column(name = "topic_tag_id")
    private Integer topicTagId;

    /**
     * 活动内容
     */
    private String content;

}