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
	private CategoryMapper categoryDao;
	
	
	public Category selectByPrimaryKey(Integer id){
		return categoryDao.selectByPrimaryKey(id);
	}
	
	
	public List<Category> selectByMap(Map<String, Object> map){
		return categoryDao.selectByMap(map);
	}
	
	
	public int countByMap(Map<String, Object> map){
		return categoryDao.countByMap(map);
	}
	
	
	public void save(Category category){
		categoryDao.insert(category);
	}
	
	
	public void update(Category category){
		categoryDao.updateByPrimaryKey(category);
	}
	
	
	public void delete(Integer id){
		categoryDao.deleteByPrimaryKey(id);
	}
	
	
	public void deleteBatch(Integer[] ids){
		categoryDao.deleteBatch(ids);
	}
	
}
