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
import com.blog.service.AdminService;
import com.blog.service.ArticleService;
import com.blog.service.CategoryService;
import com.blog.service.FriendService;
import com.blog.service.TagService;
import com.blog.util.Constant;
import com.blog.util.JsonUtil;
import com.blog.util.Pager;
import com.blog.util.Result;
import com.blog.util.config.CompositeFactory;

/**
 * @desc	后台登录、登出...
 * @author	wlh
 */
@Controller
public class AdminController{

	@Autowired
	private AdminService adminService;
	@Autowired
	private ArticleService articleService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private TagService tagService;

	@Autowired
	private FriendService friendService;
	/**
	 * 跳转首页
	 */
	@RequestMapping("/login")
	public String redirectLogin(HttpSession session) throws Exception{
		// 检查session中用户是否已登录 如果已经登录 则直接跳转后台
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		if (null != manager) {
			// 跳转后台 home
			//			return "redirect:/admin/home";
			return "redirect:/admin/article/list";
		}
		return "/admin/login";
	}
	
	/**
	 * 登录校验
	 */
	@RequestMapping("/admin/login")
	public @ResponseBody 
	Result login(Manager manager,HttpSession session){
		//System.out.println("1");
		// 校验用户名和密码
		if(adminService.validateManager(manager)){
			// 存放用户信息在session中 有效时间60分钟
			manager = adminService.getManagerInfo(manager);
			session.setAttribute(Constant.USERINFO, manager);
			session.setMaxInactiveInterval(3600);
			return new Result("success",Constant.LOGIN_SUCCESS);
		}else{
			return new Result("fail",Constant.LOGIN_FAIL);
		}
	}
	
	/**
	 * 跳转首页
	 */
	@RequestMapping("/admin/home")
	public String home(HttpSession session,ModelMap map){
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		//		return "/admin/home";
		return "redirect:/admin/article/list";
	}

	/**
	 * 登出
	 */
	@RequestMapping("/out")
	public String loginout(HttpSession session,ModelMap map){
		// 清除会话session
		session.removeAttribute(Constant.USERINFO);
		session.invalidate();
		// 跳转登录页
		// 栏目列表
		List<Category> categoryList = categoryService.getCategoryList();
		// 标签列表
		List<Tag> tagList = tagService.getTagList();
		// 合作伙伴列表
		List<Friend> friendList = friendService.getAllFriendList();
		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		map.put("friendList", friendList);
		return "/blog/home";
	}

	@RequestMapping("/admin/editJump")
	public String editJump(ModelMap map,HttpSession session,String id){
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		Manager m = adminService.getManagerById(id);
		map.put("m", m);
		return "/admin/admin_edit";
	}
	
	@RequestMapping("/admin/edit")
	public @ResponseBody Result update(ModelMap map,String password,String userName,String id,HttpSession session) throws UnsupportedEncodingException{
		Manager m = adminService.getManagerById(id);
		m.setUserName(URLDecoder.decode(userName, "utf-8"));
		m.setPassword(URLDecoder.decode(password, "utf-8"));
//		System.out.println(adminService.getManagerInfo(manager));
		// 校验用户名和密码
		Manager m1 = adminService.getManagerByUserName(m.getUserName());
		
		if(m1!=null&&m1.getId()!=m.getId()) {
			return new Result("exist", "用户已存在");
		}
		
		if(adminService.getManagerInfo(m)!=null||adminService.updateManager(m)){
			Manager manager = adminService.getManagerInfo((Manager)session.getAttribute(Constant.USERINFO));
			map.put("manager", manager);
			session.setAttribute(Constant.USERINFO, manager);
			session.setMaxInactiveInterval(3600);
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail",Constant.DEAL_FAIL);
		}
	}
	@RequestMapping(value = "/admin/list")
	public String list(HttpSession session,ModelMap map) {
		// 标签列表
		List<Manager> managerList = adminService.getAllManagerList();	
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		map.put("managerList", managerList);
		return "/admin/admin_list";
	}
	/**
	 * 获取分页列表
	 */
	@RequestMapping(value = "/admin/load")
	public String loadManagerPageList(HttpSession session,ModelMap map,String param,Pager<Manager> pager) throws UnsupportedEncodingException {
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		
		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		//System.out.println(param);
		Manager m = JsonUtil.fromJson(param, Manager.class);
		if(m==null)
			m = new Manager();
		if(!StringUtils.isEmpty(m.getUserName())){
			m.setUserName(URLDecoder.decode(m.getUserName(), "utf-8"));
		}
		paramMap.put("m", m);
		paramMap.put("manager", manager);
		List<Manager> managerList = adminService.getManagerList(paramMap, pager);
		map.put("managerList", managerList);
		map.put("pager", pager);
		return "/admin/admin_pager_list";
	}
	@RequestMapping(value = "/admin/addJump")
	public String addJump(HttpSession session,ModelMap map){
		Manager manager = (Manager)session.getAttribute(Constant.USERINFO);
		map.put("manager", manager);
		return "/admin/admin_add";
	}
	@RequestMapping(value = "/admin/add")
	public @ResponseBody Result addPartner(Manager manager) throws UnsupportedEncodingException {
		manager.setUserName(URLDecoder.decode(manager.getUserName(), "utf-8"));
		manager.setPassword(URLDecoder.decode(manager.getPassword(), "utf-8"));
		if(adminService.getManagerByUserName(manager.getUserName())!=null) {
			return new Result("exist", "用户已存在");
		}
		if(adminService.addManager(manager) > 0){
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}
	@RequestMapping(value = "/admin/delete")
	public @ResponseBody Result deleteManager(String id) {
		
		if(adminService.deleteManager(id) > 0){
			return new Result("success", Constant.DEAL_SUCCESS);
		}else{
			return new Result("fail", Constant.DEAL_FAIL);
		}
	}
	
	/**
	 * 404
	 */
	@RequestMapping("/404")
	public String fourTofour(){
		return "/admin/404";
	}

	/**
	 * 500
	 */
	@RequestMapping("/500")
	public String five(){
		return "/admin/500";
	}

}
