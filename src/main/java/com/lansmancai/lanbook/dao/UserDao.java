package com.lansmancai.lanbook.dao;

import com.lansmancai.lanbook.vo.User;

/**
 * 用户DAO接口
 */
public interface UserDao {

	User getUser(String name, String password);
}
