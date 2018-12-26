package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.CommentPictureMapper;
import com.mtgz.xw.api.dao.model.CommentPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiCommentPictureService {
    @Autowired
    private CommentPictureMapper commentPictureMapper;


    public CommentPicture selectByPrimaryKey(Integer id) {
        return commentPictureMapper.selectByPrimaryKey(id);
    }


    public List<CommentPicture> selectByMap(Map<String, Object> map) {
        return commentPictureMapper.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return commentPictureMapper.countByMap(map);
    }

    public int save(CommentPicture comment) {
        return commentPictureMapper.insertSelective(comment);
    }


    public void update(CommentPicture comment) {
        commentPictureMapper.updateByPrimaryKey(comment);
    }


    public void delete(Integer id) {
        commentPictureMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        commentPictureMapper.deleteBatch(ids);
    }

}
