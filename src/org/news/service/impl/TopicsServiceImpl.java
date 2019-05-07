package org.news.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.news.dao.NewsDao;
import org.news.dao.TopicsDao;
import org.news.dao.impl.NewsDaoImpl;
import org.news.dao.impl.TopicsDaoImpl;
import org.news.entity.Topic;
import org.news.service.TopicsService;
import org.news.util.DatabaseUtil;

public class TopicsServiceImpl implements TopicsService {

    @Override
    public List<Topic> findAllTopics() throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();
            
            return new TopicsDaoImpl(conn).getAllTopics();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }

    @Override
    public int updateTopic(Topic topic) throws SQLException {
        Connection conn = null;
        int result;
        try {
            conn = DatabaseUtil.getConnection();
            conn.setAutoCommit(false);

            TopicsDao topicsDao = new TopicsDaoImpl(conn);
            
            if (topicsDao.findTopicByName(topic.getTname()) == null) {
                result = topicsDao.updateTopic(topic);
            } else {
                result = -1;
            }

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
    public Topic findTopicByName(String name) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();
            
            return new TopicsDaoImpl(conn).findTopicByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }

    @Override
    public int addTopic(String name) throws SQLException {
        Connection conn = null;
        int result;
        try {
            conn = DatabaseUtil.getConnection();
            conn.setAutoCommit(false);

            TopicsDao topicsDao = new TopicsDaoImpl(conn);
            
            if (topicsDao.findTopicByName(name) == null) {
                result = topicsDao.addTopic(name);
            } else {
                result = -1;
            }

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
    public int deleteTopic(int tid) throws SQLException {
        Connection conn = null;
        int result;
        try {
            conn = DatabaseUtil.getConnection();
            conn.setAutoCommit(false);

            NewsDao newsDao = new NewsDaoImpl(conn);
            TopicsDao topicsDao = new TopicsDaoImpl(conn);
            
            if (newsDao.getNewsCountByTID(tid) == 0) {
                result = topicsDao.deleteTopic(tid);
            } else {
                result = -1;
            }

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
