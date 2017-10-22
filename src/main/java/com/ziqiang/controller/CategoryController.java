package com.ziqiang.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ziqiang.model.Category;
import com.ziqiang.model.Friend;
import com.ziqiang.model.Tag;
import com.ziqiang.service.CategoryService;
import com.ziqiang.service.FriendService;
import com.ziqiang.service.TagService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TagService tagService;
	@Autowired
	private FriendService friendService;
	
	@RequestMapping(value = "/category/{aliasName}")
	public String loadCategoryPage(HttpSession session,ModelMap map,@PathVariable String aliasName) throws UnsupportedEncodingException {
		aliasName = URLDecoder.decode(aliasName, "utf-8");
		Category category = categoryService.getCategoryByAlias(aliasName);
		
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
}
