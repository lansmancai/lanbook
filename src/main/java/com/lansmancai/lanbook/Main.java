package com.lansmancai.lanbook;

import com.lansmancai.lanbook.dao.UserDao;
import com.lansmancai.lanbook.dao.impl.UserDaoImpl;
import com.lansmancai.lanbook.service.UserService;
import com.lansmancai.lanbook.service.impl.UserServiceImpl;
import com.lansmancai.lanbook.ui.LoginFrame;

/**
 * 程序入口类
 */
public class Main {


	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl();
		UserService userService = new UserServiceImpl(userDao);
		LoginFrame loginFrame = new LoginFrame(userService);
	}

}
