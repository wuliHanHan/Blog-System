package com.blog.util.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.blog.model.Category;
import com.blog.service.CategoryService;
import com.blog.util.Constant;
import com.blog.util.HttpTookit;
import com.blog.util.spring.SpringContext;

/**
 * @desc	栏目首页缓存线程  每隔一段时间自动刷新一次  主要是为了 如果缓存过期  后台自动刷新 不需要用户去点击生成缓存  
 * 			生成缓存的过程体验差
 * @author	wlh
 */
public class CategoryThread implements Runnable{
	
	private static final String HOST_URL = Constant.HOST_URL;
	
	private CategoryService categoryService;
	
	private String lastUpdateTime;
	
	public void run() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		categoryService = SpringContext.getBean("categoryService");
		
		while(true){
			if(lastUpdateTime == null){
				updateCategoryCache();
				lastUpdateTime = df.format(new Date());
			}else{
				try {
					long now = new Date().getTime();
					long before = df.parse(lastUpdateTime).getTime();
					long l= now-before;
					if(l > 600*1000){  // 超过1分钟自动更新
						updateCategoryCache();
						lastUpdateTime = df.format(new Date());
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void updateCategoryCache() {
		// loadPage/-1/99999999999
		// 查询所有的分类
		// 请求每个分类的首分页数据
		List<Category> categoryList = categoryService.getCategoryList();
		Category home = new Category();
		home.setId(-1);
		categoryList.add(home);
		
		// 首页缓存
		String homeUrl = HOST_URL
				+ "/";
		HttpTookit.doGet(homeUrl, null);
		
		for (Category category : categoryList) {
			// 调用自身接口
			String categoryPageUrl = HOST_URL
					+ "/article/loadPage/" + category.getId().intValue()
					+ "/99999999999";
			HttpTookit.doGet(categoryPageUrl, null);
			
			if(category.getAliasName() != null){
				String categoryUrl = HOST_URL
						+ "/category/"+category.getAliasName();
				HttpTookit.doGet(categoryUrl, null);
			}
		}
	}
	
}
