package com.lansmancai.lanbook.service.impl;

import com.lansmancai.lanbook.commons.BusinessException;
import com.lansmancai.lanbook.dao.UserDao;
import com.lansmancai.lanbook.service.UserService;
import com.lansmancai.lanbook.vo.User;

/**
 * 用户业务实现类
 * 
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void login(String name, String password) {
		User user = userDao.getUser(name, password);
		if (user == null) {
			throw new BusinessException("用户名密码错误");
		}
	}

}
