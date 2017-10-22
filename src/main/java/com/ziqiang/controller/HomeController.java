package com.ziqiang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ziqiang.model.Category;
import com.ziqiang.model.Friend;
import com.ziqiang.model.Tag;
import com.ziqiang.service.CategoryService;
import com.ziqiang.service.FriendService;
import com.ziqiang.service.TagService;

@Controller
public class HomeController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TagService tagService;
	@Autowired
	private FriendService friendService;
	
	@RequestMapping("/")
	public String home(ModelMap map) {
		List<Category> categoryList = categoryService.getCategoryList();
		List<Tag> tagList = tagService.getTagList();
		List<Friend> friendList = friendService.getAllFriendList();
		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		map.put("friendList", friendList);
		return "/blog/home";
	}

	
	
}
