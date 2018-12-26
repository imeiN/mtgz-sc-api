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
    private HelpIssueMapper helpIssueMapper;

    public HelpIssue selectByPrimaryKey(Integer id) {
        return helpIssueMapper.selectByPrimaryKey(id);
    }

    public List<HelpIssue> selectByMap(Map<String, Object> map) {
        return helpIssueMapper.selectByMap(map);
    }

    public int countByMap(Map<String, Object> map) {
        return helpIssueMapper.countByMap(map);
    }

    public int save(HelpIssue helpIssue) {
        return helpIssueMapper.save(helpIssue);
    }

    public int update(HelpIssue helpIssue) {
        return helpIssueMapper.update(helpIssue);
    }

    public int delete(Integer id) {
        return helpIssueMapper.delete(id);
    }

    public int deleteBatch(Integer[] ids) {
        return helpIssueMapper.deleteBatch(ids);
    }
}
