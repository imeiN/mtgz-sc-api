package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_feedback")
public class Feedback {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 会员Id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 会员会员名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 反馈类型
     */
    @Column(name = "feed_Type")
    private Integer feedType;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 反馈时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 详细内容
     */
    private String content;

}