package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_attribute")
public class Attribute {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品类型
     */
    @Column(name = "attribute_category_id")
    private Integer attributeCategoryId;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 当添加商品时,该属性的添加类别; 0为手功输入;1为选择输入;2为多行文本输入
     */
    @Column(name = "input_type")
    private Boolean inputType;

    @Column(name = "sort_order")
    private Byte sortOrder;

    /**
     * 即选择输入,则attr_name对应的值的取值就是该这字段值 
     */
    private String value;


}