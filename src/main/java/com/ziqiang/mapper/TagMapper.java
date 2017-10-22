package com.ziqiang.mapper;

import java.util.List;

import com.ziqiang.model.Tag;

public interface TagMapper {

	List<Tag> getTagList();

	List<Tag> getArticleTagList(String id);

	List<Tag> getAllTagList();
	
}
