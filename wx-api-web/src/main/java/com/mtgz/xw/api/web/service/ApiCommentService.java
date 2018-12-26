package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.CommentMapper;
import com.mtgz.xw.api.dao.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiCommentService {
    @Autowired
    private CommentMapper commentMapper;


    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }


    public List<Comment> selectByMap(Map<String, Object> map) {
        return commentMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return commentMapper.countByMap(map);
    }

    public int queryhasPicTotal(Map<String, Object> map) {
        return commentMapper.queryhasPicTotal(map);
    }

    public int save(Comment comment) {
        return commentMapper.insertSelective(comment);
    }


    public void update(Comment comment) {
        commentMapper.updateByPrimaryKey(comment);
    }


    public void delete(Integer id) {
        commentMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        commentMapper.deleteBatch(ids);
    }

}
