package com.mtgz.xw.api.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nideshop_ad")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "ad_position_id")
    private Short adPositionId;

    @Column(name = "media_type")
    private Byte mediaType;

    private String name;

    private String link;

    private String content;

    @Column(name = "end_time")
    private Date endTime;

    private Byte enabled;

    @Column(name = "image_url")
    private String imageUrl;

}