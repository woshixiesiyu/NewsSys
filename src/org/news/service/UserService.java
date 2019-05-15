package org.news.service;

import java.sql.SQLException;
import java.util.List;

import org.news.entity.User;

public interface UserService {
    public User doLogin(User user) throws SQLException;

	public List<User> getAlluser() throws SQLException;
}
