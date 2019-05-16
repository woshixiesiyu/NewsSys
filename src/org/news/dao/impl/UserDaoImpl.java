package org.news.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.news.dao.BaseDao;
import org.news.dao.UserDao;
import org.news.entity.News;
import org.news.entity.User;
import org.news.util.DatabaseUtil;

public class UserDaoImpl extends BaseDao implements UserDao {

    public UserDaoImpl(Connection conn) {
        super(conn);
    }

    public List<User> getAlluser() throws SQLException {
        List<User> list = new ArrayList<User>();
        ResultSet rs = null;
        String sql = "select *from news_users";
        try {
            rs = this.executeQuery(sql);
            User user = null;
            while (rs.next()) {
                user=new User();
                user.setUid(rs.getInt("uid"));
                user.setUname(rs.getString("uname"));
                user.setUpwd(rs.getString("upwd"));
                user.setRole(rs.getString("role"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(null, null, rs);
        }
        return list;
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
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(null, null, rs);
        }
        return user;
    }

	@Override
	public void update(User user) {
	
		String sql="update news_users set uname=?,upwd=? where uid=?";
		try {
			this.executeUpdate(sql, user.getUname(),user.getUpwd(),user.getUid());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(null,null, null);
			
		}
		
		
		
	}

	@Override
	public void delete(int uid) {
		String sql="delete from news_users where uid=?";
		try {
			this.executeUpdate(sql, uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(null,null, null);
			
		}
		
		
	}

	@Override
	public User findUserById(int uid) {

		 	ResultSet rs = null;
	        User user = null;
	        // 根据用户id查找匹配的用户
	        String sql = "select * from news_users where uid = ?";
	        try {
	            rs = this.executeQuery(sql, uid);
	            if (rs.next()) {
	                user = new User();
	                user.setUid(rs.getInt("uid"));
	                user.setUname(rs.getString("uname"));
	                user.setUpwd(rs.getString("upwd"));
	                user.setRole(rs.getString("role"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DatabaseUtil.closeAll(null, null, rs);
	        }
	        return user;
	    }

	@Override
	public void addUser(User user) {
		String sql="insert into news_users(uname,upwd,role) values(?,?,?)";
		try {
			this.executeUpdate(sql,new Object[] {user.getUname(),user.getUpwd(),user.getRole()});
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(null,null, null);
			
		}
		
		
	}
}
