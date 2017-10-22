package com.ziqiang.util.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ziqiang.model.Tag;
import com.ziqiang.service.TagService;
import com.ziqiang.util.Constant;
import com.ziqiang.util.HttpTookit;
import com.ziqiang.util.spring.SpringContext;

/**
 * @desc	tag缓存线程  每隔一段时间自动刷新一次  主要是为了 如果缓存过期  后台自动刷新 不需要用户去点击生成缓存  
 * 			生成缓存的过程体验差
 * @author	ljt
 * @time	2015年8月31日下午8:14:01
 */
public class TagThread implements Runnable{
	
	private static final String HOST_URL = Constant.HOST_URL;
	
	private TagService tagService;
	
	private String lastUpdateTime;
	
	public void run() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tagService = SpringContext.getBean("tagService");
		
		while(true){
			if(lastUpdateTime == null){
				updateTagCache();
				lastUpdateTime = df.format(new Date());
			}else{
				try {
					long now = new Date().getTime();
					long before = df.parse(lastUpdateTime).getTime();
					long l= now-before;
					if(l > 600*1000){  // 超过1分钟自动更新
						updateTagCache();
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

	private void updateTagCache() {
		// loadPage/-1/99999999999
		// 查询所有的tag
		// 请求每个分类的首分页数据
		List<Tag> tagList = tagService.getTagList();
		for (Tag tag : tagList) {
			// 调用自身接口
			String tagPageUrl = HOST_URL
					+ "/tag/loadPage/" + tag.getId()
					+ "/99999999999";
			HttpTookit.doGet(tagPageUrl, null);
		}
	}
	
}
