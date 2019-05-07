package org.news.dao;


import java.sql.SQLException;

import org.news.entity.User;

public interface UserDao{
	//查找是否登录成功
	public User findUser(String uname, String password)  throws SQLException;		 
}