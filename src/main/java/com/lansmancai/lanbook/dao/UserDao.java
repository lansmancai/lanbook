package com.lansmancai.lanbook.dao;

import com.lansmancai.lanbook.vo.User;

/**
 * �û�DAO�ӿ�
 */
public interface UserDao {

	User getUser(String name, String password);
}
