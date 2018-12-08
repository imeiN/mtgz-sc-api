package com.mtgz.xw.api.web.controller;

import com.mtgz.common.service.client.CommonClient;
import com.mtgz.common.service.common.entity.SysRegionEntity;
import com.mtgz.xw.api.common.AppConstants;
import com.mtgz.xw.api.web.annotation.IgnoreAuth;
import com.mtgz.xw.api.web.vo.RegionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "评论")
@RestController
@RequestMapping(value = AppConstants.BASE_PATH + "/region")
public class ApiRegionController extends ApiBaseAction {

    @Autowired
    CommonClient commonClient;

    @ApiOperation(value = "地区列表")
    @IgnoreAuth
    @PostMapping("list")
    public Object list(Integer parentId) {
        List<SysRegionEntity> regionEntityList = commonClient.getChildrenByParentId(parentId);
        List<RegionVo> regionVoList = new ArrayList<RegionVo>();
        if (null != regionEntityList && regionEntityList.size() > 0) {
            for (SysRegionEntity sysRegionEntity : regionEntityList) {
                regionVoList.add(new RegionVo(sysRegionEntity));
            }
        }
        return toResponsSuccess(regionVoList);
    }

    @IgnoreAuth
    @PostMapping("provinceList")
    public Object provinceList() {
        List<SysRegionEntity> regionEntityList = commonClient.getAllProvice();
        List<RegionVo> regionVoList = new ArrayList<RegionVo>();
        if (null != regionEntityList && regionEntityList.size() > 0) {
            for (SysRegionEntity sysRegionEntity : regionEntityList) {
                regionVoList.add(new RegionVo(sysRegionEntity));
            }
        }
        return toResponsSuccess(regionVoList);
    }

    @IgnoreAuth
    @PostMapping("cityList")
    public Object provinceList(String proviceName) {
        List<SysRegionEntity> regionEntityList = commonClient.getChildrenCity(proviceName);
        List<RegionVo> regionVoList = new ArrayList<RegionVo>();
        if (null != regionEntityList && regionEntityList.size() > 0) {
            for (SysRegionEntity sysRegionEntity : regionEntityList) {
                regionVoList.add(new RegionVo(sysRegionEntity));
            }
        }
        return toResponsSuccess(regionVoList);
    }

    @IgnoreAuth
    @PostMapping("distinctList")
    public Object distinctList(String proviceName, String cityName) {
        List<SysRegionEntity> regionEntityList = commonClient.getChildrenDistrict(proviceName, cityName);
        List<RegionVo> regionVoList = new ArrayList<RegionVo>();
        if (null != regionEntityList && regionEntityList.size() > 0) {
            for (SysRegionEntity sysRegionEntity : regionEntityList) {
                regionVoList.add(new RegionVo(sysRegionEntity));
            }
        }
        return toResponsSuccess(regionVoList);
    }

    @IgnoreAuth
    @PostMapping("info")
    public Object info(Integer regionId) {
        SysRegionEntity regionEntity = commonClient.getAreaByAreaId(regionId);
        return toResponsSuccess(new RegionVo(regionEntity));
    }

    @IgnoreAuth
    @PostMapping("regionIdsByNames")
    public Object regionIdsByNames(String provinceName, String cityName, String districtName) {
        Map<String, Integer> resultObj = new HashMap<String, Integer>();
        Integer provinceId = 0;
        Integer cityId = 0;
        Integer districtId = 0;
        if (null != provinceName) {
            provinceId = commonClient.getProvinceIdByName(provinceName);
        }
        if (null != provinceId && !StringUtils.isBlank(cityName)) {
            cityId = commonClient.getCityIdByName(provinceId, cityName);
        }
        if (null != provinceId && null != cityId && !StringUtils.isBlank(districtName)) {
            districtId = commonClient.getDistrictIdByName(provinceId, cityId, districtName);
        }
        resultObj.put("provinceId", provinceId);
        resultObj.put("cityId", cityId);
        resultObj.put("districtId", districtId);
        return toResponsSuccess(resultObj);
    }
}