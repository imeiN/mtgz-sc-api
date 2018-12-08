package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.HelpIssueMapper;
import com.mtgz.xw.api.dao.model.HelpIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-11-07 11:04:20
 */
@Service
public class ApiHelpIssueService {
    @Autowired
    private HelpIssueMapper helpIssueDao;

    public HelpIssue selectByPrimaryKey(Integer id) {
        return helpIssueDao.selectByPrimaryKey(id);
    }

    public List<HelpIssue> selectByMap(Map<String, Object> map) {
        return helpIssueDao.selectByMap(map);
    }

    public int countByMap(Map<String, Object> map) {
        return helpIssueDao.countByMap(map);
    }

    public int save(HelpIssue helpIssue) {
        return helpIssueDao.save(helpIssue);
    }

    public int update(HelpIssue helpIssue) {
        return helpIssueDao.update(helpIssue);
    }

    public int delete(Integer id) {
        return helpIssueDao.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return helpIssueDao.deleteBatch(ids);
    }
}
