package com.mtgz.xw.api.web.controller;

import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.dao.model.HelpIssue;
import com.mtgz.xw.api.dao.model.HelpType;
import com.mtgz.xw.api.web.annotation.IgnoreAuth;
import com.mtgz.xw.api.web.service.ApiHelpIssueService;
import com.mtgz.xw.api.web.service.ApiHelpTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2018-11-07 11:04:20
 */
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/helpissue")
public class ApiHelpIssueController extends ApiBaseAction {
    @Autowired
    private ApiHelpIssueService helpIssueService;
    @Autowired
    private ApiHelpTypeService helpTypeService;

    /**
     * 查看帮助类型列表
     */
    @RequestMapping("/typeList")
    @IgnoreAuth
    public Object typeList() {

        List<HelpType> list = helpTypeService.selectByMap(new HashMap());

        return toResponsSuccess(list);
    }

    /**
     * 查看问题列表
     */
    @RequestMapping("/issueList")
    @IgnoreAuth
    public Object issueList(Long type_id) {

        Map params = new HashMap();
        params.put("type_id", type_id);
        List<HelpIssue> helpIssueList = helpIssueService.selectByMap(params);

        return toResponsSuccess(helpIssueList);
    }
}
