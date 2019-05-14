package org.news.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.news.dao.BaseDao;
import org.news.dao.UserDao;
import org.news.entity.User;
import org.news.util.DatabaseUtil;

public class UserDaoImpl extends BaseDao implements UserDao {

    public UserDaoImpl(Connection conn) {
        super(conn);
    }

    public User findUser(String uname, String password) throws SQLException {
        ResultSet rs = null;
        User user = null;
        // 根据用户名密码查找匹配的用户
        String sql = "select * from news_users where uname=? and upwd=?";
        try {
            rs = this.executeQuery(sql, uname, password);
            if (rs.next()) {
                user = new User();
                user.setUid(rs.getInt("uid"));
                user.setUname(uname);
                user.setUpwd(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(null, null, rs);
        }
        return user;
    }
}
