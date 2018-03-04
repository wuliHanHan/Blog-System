package com.blog.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.CategoryMapper;
import com.blog.model.Category;
import com.blog.model.Tag;
import com.blog.util.Pager;
import com.blog.util.cache.EhcacheUtil;

@Service
@Transactional(isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class CategoryService{
	
	@Autowired
	private CategoryMapper categoryMapper;

	public 	Category getByName(String categoryName) {
		return categoryMapper.getByName(categoryName);
	}
	
	public List<Category> getCategoryList() {
		List<Category> categoryList = (List<Category>)EhcacheUtil.get("defaultCache", "categoryList");
		if(categoryList == null){
			categoryList = categoryMapper.getCategoryList();
			EhcacheUtil.put("defaultCache", "categoryList",categoryList);
		}
		return categoryList;
	}

	public Category getCategoryByAlias(String aliasName) {
		return categoryMapper.getCategoryByAlias(aliasName);
	}

	public List<Category> getCategoryList(HashMap<String, Object> paramMap, Pager<Category> pager) {
		List<Category> categoryList = null;
		int count = categoryMapper.getCategoryCount(paramMap);
		pager.setTotalCount(count);
		if(count > 0){
			paramMap.put("start", pager.getStart());
			paramMap.put("limit", pager.getLimit());
			categoryList = categoryMapper.getCategoryListByPage(paramMap);
		}
		return categoryList;
	}
	public int getMaxSort() {
		return categoryMapper.getMaxSort();
	}
	public int addCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryMapper.addCategory(category);
	}

	public int deleteCategory(String id) {
		// TODO Auto-generated method stub
		return categoryMapper.deleteCategory(id);
	}

	public Category getCategoryById(String id) {
		// TODO Auto-generated method stub
		return categoryMapper.getCategoryById(id);
	}

	public int editCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryMapper.editCategory(category);
	}
	
}
