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
    private CommentMapper commentDao;


    public Comment selectByPrimaryKey(Integer id) {
        return commentDao.selectByPrimaryKey(id);
    }


    public List<Comment> selectByMap(Map<String, Object> map) {
        return commentDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return commentDao.countByMap(map);
    }

    public int queryhasPicTotal(Map<String, Object> map) {
        return commentDao.queryhasPicTotal(map);
    }

    public int save(Comment comment) {
        return commentDao.insert(comment);
    }


    public void update(Comment comment) {
        commentDao.updateByPrimaryKey(comment);
    }


    public void delete(Integer id) {
        commentDao.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        commentDao.deleteBatch(ids);
    }

}
