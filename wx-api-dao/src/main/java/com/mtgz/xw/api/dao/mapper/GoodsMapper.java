package com.mtgz.xw.api.dao.mapper;

import com.mtgz.xw.api.dao.config.MyMapper;
import com.mtgz.xw.api.dao.model.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsMapper extends MyMapper<Goods> {
    List<Goods> queryHotGoodsList(Map<String, Object> params);

    List<Goods> queryCatalogProductList(Map<String, Object> params);
}