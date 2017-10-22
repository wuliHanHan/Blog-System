package com.ziqiang.mapper;

import java.util.List;

import com.ziqiang.model.Category;

public interface CategoryMapper {

	public List<Category> getCategoryList();

	public Category getCategoryByAlias(String aliasName);

}
