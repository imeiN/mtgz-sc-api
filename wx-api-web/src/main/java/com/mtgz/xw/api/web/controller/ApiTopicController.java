package com.mtgz.xw.api.web.controller;

import com.mtgz.common.service.common.AppConstants;
import com.mtgz.xw.api.dao.model.Topic;
import com.mtgz.xw.api.dao.model.User;
import com.mtgz.xw.api.web.annotation.IgnoreAuth;
import com.mtgz.xw.api.web.annotation.LoginUser;
import com.mtgz.xw.api.web.service.ApiTopicService;
import com.mtgz.common.service.common.util.ApiPageUtils;
import com.mtgz.common.service.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/topic")
public class ApiTopicController extends ApiBaseAction {
    @Autowired
    private ApiTopicService topicService;

    /**
     */
    @IgnoreAuth
    @PostMapping("list")
    public Object list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map param = new HashMap();
        param.put("page", page);
        param.put("limit", size);
        param.put("sidx", "id");
        param.put("order", "desc");
        param.put("fields", "id, title, price_info, scene_pic_url,subtitle");
        //查询列表数据
        Query query = new Query(param);
        List<Topic> topicEntities = topicService.selectByMap(query);
        int total = topicService.countByMap(query);
        ApiPageUtils pageUtil = new ApiPageUtils(topicEntities, total, query.getLimit(), query.getPage());
        return toResponsSuccess(pageUtil);
    }

    /**
     */
    @IgnoreAuth
    @PostMapping("detail")
    public Object detail(@LoginUser User loginUser, Integer id) {
        Topic topicEntity = topicService.selectByPrimaryKey(id);
        return toResponsSuccess(topicEntity);
    }

    /**
     */
    @IgnoreAuth
    @PostMapping("related")
    public Object related(@LoginUser User loginUser, Integer id) {
        Map param = new HashMap();
        param.put("limit", 4);
        List<Topic> topicEntities = topicService.selectByMap(param);
        return toResponsSuccess(topicEntities);
    }
}