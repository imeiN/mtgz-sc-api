package com.mtgz.xw.api.web.controller;

import com.mtgz.common.service.common.CommonAppConstants;
import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.dao.model.*;
import com.mtgz.xw.api.web.annotation.IgnoreAuth;
import com.mtgz.xw.api.web.annotation.LoginUser;
import com.mtgz.xw.api.web.service.ApiKeywordsService;
import com.mtgz.xw.api.web.service.ApiSearchHistoryService;
import com.mtgz.common.service.common.util.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API登录授权
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-03-23 15:31
 */
@Api(tags = "商品搜索")
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/search")
public class ApiSearchController extends ApiBaseAction {
    @Autowired
    private ApiKeywordsService keywordsService;
    @Autowired
    private ApiSearchHistoryService searchHistoryService;

    /**
     * 　　index
     */
    @ApiOperation(value = "搜索商品列表")
    @PostMapping("index")
    public Object index(@LoginUser User loginUser) {
        Map<String, Object> resultObj = new HashMap();
        Map param = new HashMap();
        param.put("is_default", 1);
        param.put("page", 1);
        param.put("limit", 1);
        param.put("sidx", "id");
        param.put("order", "asc");
        List<Keywords> keywordsEntityList = keywordsService.selectByMap(param);
        //取出输入框默认的关键词
        Keywords defaultKeyword = null != keywordsEntityList && keywordsEntityList.size() > 0 ? keywordsEntityList.get(0) : null;
        //取出热闹关键词
        param = new HashMap();
        param.put("fields", "distinct keyword,is_hot");
        param.put("page", 1);
        param.put("limit", 10);
        param.put("sidx", "id");
        param.put("order", "asc");
        Query query = new Query(param);
        List<Map> hotKeywordList = keywordsService.hotKeywordList(query);
        //
        param = new HashMap();
        param.put("user_id", loginUser.getId());
        param.put("fields", "distinct keyword");
        param.put("page", 1);
        param.put("limit", 10);
        param.put("sidx", "id");
        param.put("order", "asc");
        List<SearchHistory> historyVoList = searchHistoryService.selectByMap(param);
        String[] historyKeywordList = new String[historyVoList.size()];
        if (null != historyVoList) {
            int i = 0;
            for (SearchHistory historyVo : historyVoList) {
                historyKeywordList[i] = historyVo.getKeyword();
                i++;
            }
        }
        //
        resultObj.put("defaultKeyword", defaultKeyword);
        resultObj.put("historyKeywordList", historyKeywordList);
        resultObj.put("hotKeywordList", hotKeywordList);
        return toResponsSuccess(resultObj);
    }

    /**
     * 　　helper
     */
    @ApiOperation(value = "搜索商品")
    @ApiImplicitParams({@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "path", required = true)})
    @IgnoreAuth
    @PostMapping("helper")
    public Object helper(@LoginUser User loginUser, String keyword) {
        Map param = new HashMap();
        param.put("fields", "distinct keyword");
        param.put("keyword", keyword);
        param.put("limit", 10);
        param.put("offset", 0);
        List<Keywords> keywords = keywordsService.selectByMap(param);
        String[] keys = new String[keywords.size()];
        if (null != keywords) {
            int i = 0;
            for (Keywords keywordsVo : keywords) {
                keys[i] = keywordsVo.getKeyword();
                i++;
            }
        }
        //
        return toResponsSuccess(keys);
    }

    /**
     * 　　clearhistory
     */
    @PostMapping("clearhistory")
    public Object clearhistory(@LoginUser User loginUser) {
        searchHistoryService.deleteByUserId(loginUser.getId());
        //
        return toResponsSuccess("");
    }
}
