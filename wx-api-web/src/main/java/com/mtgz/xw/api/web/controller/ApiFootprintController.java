package com.mtgz.xw.api.web.controller;

import com.github.pagehelper.PageHelper;
import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.dao.model.Footprint;
import com.mtgz.xw.api.dao.model.User;
import com.mtgz.xw.api.web.annotation.LoginUser;
import com.mtgz.xw.api.web.service.ApiFootprintService;
import com.mtgz.common.service.common.util.ApiPageUtils;
import com.mtgz.common.service.common.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: ApiIndexController <br>
 */
@Api(tags = "足迹")
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/footprint")
public class ApiFootprintController extends ApiBaseAction {
    @Autowired
    private ApiFootprintService footprintService;

    /**
     */
    @ApiOperation(value = "删除足迹")
    @ApiImplicitParams({@ApiImplicitParam(name = "footprintId", value = "足迹id", paramType = "path", required = true)})
    @PostMapping("delete")
    public Object delete(@LoginUser User loginUser, Integer footprintId) {
        if (footprintId == null) {
            return toResponsFail("删除出错");
        }
        //删除当天的同一个商品的足迹
        Footprint footprintEntity = footprintService.selectByPrimaryKey(footprintId);
        //
        if (loginUser == null || loginUser.getId() == null || footprintEntity == null || footprintEntity.getGoodsId() == null) {
            return toResponsFail("删除出错");
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", loginUser.getId());
        param.put("goodsId", footprintEntity.getGoodsId());
        footprintService.deleteByParam(param);

        return toResponsMsgSuccess("删除成功");
    }

    /**
     */
    @ApiOperation(value = "获取足迹列表")
    @PostMapping("list")
    public Object list(@LoginUser User loginUser,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> resultObj = new HashMap<String, Object>();

        //查询列表数据
        PageHelper.startPage(0, 10, false);
        List<Footprint> footprintVos = footprintService.selectByMapFootprint(loginUser.getId() + "");

        ApiPageUtils pageUtil = new ApiPageUtils(footprintVos, 0, size, page);
        //
        Map<String, List<Footprint>> footPrintMap = new TreeMap<String, List<Footprint>>(new Comparator<String>() {
            /*
             * int compare(Object o1, Object o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            @Override
            public int compare(String o1, String o2) {

                //指定排序器按照降序排列
                return o2.compareTo(o1);
            }
        });

        if (null != footprintVos && footprintVos.size() > 0) {
            for (Footprint footprintVo : footprintVos) {
                String addTime = DateUtils.timeToStr(footprintVo.getAddTime(), DateUtils.DATE_PATTERN);
                List<Footprint> tmpList = footPrintMap.get(addTime);
                if (null == footPrintMap.get(addTime)) {
                    tmpList = new ArrayList<Footprint>();
                }
                tmpList.add(footprintVo);
                footPrintMap.put(addTime, tmpList);
            }
            List<List<Footprint>> footprintVoList = new ArrayList<List<Footprint>>();
            for (Map.Entry<String, List<Footprint>> entry : footPrintMap.entrySet()) {
                footprintVoList.add(entry.getValue());
            }
            resultObj.put("count", pageUtil.getCount());
            resultObj.put("totalPages", pageUtil.getTotalPages());
            resultObj.put("numsPerPage", pageUtil.getNumsPerPage());
            resultObj.put("currentPage", pageUtil.getCurrentPage());
            resultObj.put("data", footprintVoList);
        }

        return this.toResponsSuccess(resultObj);
    }


    /**
     */
    @ApiOperation(value = "分享足迹")
    @PostMapping("sharelist")
    public Object sharelist(@LoginUser User loginUser,
                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, List<Footprint>> resultObj = new HashMap<String, List<Footprint>>();

        //查询列表数据
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sidx", "f.id");
        params.put("order", "desc");
        params.put("referrer", loginUser.getId());
        List<Footprint> footprintVos = footprintService.shareList(params);
        //
        resultObj.put("data", footprintVos);
        return this.toResponsSuccess(resultObj);
    }
}