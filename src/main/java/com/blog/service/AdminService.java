package com.blog.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.AdminMapper;
import com.blog.model.Friend;
import com.blog.model.Manager;
import com.blog.model.Tag;
import com.blog.util.MD5;
import com.blog.util.Pager;
import com.blog.util.cache.EhcacheUtil;

@Service
@Transactional(isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class AdminService {

	@Autowired
	private AdminMapper adminMapper;

	public boolean validateManager(Manager manager) {
		return adminMapper.validateManager(manager) > 0 ? true : false;	
	}

	public Manager getManagerInfo(Manager manager) {
		return adminMapper.getManagerInfo(manager);
	}
	
	public boolean updateManager(Manager manager) {
		return adminMapper.updateManager(manager) > 0 ? true : false;
	}	
	
	public List<Manager> getManagerList(HashMap<String, Object> paramMap){
		List<Manager> managerList = (List<Manager>)EhcacheUtil.get("defaultCache", "managerList");
		if(managerList == null){
			managerList = adminMapper.getAllManagerList();
			EhcacheUtil.put("defaultCache", "managerList",managerList);
		}
		return managerList;
	}
	public List<Manager> getManagerList(HashMap<String, Object> paramMap,Pager<Manager> pager){
		List<Manager> managerList = null;
		int count = adminMapper.getManagerCount(paramMap);
		pager.setTotalCount(count);
		if(count > 0){
			paramMap.put("start", pager.getStart());
			paramMap.put("limit", pager.getLimit());
			managerList = adminMapper.getManagerList(paramMap);
		}
		return managerList;
	}
	public List<Manager> getAllManagerList(){
		return adminMapper.getAllManagerList();
	}

	public int deleteManager(String id) {
		// TODO Auto-generated method stub
		return adminMapper.deleteManager(id);
	}

	public Manager getManagerById(String id) {
		// TODO Auto-generated method stub
		return adminMapper.getManagerById(id);
	}
	public int addManager(Manager manager) {
		return adminMapper.addManager(manager);
	}

	public Manager getManagerByUserName(String userName) {
		// TODO Auto-generated method stub
		return adminMapper.getManagerByUserName(userName);
	}
}
