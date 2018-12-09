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
    private CommentPictureMapper commentPictureDao;


    public CommentPicture selectByPrimaryKey(Integer id) {
        return commentPictureDao.selectByPrimaryKey(id);
    }


    public List<CommentPicture> selectByMap(Map<String, Object> map) {
        return commentPictureDao.selectByMap(map);
    }


    public int countByMap(Map<String, Object> map) {
        return commentPictureDao.countByMap(map);
    }

    public int save(CommentPicture comment) {
        return commentPictureDao.insertSelective(comment);
    }


    public void update(CommentPicture comment) {
        commentPictureDao.updateByPrimaryKey(comment);
    }


    public void delete(Integer id) {
        commentPictureDao.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        commentPictureDao.deleteBatch(ids);
    }

}
