package com.lansmancai.lanbook.service.impl;

import com.lansmancai.lanbook.commons.BusinessException;
import com.lansmancai.lanbook.dao.UserDao;
import com.lansmancai.lanbook.service.UserService;
import com.lansmancai.lanbook.vo.User;

/**
 * �û�ҵ��ʵ����
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
			throw new BusinessException("�û����������");
		}
	}

}
