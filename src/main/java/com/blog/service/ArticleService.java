package com.blog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.ArticleMapper;
import com.blog.model.Article;
import com.blog.model.Tag;
import com.blog.util.Pager;
import com.blog.util.BlogUtil;

@Service
@Transactional(isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class ArticleService{
	
	@Autowired
	private ArticleMapper articleMapper;
	
	public Article getArticleById(String id) {
		return articleMapper.getArticleById(id);
	}
	
	public List<Article> getArticleList(HashMap<String, Object> paramMap, Pager<Article> pager) {
		List<Article> articleList = null;
		int count = articleMapper.getArticleCount(paramMap);
		pager.setTotalCount(count);
		if(count > 0){
			paramMap.put("start", pager.getStart());
			paramMap.put("limit", pager.getLimit());
			articleList = articleMapper.getArticleList(paramMap);
		}
		return articleList;
	}
	
	public List<Article> getAllArticleList() {
		return articleMapper.getAllArticleList();
	}
	
	public int editArticle(Article article) {
		// 删除原来的文章标签关系
		articleMapper.deleteArticleTag(article);
		int count = articleMapper.editArticle(article);
		for(Tag tag : article.getTagList()){
			article.setTagId(tag.getId());
			articleMapper.addArticleTag(article);
		}
		
		// 删除原来的文章图片关系
		articleMapper.deleteArticleImage(article);
		// 提取文章图片地址
		List<String> imageList = getImageSrc(article.getContent());
		if(!BlogUtil.isEmptyCollection(imageList)){
			for(String imageUrl : imageList){
				article.setImageUrl(imageUrl.substring(imageUrl.indexOf("uploads")+8));
				articleMapper.addArticleImage(article);
			}
		}
		
		return count;
	}
	
	public int addArticle(Article article) {
		int count = articleMapper.addArticle(article);
		for (Tag tag : article.getTagList()) {
			article.setTagId(tag.getId());
			articleMapper.addArticleTag(article);
		}

		// 提取文章图片地址
		List<String> imageList = getImageSrc(article.getContent());
		if (!BlogUtil.isEmptyCollection(imageList)) {
			for (String imageUrl : imageList) {
				article.setImageUrl(imageUrl.substring(imageUrl.indexOf("uploads") + 8));
				articleMapper.addArticleImage(article);
			}
		}

		return count;
	}
	
	public static List<String> getImageSrc(String htmlCode) {
		List<String> imageSrcList = new ArrayList<String>();
		Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(htmlCode);
		String quote = null;
		String src = null;
		while (m.find()) {
			quote = m.group(1);
			src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
			imageSrcList.add(src);
		}
		return imageSrcList;
	}
	
	public int deleteArticle(String id) {
		articleMapper.deleteArticleImageById(id);
		articleMapper.deleteArticleTagById(id);
		// 删除文章
		return articleMapper.deleteArticle(id);
	}
	
	public int updateStatue(Article article) {
		return articleMapper.updateStatue(article);
	}

	public List<Article> getLastArticleList(HashMap<String, Object> paramMap) {
		List<Article> articleList = articleMapper.getLastArticleList(paramMap);
		return articleList;
	}

	public String getArticleImageUrl(String id) {
		return articleMapper.getArticleImageUrl(id);
	}

	public Article getNextArticle(HashMap<String, Object> param) {
		return articleMapper.getNextArticle(param);
	}

	public Article getBeforeArticle(HashMap<String, Object> param) {
		return articleMapper.getBeforeArticle(param);
	}

	public List<Article> getRelationArticleList(HashMap<String,Object> param) {
		return articleMapper.getRelationArticleList(param);
	}

	public void incrArticleShowCount(String id) {
		articleMapper.incrArticleShowCount(id);
	}

	public Article getArticleByTitle(String title) {
		// TODO Auto-generated method stub
		return articleMapper.getArticleByTitle(title);
	}

	public List<Article> getArticleListByTitle(String title) {
		// TODO Auto-generated method stub
		return articleMapper.getArticleListByTitle(title);
	}
}
