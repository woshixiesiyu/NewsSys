package org.news.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.news.dao.impl.CommentsDaoImpl;
import org.news.entity.Comment;
import org.news.service.CommentsService;
import org.news.util.DatabaseUtil;

public class CommentsServiceImpl implements CommentsService {

    @Override
    public List<Comment> findCommentsByNid(int nid) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            return new CommentsDaoImpl(conn).getCommentsByNid(nid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }

    @Override
    public int addComment(Comment comment) throws SQLException {
        Connection conn = null;
        int result;
        try {
            conn = DatabaseUtil.getConnection();
            conn.setAutoCommit(false);

            result = new CommentsDaoImpl(conn).addComment(comment);

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null)
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
        return result;
    }
    
    @Override
    public int deleteCommentById(int cid) throws SQLException {
        Connection conn = null;
        int result;
        try {
            conn = DatabaseUtil.getConnection();
            conn.setAutoCommit(false);
            
            result = new CommentsDaoImpl(conn).deleteCommentsById(cid);
            
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null)
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
        return result;
    }

}
