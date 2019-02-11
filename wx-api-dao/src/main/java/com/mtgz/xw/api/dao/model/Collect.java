package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_collect")
public class Collect {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 产品Id
     */
    @Column(name = "value_id")
    private Integer valueId;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Long addTime;

    /**
     * 是否提醒
     */
    @Column(name = "is_attention")
    private Boolean isAttention;

    @Column(name = "type_id")
    private Integer typeId;

    @Transient
    private Object obj;

}