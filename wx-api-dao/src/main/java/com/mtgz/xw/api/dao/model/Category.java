package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String keywords;

    @Column(name = "front_desc")
    private String frontDesc;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "sort_order")
    private Boolean sortOrder;

    @Column(name = "show_index")
    private Boolean showIndex;

    @Column(name = "is_show")
    private Boolean isShow;

    @Column(name = "banner_url")
    private String bannerUrl;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "wap_banner_url")
    private String wapBannerUrl;

    private String level;

    private Integer type;

    @Column(name = "front_name")
    private String frontName;

    private Boolean checked;

    private List<Category> subCategoryList;

}