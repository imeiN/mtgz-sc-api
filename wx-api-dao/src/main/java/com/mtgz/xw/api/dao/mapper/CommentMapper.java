package com.mtgz.xw.api.dao.mapper;

import com.mtgz.xw.api.dao.config.MyMapper;
import com.mtgz.xw.api.dao.model.Comment;

import java.util.Map;

public interface CommentMapper extends MyMapper<Comment> {
    int queryhasPicTotal(Map<String, Object> map);
}