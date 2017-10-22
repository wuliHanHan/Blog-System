package com.ziqiang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziqiang.mapper.CategoryMapper;
import com.ziqiang.model.Category;

@Service
public class CategoryService {
	private CategoryMapper categoryMapper;
	
	public List<Category> getCategoryList() {
		List<Category> categoryList = categoryMapper.getCategoryList();
		return categoryList;
	}

	public CategoryMapper getCategoryMapper() {
		return categoryMapper;
	}
	@Autowired
	public void setCategoryMapper(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}

	public Category getCategoryByAlias(String aliasName) {
		return categoryMapper.getCategoryByAlias(aliasName);
	}
	
}
