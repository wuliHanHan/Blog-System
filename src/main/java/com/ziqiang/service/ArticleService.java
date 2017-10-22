package com.ziqiang.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziqiang.mapper.ArticleMapper;
import com.ziqiang.model.Article;
import com.ziqiang.model.Tag;
import com.ziqiang.util.Pager;
import com.ziqiang.util.SuprUtil;

@Service
public class ArticleService {
	@Autowired
	private ArticleMapper articleMapper;

	public int addArticle(Article article) {
		int count = articleMapper.addArticle(article);

		for (Tag tag : article.getTagList()) {
			article.setTagId(tag.getId());
			articleMapper.addArticleTag(article);
		}

		// 提取文章图片地址
		List<String> imageList = getImageSrc(article.getContent());
		if (!SuprUtil.isEmptyCollection(imageList)) {
			for (String imageUrl : imageList) {
				article.setImageUrl(imageUrl.substring(imageUrl.indexOf("uploads") + 8));
				articleMapper.addArticleImage(article);
			}
		}

		return count;
	}

	public static List<String> getImageSrc(String htmlCode) {
		List<String> imageSrcList = new ArrayList<String>();
		Pattern p = Pattern.compile(
				"<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>",
				Pattern.CASE_INSENSITIVE);
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

	public List<Article> getLastArticleList(HashMap<String, Object> paramMap) {
		List<Article> articleList = articleMapper.getLastArticleList(paramMap);
		return articleList;
	}

	public String getArticleImageUrl(String id) {
		return articleMapper.getArticleImageUrl(id);
		
	}

	public void incrArticleShowCount(String id) {
		articleMapper.incrArticleShowCount(id);
	}

	public Article getArticleById(String id) {
		return articleMapper.getArticleById(id);
	}

	public Article getBeforeArticle(HashMap<String, Object> param) {
		return articleMapper.getBeforeArticle(param);
	}

	public Article getNextArticle(HashMap<String, Object> param) {
		return articleMapper.getNextArticle(param);
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

}
