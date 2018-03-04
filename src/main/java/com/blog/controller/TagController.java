package com.blog.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.blog.util.JsonUtil;
import com.blog.util.Pager;
import com.blog.util.Result;
import com.blog.util.BlogUtil;

@Controller
public class TagController{
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private FriendService friendService;
	
	/**
	 * 跳转列表
	 */
	@RequestMapping(value = "/admin/tag/list")
	public String list(HttpSession session,ModelMap map) {
		// 标签列表
		List<Tag> tagList = tagService.getTagList();	
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		map.put("tagList", tagList);
		return "/admin/tag/tag_list";
	}
	
	/**
	 * 获取分页列表
	 */
	@RequestMapping(value = "/admin/tag/load")
	public String loadTagPageList(HttpSession session,ModelMap map,String param,Pager<Tag> pager) throws UnsupportedEncodingException {
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		
		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		Tag tag = JsonUtil.fromJson(param, Tag.class);
		if(!StringUtils.isEmpty(tag.getTagName())){
			tag.setTagName(URLDecoder.decode(tag.getTagName(), "utf-8"));
		}
		paramMap.put("tag", tag);
		List<Tag> tagList = tagService.getTagList(paramMap, pager);
		map.put("tagList", tagList);
		map.put("pager", pager);
		return "/admin/tag/tag_pager_list";
	}
	
	/**
	 * 跳转新增页面
	 */
	@RequestMapping(value = "/admin/tag/addJump")
	public String addJump(HttpSession session,ModelMap map){
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		return "/admin/tag/tag_add";
	}
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/admin/tag/add")
	public @ResponseBody Result addTag(Tag tag,HttpSession session) throws UnsupportedEncodingException {
		tag.setTagName(URLDecoder.decode(tag.getTagName(), "utf-8"));
		if(tagService.getTagByName(tag.getTagName())!=null) {
			return new Result("exist", "标签已存在");
		}
		if(tagService.addTag(tag) > 0){
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/admin/tag/delete")
	public @ResponseBody Result deleteTag(String id) {
		if(tagService.deleteTag(id) > 0){
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}
	
	/**
	 * 跳转编辑页面
	 */
	@RequestMapping(value = "/admin/tag/editJump/{id}")
	public String editJump(HttpSession session,ModelMap map, @PathVariable String id) {
		Tag tag = tagService.getTagById(id);
		map.put("tag", tag);
		map.put("tagId", id);
		return "/admin/tag/tag_edit";
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/admin/tag/edit")
	public @ResponseBody Result editTag(Tag tag,HttpSession session) throws UnsupportedEncodingException {
		tag.setTagName(URLDecoder.decode(tag.getTagName(), "utf-8"));
		if(tagService.getTagByName(tag.getTagName())!=null) {
			return new Result("exist", "标签已存在");
		}
		if(tagService.editTag(tag) > 0){
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}
	
	/**
	 * 获取tag分页列表
	 */
	@RequestMapping(value = "/tag/{tagName}")
	public String loadCategoryPage(HttpSession session,ModelMap map,@PathVariable String tagName) throws UnsupportedEncodingException {
		//System.out.println(tagName);
		tagName =  new String(tagName.getBytes("ISO-8859-1"), "utf8");  
		System.out.println(tagName);
		Tag tag = tagService.getTagByName(tagName);
		//System.out.println(tag.getTagName());
		// 栏目列表
		List<Category> categoryList = categoryService.getCategoryList();
		
		// 标签列表
		List<Tag> tagList = tagService.getTagList();
		
		// 合作伙伴列表
		List<Friend> friendList = friendService.getAllFriendList();
		
		map.put("categoryList", categoryList);
		if(tag == null){
			tag = new Tag();
			tag.setTagName("无内容");
			tag.setId(-2);
		}
		map.put("tag", tag);
		map.put("tagList", tagList);
		map.put("friendList", friendList);
		
		return "/blog/tag/tag_detail";
	}
	
	
	/**
	 * 获取文章分页列表
	 */
	@RequestMapping(value = "/tag/loadPage/{tagId}/{articleId}")
	public String loadPage(HttpSession session,ModelMap map,@PathVariable String articleId,@PathVariable String tagId) throws UnsupportedEncodingException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tagId", tagId);
		paramMap.put("articleId", articleId);
		// 最新的文章列表
		List<Article> articleList = tagService.getLastTagArticleList(paramMap);
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
		return "/blog/tag/article_pager";
	}
	
}
