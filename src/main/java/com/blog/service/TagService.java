package com.blog.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.TagMapper;
import com.blog.model.Article;
import com.blog.model.Tag;
import com.blog.util.Pager;
import com.blog.util.cache.EhcacheUtil;

@Service
@Transactional(isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class TagService{
	
	@Autowired
	private TagMapper tagMapper;

	public List<Tag> getTagList(HashMap<String, Object> paramMap,Pager<Tag> pager) {
		List<Tag> tagList = null;
		int count = tagMapper.getTagCount(paramMap);
		pager.setTotalCount(count);
		if(count > 0){
			paramMap.put("start", pager.getStart());
			paramMap.put("limit", pager.getLimit());
			tagList = tagMapper.getTagList(paramMap);
		}
		return tagList;
	}

	public int addTag(Tag tag) {
		return tagMapper.addTag(tag);
	}

	public int deleteTag(String id) {
		return tagMapper.deleteTag(id);
	}

	public Tag getTagById(String id) {
		return tagMapper.getTagById(id);
	}

	public int editTag(Tag tag) {
		return tagMapper.editTag(tag);
	}

	public List<Tag> getTagList() {
		List<Tag> tagList = (List<Tag>)EhcacheUtil.get("defaultCache", "tagList");
		if(tagList == null){
			tagList = tagMapper.getAllTagList();
			EhcacheUtil.put("defaultCache", "tagList",tagList);
		}
		return tagList;
	}

	public List<Tag> getArticleTagList(String id) {
		return tagMapper.getArticleTagList(id);
	}

	public Tag getTagByName(String tagName) {
		return tagMapper.getTagByName(tagName);
	}

	public List<Article> getLastTagArticleList(HashMap<String, Object> paramMap) {
		List<Article> articleList = tagMapper.getLastTagArticleList(paramMap);
		return articleList;
	}
}
