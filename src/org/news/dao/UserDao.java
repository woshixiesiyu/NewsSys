package org.news.dao;


import java.sql.SQLException;
import java.util.List;

import org.news.entity.User;

public interface UserDao{
	public List<User> getAlluser() throws SQLException ;
	//查找是否登录成功
	public User findUser(String uname, String password)  throws SQLException;
	public void addUser(User user);
	
	public void update(User user);
	public void delete(int uid);
	public User findUserById(int uid);
}