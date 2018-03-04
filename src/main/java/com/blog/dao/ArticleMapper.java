package com.blog.dao;

import java.util.HashMap;
import java.util.List;

import com.blog.model.Article;


public interface ArticleMapper {

	int getArticleCount(HashMap<String, Object> paramMap);

	Article getArticleById(String id);
	
	List<Article> getArticleList(HashMap<String, Object> paramMap);
	
	List<Article> getAllArticleList();

	int addArticle(Article article);

	int deleteArticle(String id);

	int editArticle(Article article);

	int updateStatue(Article article);

	void addArticleTag(Article article);

	void deleteArticleTag(Article article);

	void deleteArticleImage(Article article);

	void addArticleImage(Article article);

	void deleteArticleImageById(String id);

	void deleteArticleTagById(String id);

	int getAllArticleCount(HashMap<String, Object> paramMap);

	List<Article> getLastArticleList(HashMap<String,Object> paramMap);

	String getArticleImageUrl(String id);

	Article getNextArticle(HashMap<String, Object> param);

	Article getBeforeArticle(HashMap<String, Object> param);

	List<Article> getRelationArticleList(HashMap<String,Object> param);

	void incrArticleShowCount(String id);
	
	Article getArticleByTitle(String title);
	
	List<Article> getArticleListByTitle(String title);
}
