package com.ziqiang.mapper;

import com.ziqiang.model.Manager;

public interface AdminMapper {

	int validateManager(Manager manager);

	Manager getManagerInfo(Manager manager);

}
