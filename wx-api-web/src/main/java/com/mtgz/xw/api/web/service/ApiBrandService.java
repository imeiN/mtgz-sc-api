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
	private BrandMapper brandDao;
	
	
	public Brand selectByPrimaryKey(Integer id){
		return brandDao.selectByPrimaryKey(id);
	}
	
	
	public List<Brand> selectByMap(Map<String, Object> map){
		return brandDao.selectByMap(map);
	}
	
	
	public int countByMap(Map<String, Object> map){
		return brandDao.countByMap(map);
	}
	
	
	public void save(Brand brand){
		brandDao.insert(brand);
	}
	
	
	public void update(Brand brand){
		brandDao.updateByPrimaryKey(brand);
	}
	
	
	public void delete(Integer id){
		brandDao.deleteByPrimaryKey(id);
	}
	
	
	public void deleteBatch(Integer[] ids){
		brandDao.deleteBatch(ids);
	}
	
}
