package com.blog.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.model.Article;
import com.blog.model.Category;
import com.blog.model.Friend;
import com.blog.model.Manager;
import com.blog.model.Tag;
import com.blog.service.ArticleService;
import com.blog.service.CategoryService;
import com.blog.service.FriendService;
import com.blog.service.TagService;
import com.blog.util.Constant;
import com.blog.util.HttpTookit;
import com.blog.util.JsonUtil;
import com.blog.util.Pager;
import com.blog.util.Result;
import com.blog.util.BlogUtil;
import com.blog.util.listener.SystemListener;
import com.blog.util.thread.LinkCommitThread;

@Controller
public class ArticleController{

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TagService tagService;

	@Autowired
	private FriendService friendService;

	/**
	 * 跳转文章列表
	 */
	@RequestMapping(value = "/admin/article/list")
	public String list(HttpSession session,ModelMap map) {
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);

		// 栏目列表
		List<Category> categoryList = categoryService.getCategoryList();
		// 标签列表
		List<Tag> tagList = tagService.getTagList();

		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		return "/admin/article/article_list";
	}
	@RequestMapping(value = "/article/searchJump")
	public String searchJump(HttpSession session,ModelMap map,@Param(value="title") 
	String title) throws UnsupportedEncodingException {
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		title =  new String(title.getBytes("ISO-8859-1"), "utf8");
		// 栏目列表
		List<Category> categoryList = categoryService.getCategoryList();
		List<Friend> friendList = friendService.getAllFriendList();
		// 标签列表
		List<Tag> tagList = tagService.getTagList();
		//文章列表		
		System.out.println(title);
		List<Article> articleList = articleService.getArticleListByTitle(title);

		map.put("title", title);
		map.put("friendList", friendList);		
		map.put("articleList", articleList);
		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		return "/blog/search_article_pager";
	}

	@RequestMapping(value = "/article/search/{mytitle}")
	public String search(HttpSession session,ModelMap map,@PathVariable
			String mytitle) throws UnsupportedEncodingException {
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		mytitle =  new String(mytitle.getBytes("ISO-8859-1"), "utf8");
		// 栏目列表
		List<Category> categoryList = categoryService.getCategoryList();
		List<Friend> friendList = friendService.getAllFriendList();
		// 标签列表
		List<Tag> tagList = tagService.getTagList();
		//文章列表		
		System.out.println(mytitle);
		List<Article> articleList = articleService.getArticleListByTitle(mytitle);
		System.out.println(articleList.size());
		if(!BlogUtil.isEmptyCollection(articleList)){
			int i = 1;
			for(Article article : articleList){
				// 获取标签
				List<Tag> tList = tagService.getArticleTagList(String.valueOf(article.getId()));
				article.setTagList(tList);
				// 获取图片
				String imageUrl = articleService.getArticleImageUrl(String.valueOf(article.getId()));
				article.setImageUrl(imageUrl);


				if(i == articleList.size()){
					map.put("articleId", article.getId());
				}
				i++;
			}
		}
		map.put("title", mytitle);
		map.put("friendList", friendList);		
		map.put("articleList", articleList);
		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		return "/blog/search_article_list";
	}
	/**
	 * 获取文章分页列表
	 */
	@RequestMapping(value = "/admin/article/load")
	public String loadArticlePageList(HttpSession session,ModelMap map,String param,Pager<Article> pager) throws UnsupportedEncodingException {
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);

		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		Article article = JsonUtil.fromJson(param, Article.class);
		if(!StringUtils.isEmpty(article.getTitle())){
			article.setTitle(URLDecoder.decode(article.getTitle(), "utf-8"));
		}
		paramMap.put("article", article);
		paramMap.put("manager", manager);
		List<Article> articleList = articleService.getArticleList(paramMap, pager);
		map.put("articleList", articleList);
		map.put("pager", pager);
		return "/admin/article/article_pager_list";
	}

	/**
	 * 获取文章分页列表
	 */
	@RequestMapping(value = "/article/loadPage/{categoryId}/{articleId}")
	public String loadPage(HttpSession session,ModelMap map,@PathVariable String articleId,@PathVariable String categoryId) throws UnsupportedEncodingException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("categoryId", categoryId);
		paramMap.put("articleId", articleId);
		// 最新的文章列表
		List<Article> articleList = articleService.getLastArticleList(paramMap);
		if(!BlogUtil.isEmptyCollection(articleList)){
			int i = 1;
			for(Article article : articleList){
				// 获取标签
				List<Tag> tList = tagService.getArticleTagList(String.valueOf(article.getId()));
				article.setTagList(tList);
				// 获取图片
				String imageUrl = articleService.getArticleImageUrl(String.valueOf(article.getId()));
				article.setImageUrl(imageUrl);


				if(i == articleList.size()){
					map.put("articleId", article.getId());
				}
				i++;
			}
		}
		map.put("articleList", articleList);
		return "/blog/article/article_pager";
	}

	/**
	 * 跳转文章新增页面
	 */
	@RequestMapping(value = "/admin/article/addJump")
	public String addJump(HttpSession session,ModelMap map){
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);

		// 栏目列表  链接栏目
		List<Category> categoryList = categoryService.getCategoryList();
		// 标签列表
		List<Tag> tagList = tagService.getTagList();

		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		return "/admin/article/article_add";
	}

	/**
	 * 跳转文章编辑页面
	 */
	@RequestMapping(value = "/article/html/{id}")
	public String articleDetail(HttpSession session, ModelMap map,@PathVariable String id) {
		// 更新文章的浏览数
		articleService.incrArticleShowCount(id);

		// 栏目列表
		List<Category> categoryList = categoryService.getCategoryList();
		// 标签列表
		List<Tag> tagList = tagService.getTagList();
		// 合作伙伴列表
		List<Friend> friendList = friendService.getAllFriendList();
		// 文章详情
		Article article = articleService.getArticleById(id);
		// 获取标签
		List<Tag> tList = tagService.getArticleTagList(id);
		article.setTagList(tList);

		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("articleId", id);
		param.put("categoryId", article.getCategoryId());
		// 获取上一个文章 下一个文章id
		Article beforeArticle = articleService.getBeforeArticle(param);
		Article nextArticle = articleService.getNextArticle(param);

		// 获取相关文章  4个  同类型的随机四个
		List<Article> relationList = articleService.getRelationArticleList(param);
		for(Article a : relationList){
			// 获取图片
			String imageUrl = articleService.getArticleImageUrl(String.valueOf(a.getId()));
			a.setImageUrl(imageUrl);
		}

		map.put("beforeArticle", beforeArticle);
		map.put("nextArticle", nextArticle);
		map.put("article", article);
		map.put("relationList", relationList);
		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		map.put("friendList", friendList);

		return "/blog/article/article_detail";
	}

	/**
	 * 添加文章
	 */
	@RequestMapping(value = "/admin/article/add")
	public @ResponseBody Result addArticle(String description,String content,String param,HttpSession session) throws UnsupportedEncodingException {
		param = URLDecoder.decode(param,"utf-8");
		content = URLDecoder.decode(content,"utf-8");
		description = URLDecoder.decode(description,"utf-8");
		Article article = JsonUtil.fromJson(param, Article.class);
		article.setContent(content);
		article.setDescription(description);
		if(articleService.getArticleByTitle(article.getTitle())!=null) {
			return new Result("exist", "文章已存在");
		}

		if(articleService.addArticle(article) > 0){
			// 提交百度链接收录
			LinkCommitThread.addLink(String.valueOf(article.getId().intValue()));
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}

	/**
	 * 删除文章
	 */
	@RequestMapping(value = "/admin/article/delete")
	public @ResponseBody Result deleteArticle(String id) {
		if(articleService.deleteArticle(id) > 0){
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}

	/**
	 * 跳转文章编辑页面
	 */
	@RequestMapping(value = "/admin/article/editJump/{id}")
	public String editJump(HttpSession session,ModelMap map, @PathVariable String id) {
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		// 栏目列表 链接栏目
		List<Category> categoryList = categoryService.getCategoryList();
		// 标签列表
		List<Tag> allTagList = tagService.getTagList();

		Article article = articleService.getArticleById(id);
		List<Tag> tagList = tagService.getArticleTagList(id);

		map.put("article", article);
		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		map.put("allTagList", allTagList);
		map.put("articleId", id);
		return "/admin/article/article_edit";
	}

	/**
	 * 变更状态
	 */
	@RequestMapping(value = "/admin/article/updateStatue")
	public @ResponseBody Result updateStatue(Article article) {
		if(articleService.updateStatue(article) > 0){
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}

	/**
	 * 编辑文章
	 */
	@RequestMapping(value = "/admin/article/edit")
	public @ResponseBody Result editArticle(String description,String content,String param,HttpSession session) throws UnsupportedEncodingException {
		content = URLDecoder.decode(content,"utf-8");
		description = URLDecoder.decode(description,"utf-8");
		param = URLDecoder.decode(param,"utf-8");
		Article article = JsonUtil.fromJson(param, Article.class);
		article.setContent(content);
		article.setDescription(description);
		Article a = articleService.getArticleByTitle(article.getTitle());

		if(a!=null&&a.getId()!=article.getId()) {
			return new Result("exist", "文章已存在");
		}


		if(articleService.editArticle(article) > 0){
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}

	/**
	 * 重新静态化文章
	 */
	@RequestMapping(value = "/admin/article/staticAll")
	public @ResponseBody Result staticAllArticle(HttpSession session){
		List<Article> articleList = articleService.getAllArticleList();
		if(!BlogUtil.isEmptyCollection(articleList)){
			for(Article article : articleList){
				// 模拟请求 静态化
				String articleUrl = Constant.HOST_URL + "/article/html/"+article.getId().intValue();
				HttpTookit.doGet(articleUrl, null);
			}
		}

		return new Result("success", Constant.DEAL_SUCCESS);
	}

	/**
	 * 重新静态化文章
	 */
	@RequestMapping(value = "/admin/article/static/{id}")
	public @ResponseBody Result staticArticle(@PathVariable String id,HttpSession session){
		Article article = articleService.getArticleById(id);

		// 模拟请求 静态化
		String articleUrl = Constant.HOST_URL + "/article/html/"+id;
		HttpTookit.doGet(articleUrl, null);
		return new Result("success", Constant.DEAL_SUCCESS);
	}
}
