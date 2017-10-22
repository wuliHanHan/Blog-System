package com.ziqiang.mapper;

import java.util.HashMap;
import java.util.List;

import com.ziqiang.model.Article;

public interface ArticleMapper {

	public int addArticle(Article article);
	

	public void addArticleTag(Article article);
		

	public void addArticleImage(Article article);


	public List<Article> getLastArticleList(HashMap<String, Object> paramMap);


	public String getArticleImageUrl(String id);


	public void incrArticleShowCount(String id);


	public Article getArticleById(String id);


	public Article getBeforeArticle(HashMap<String, Object> param);


	public Article getNextArticle(HashMap<String, Object> param);


	public int getArticleCount(HashMap<String, Object> paramMap);


	public List<Article> getArticleList(HashMap<String, Object> paramMap);
	
	

}
