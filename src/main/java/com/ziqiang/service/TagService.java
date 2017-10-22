package com.ziqiang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziqiang.mapper.TagMapper;
import com.ziqiang.model.Tag;

@Service
public class TagService {
	@Autowired
	private TagMapper tagMapper;

	public List<Tag> getTagList() {
		List<Tag> tagList = tagMapper.getAllTagList();
		return tagList;
	}

	public List<Tag> getArticleTagList(String id) {
		return tagMapper.getArticleTagList(id);
	}

}
