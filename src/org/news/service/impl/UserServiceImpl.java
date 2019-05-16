package org.news.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.news.dao.impl.UserDaoImpl;
import org.news.entity.User;
import org.news.service.UserService;
import org.news.util.DatabaseUtil;

public class UserServiceImpl implements UserService {

    @Override
    public User doLogin(User user) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            return new UserDaoImpl(conn).findUser(user.getUname(),
                    user.getUpwd());
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }
    
    public List<User> getAlluser() throws SQLException{
		Connection conn=null;
		conn=DatabaseUtil.getConnection();	
    	return new UserDaoImpl(conn).getAlluser();
    	
    	
    }

	@Override
	public void update(User user) throws SQLException {
		Connection conn=null;
		conn=DatabaseUtil.getConnection();	
		new UserDaoImpl(conn).update(user);
		
		
	}

	@Override
	public void delete(int uid) throws SQLException {
		
		Connection conn=null;
		conn=DatabaseUtil.getConnection();	
		new UserDaoImpl(conn).delete(uid);
		
	}

	@Override
	public User findUserById(int uid) throws SQLException {
		Connection conn=null;
		conn=DatabaseUtil.getConnection();
		
		
		return new UserDaoImpl(conn).findUserById(uid);
	}

	@Override
	public void register(User user) throws SQLException {
		Connection conn=null;
		conn=DatabaseUtil.getConnection();	
		new UserDaoImpl(conn).addUser(user);
		
		
		
		
	}
   

}
