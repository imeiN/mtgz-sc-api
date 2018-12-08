package com.mtgz.xw.api.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.dao.model.*;
import com.mtgz.xw.api.web.annotation.IgnoreAuth;
import com.mtgz.xw.api.web.annotation.LoginUser;
import com.mtgz.xw.api.web.service.*;
import com.mtgz.common.service.common.util.ApiPageUtils;
import com.mtgz.common.service.common.util.CharUtil;
import com.mtgz.common.service.common.util.Query;
import com.mtgz.xw.api.web.vo.CommentPictureVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mtgz.common.service.common.util.Base64;

import java.util.*;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "评论")
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/comment")
public class ApiCommentController extends ApiBaseAction {
    @Autowired
    private ApiCommentService commentService;
    @Autowired
    private ApiUserService userService;
    @Autowired
    private ApiCommentPictureService commentPictureService;
    @Autowired
    private ApiCouponService apiCouponService;
    @Autowired
    private ApiUserCouponService apiUserCouponService;

    /**
     * 发表评论
     */
    @ApiOperation(value = "发表评论")
    @PostMapping("post")
    public Object post(@LoginUser User loginUser) {
        Map resultObj = new HashMap();
        //
        JSONObject jsonParam = getJsonRequest();
        Byte typeId = jsonParam.getByte("typeId");
        Integer valueId = jsonParam.getInteger("valueId");
        String content = jsonParam.getString("content");
        JSONArray imagesList = jsonParam.getJSONArray("imagesList");
        Comment commentEntity = new Comment();
        commentEntity.setTypeId(typeId);
        commentEntity.setValueId(valueId);
        commentEntity.setContent(content);
        commentEntity.setStatus(new Byte("0"));
        //
        commentEntity.setAddTime(System.currentTimeMillis() / 1000);
        commentEntity.setUserId(loginUser.getId());
        commentEntity.setContent(com.mtgz.common.service.common.util.Base64.encode(commentEntity.getContent()));
        Integer insertId = commentService.save(commentEntity);
        //
        if (insertId > 0 && null != imagesList && imagesList.size() > 0) {
            int i = 0;
            for (Object imgLink : imagesList) {
                i++;
                CommentPicture pictureVo = new CommentPicture();
                pictureVo.setCommentId(insertId);
                pictureVo.setPicUrl(imgLink.toString());
                pictureVo.setSortOrder(i);
                commentPictureService.save(pictureVo);
            }
        }
        // 是否领取优惠券
        if (insertId > 0 && typeId == 0) {
            // 当前评价的次数
            Map param = new HashMap();
            param.put("value_id", valueId);
            List<Comment> commentVos = commentService.selectByMap(param);
            boolean hasComment = false;
            for (Comment commentVo : commentVos) {
                if (commentVo.getUserId().equals(loginUser.getId())
                        && !commentVo.getId().equals(insertId)) {
                    hasComment = true;
                }
            }
            if (!hasComment) {
                Map couponParam = new HashMap();
                couponParam.put("send_type", 6);
                Coupon newCouponConfig = apiCouponService.queryMaxUserEnableCoupon(couponParam);
                if (null != newCouponConfig
                        && newCouponConfig.getMinTransmitNum() >= commentVos.size()) {
                    UserCoupon userCoupon = new UserCoupon();
                    userCoupon.setAddTime(new Date());
                    userCoupon.setCouponId(newCouponConfig.getId());
                    userCoupon.setCouponNumber(CharUtil.getRandomString(12));
                    userCoupon.setUserId(loginUser.getId());
                    apiUserCouponService.save(userCoupon);
                    resultObj.put("coupon", newCouponConfig);
                }
            }
        }
        if (insertId > 0) {
            return toResponsObject(0, "评论添加成功", resultObj);
        } else {
            return toResponsFail("评论保存失败");
        }
    }

    /**
     */
    @ApiOperation(value = "评论数量")
    @PostMapping("count")
    public Object count(Integer typeId, Integer valueId) {
        Map<String, Object> resultObj = new HashMap();
        //
        Map param = new HashMap();
        param.put("type_id", typeId);
        param.put("value_id", valueId);
        Integer allCount = commentService.countByMap(param);
        Integer hasPicCount = commentService.queryhasPicTotal(param);
        //
        resultObj.put("allCount", allCount);
        resultObj.put("hasPicCount", hasPicCount);
        return toResponsSuccess(resultObj);
    }

    /**
     * @param typeId
     * @param valueId
     * @param showType 选择评论的类型 0 全部， 1 只显示图片
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "选择评论类型")
    @IgnoreAuth
    @PostMapping("list")
    public Object list(Integer typeId, Integer valueId, Integer showType,
                       @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size,
                       String sort, String order) {
        Map<String, Object> resultObj = new HashMap();
        List<Comment> commentList = new ArrayList();
        Map param = new HashMap();
        param.put("type_id", typeId);
        param.put("value_id", valueId);
        param.put("page", page);
        param.put("limit", size);
        if (StringUtils.isBlank(sort)) {
            param.put("order", "desc");
        } else {
            param.put("order", sort);
        }
        if (StringUtils.isBlank(order)) {
            param.put("sidx", "id");
        } else {
            param.put("sidx", order);
        }
        if (null != showType && showType == 1) {
            param.put("hasPic", 1);
        }
        //查询列表数据
        Query query = new Query(param);
        commentList = commentService.selectByMap(query);
        int total = commentService.countByMap(query);
        ApiPageUtils pageUtil = new ApiPageUtils(commentList, total, query.getLimit(), query.getPage());
        //
        for (Comment commentItem : commentList) {
            commentItem.setContent(Base64.decode(commentItem.getContent()));
            commentItem.setUser_info(userService.selectByPrimaryKey(commentItem.getUserId()));

            Map paramPicture = new HashMap();
            paramPicture.put("comment_id", commentItem.getId());
            List<CommentPicture> commentPictureEntities = commentPictureService.selectByMap(paramPicture);
            commentItem.setPic_list(commentPictureEntities);
        }
        return toResponsSuccess(pageUtil);
    }
}