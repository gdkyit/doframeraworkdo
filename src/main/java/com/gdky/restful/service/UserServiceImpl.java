package com.gdky.restful.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdky.restful.dao.MainDaoImpl;
import com.gdky.restful.entity.Role;
import com.gdky.restful.entity.User;
@Service
public class UserServiceImpl {

	@Resource
	MainDaoImpl mainDao;
	
	
	public List<Role> getRolesByUser(String username) {
		// TODO Auto-generated method stub
		return mainDao.getUserRoles(username);
	}

	
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return mainDao.getUser(username);
	}

}
