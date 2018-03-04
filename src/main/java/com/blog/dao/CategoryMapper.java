package com.blog.dao;

import java.util.HashMap;
import java.util.List;

import com.blog.model.Category;
import com.blog.model.Tag;
import com.blog.util.Pager;


public interface CategoryMapper {

	List<Category> getCategoryList();

	Category getCategoryByAlias(String aliasName);

	Category getCategoryById(String id);
	
	List<Category> getCategoryList(HashMap<String, Object> paramMap, Pager<Category> pager);

	List<Category> getCategoryListByPage(HashMap<String, Object> paramMap);

	int getCategoryCount(HashMap<String, Object> paramMap);
	
	Category getByName(String categoryName);
	
	int addCategory(Category category);
	
	int getMaxSort();
	
	int deleteCategory(String id);
	
	int editCategory(Category category);
}
