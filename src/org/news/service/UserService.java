package org.news.service;

import java.sql.SQLException;

import org.news.entity.User;

public interface UserService {
    public User doLogin(User user) throws SQLException;
}
