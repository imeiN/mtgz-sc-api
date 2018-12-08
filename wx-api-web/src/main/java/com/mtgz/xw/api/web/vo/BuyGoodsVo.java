package com.mtgz.xw.api.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyGoodsVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer goodsId;
    private Integer productId;
    private Integer number;
    private String name;

}
