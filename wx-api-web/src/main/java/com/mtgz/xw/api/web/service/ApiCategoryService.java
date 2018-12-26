package com.mtgz.xw.api.web.service;

import com.mtgz.xw.api.dao.mapper.CategoryMapper;
import com.mtgz.xw.api.dao.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ApiCategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	
	
	public Category selectByPrimaryKey(Integer id){
		return categoryMapper.selectByPrimaryKey(id);
	}
	
	
	public List<Category> selectByMap(Map<String, Object> map){
		return categoryMapper.selectByMap(map);
	}
	
	
	public int countByMap(Map<String, Object> map){
		return categoryMapper.countByMap(map);
	}
	
	
	public void save(Category category){
		categoryMapper.insertSelective(category);
	}
	
	
	public void update(Category category){
		categoryMapper.updateByPrimaryKey(category);
	}
	
	
	public void delete(Integer id){
		categoryMapper.deleteByPrimaryKey(id);
	}
	
	
	public void deleteBatch(Integer[] ids){
		categoryMapper.deleteBatch(ids);
	}
	
}
