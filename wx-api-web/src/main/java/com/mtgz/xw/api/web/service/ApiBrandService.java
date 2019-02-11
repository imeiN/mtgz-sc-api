package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.BrandMapper;
import com.mtgz.xw.api.dao.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiBrandService {
	@Autowired
	private BrandMapper brandMapper;
	
	
	public Brand selectByPrimaryKey(Integer id){
		return brandMapper.selectByPrimaryKey(id);
	}
	
	
	public List<Brand> selectByMap(Map<String, Object> map){
		return brandMapper.selectByMap(map);
	}
	
	
	public int countByMap(Map<String, Object> map){
		return brandMapper.countByMap(map);
	}
	
	
	public void save(Brand brand){
		brandMapper.insertSelective(brand);
	}
	
	
	public void update(Brand brand){
		brandMapper.updateByPrimaryKeySelective(brand);
	}
	
	
	public void delete(Integer id){
		brandMapper.deleteByPrimaryKey(id);
	}
	
	
	public void deleteBatch(Integer[] ids){
		brandMapper.deleteBatch(ids);
	}
	
}
