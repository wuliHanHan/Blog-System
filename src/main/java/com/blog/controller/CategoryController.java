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

@Controller
public class CategoryController{
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private FriendService friendService;
	
	/**
	 * 获取文章分页列表
	 */
	@RequestMapping(value = "/category/{aliasName}")
	public String loadCategoryPage(HttpSession session,ModelMap map,@PathVariable String aliasName,String articleId,String categoryId) throws UnsupportedEncodingException {
		aliasName = new String(aliasName.getBytes("ISO-8859-1"), "utf-8");
		Category category = categoryService.getCategoryByAlias(aliasName);
		//System.out.println(aliasName);
		// 栏目列表
		List<Category> categoryList = categoryService.getCategoryList();;
		
		// 标签列表
		List<Tag> tagList = tagService.getTagList();
		
		// 合作伙伴列表
		List<Friend> friendList = friendService.getAllFriendList();;
		
		map.put("categoryList", categoryList);
		map.put("ca", category);
		map.put("tagList", tagList);
		map.put("friendList", friendList);
		
		return "/blog/category/article_pager";
	}
	
	
	/**
	 * 跳转列表
	 */
	@RequestMapping(value = "/admin/category/list")
	public String list(HttpSession session,ModelMap map) {
		// 标签列表
		List<Category> categoryList = categoryService.getCategoryList();	
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		map.put("categoryList", categoryList);
		return "/admin/category/category_list";
	}
	
	/**
	 * 获取分页列表
	 */
	@RequestMapping(value = "/admin/category/load")
	public String loadCategoryPageList(HttpSession session,ModelMap map,String param,Pager<Category> pager) throws UnsupportedEncodingException {
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);	
		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		Category category = JsonUtil.fromJson(param, Category.class);
		if(!StringUtils.isEmpty(category.getCategoryName())){
			category.setCategoryName(URLDecoder.decode(category.getCategoryName(), "utf-8"));
		}
		paramMap.put("category", category);
		List<Category> categoryList = categoryService.getCategoryList(paramMap, pager);
		map.put("categoryList", categoryList);
		map.put("pager", pager);
		return "/admin/category/category_pager_list";
	}
	
	/**
	 * 跳转新增页面
	 */
	@RequestMapping(value = "/admin/category/addJump")
	public String addJump(HttpSession session,ModelMap map){
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		return "/admin/category/category_add";
	}
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/admin/category/add")
	public @ResponseBody Result addCategory(Category category,HttpSession session) throws UnsupportedEncodingException {
		category.setCategoryName(URLDecoder.decode(category.getCategoryName(), "utf-8"));
		category.setAliasName(URLDecoder.decode(category.getAliasName(), "utf-8"));
		category.setSort(categoryService.getMaxSort()+1);
		if(categoryService.getByName(category.getCategoryName())!=null) {
			return new Result("exist", Constant.DEAL_FAIL);
		}
		if(categoryService.addCategory(category) > 0){
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/admin/category/delete")
	public @ResponseBody Result deleteCategory(String id) {
		if(categoryService.deleteCategory(id) > 0){
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}
	
	/**
	 * 跳转编辑页面
	 */
	@RequestMapping(value = "/admin/category/editJump/{id}")
	public String editJump(HttpSession session,ModelMap map, @PathVariable String id) {
		Category category = categoryService.getCategoryById(id);
		map.put("category", category);
		map.put("categoryId", id);
		return "/admin/category/category_edit";
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/admin/category/edit")
	public @ResponseBody Result editCategory(Category category,HttpSession session) throws UnsupportedEncodingException {
		category.setCategoryName(URLDecoder.decode(category.getCategoryName(), "utf-8"));
		category.setAliasName(URLDecoder.decode(category.getAliasName(), "utf-8"));
		Category category2 = categoryService.getByName(category.getCategoryName());
		//System.out.println(category2.getId());
		//System.out.println(category.getId());
		if((category2!=null&&category2.getId()!=category.getId())) {
			return new Result("exist", "Category has already existing");
		}
		if(categoryService.editCategory(category) > 0){
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}
}
