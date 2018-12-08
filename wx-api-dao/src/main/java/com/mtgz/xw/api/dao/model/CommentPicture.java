package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_comment_picture")
public class CommentPicture {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评价Id
     */
    @Column(name = "comment_id")
    private Integer commentId;

    /**
     * 评价图片
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 排序
     */
    @Column(name = "sort_order")
    private Integer sortOrder;

}