package com.blog.dao;

import java.util.HashMap;
import java.util.List;

import com.blog.model.Friend;
import com.blog.model.Manager;
import com.blog.model.Tag;
import com.blog.util.Pager;

public interface AdminMapper {

	Manager getManagerByUserName(String userName);
	
	int validateManager(Manager manager);

	Manager getManagerInfo(Manager manager);
	
	int addManager(Manager manager);
	
	int updateManager(Manager manager);
	
	List<Manager> getManagerList(HashMap<String, Object> paramMap);
	
	List<Manager> getManagerList(HashMap<String, Object> paramMap,Pager<Manager> pager);
	
	List<Manager> getAllManagerList();
	
	int getManagerCount(HashMap<String, Object> paramMap);

	int deleteManager(String id);
	
	Manager getManagerById(String id);
}
