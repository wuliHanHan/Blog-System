package com.ziqiang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziqiang.mapper.FriendMapper;
import com.ziqiang.model.Friend;

@Service
public class FriendService {
	@Autowired
	private FriendMapper friendMapper;
	
	public List<Friend> getAllFriendList() {
		return friendMapper.getAllFriendList();
	}

}
