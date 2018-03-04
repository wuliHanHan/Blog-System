package com.blog.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.FriendMapper;
import com.blog.model.Friend;
import com.blog.util.Pager;
import com.blog.util.cache.EhcacheUtil;

@Service
@Transactional(isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
public class FriendService{
	
	@Autowired
	private FriendMapper friendMapper;
	
	public List<Friend> getFriendList(HashMap<String, Object> paramMap, Pager<Friend> pager) {
		List<Friend> friendList = null;
		int count = friendMapper.getFriendCount(paramMap);
		pager.setTotalCount(count);
		if(count > 0){
			paramMap.put("start", pager.getStart());
			paramMap.put("limit", pager.getLimit());
			friendList = friendMapper.getFriendList(paramMap);
		}
		return friendList;
	}
	
	public int addFriend(Friend Friend) {
		return friendMapper.addFriend(Friend);
	}
	
	public int editFriend(Friend Friend) {
		return friendMapper.editFriend(Friend);
	}
	
	public Friend getFriendById(String id) {
		return friendMapper.getFriendById(id);
	}
	
	public int deleteFriend(String id) {
		return friendMapper.deleteFriend(id);
	}

	public List<Friend> getAllFriendList() {
		List<Friend> friendList = (List<Friend>)EhcacheUtil.get("defaultCache", "friendList");
		if(friendList == null){
			friendList = friendMapper.getAllFriendList();
			EhcacheUtil.put("defaultCache", "friendList",friendList);
		}
		return friendList;
	}

	public Friend getBySiteName(String siteName) {
		// TODO Auto-generated method stub
		return friendMapper.getBySiteName(siteName);
	}
	
}
