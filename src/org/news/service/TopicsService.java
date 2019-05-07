package org.news.service;

import java.sql.SQLException;
import java.util.List;
import org.news.entity.Topic;

public interface TopicsService {
    // 获取所有主题
    public List<Topic> findAllTopics() throws SQLException;
    // 更新所有主题
    public int updateTopic(Topic topic) throws SQLException;
    // 根据名字查找主题
    public Topic findTopicByName(String name) throws SQLException;
    // 添加主题
    public int addTopic(String name) throws SQLException;
    // 通过tid删除主题
    public int deleteTopic(int tid) throws SQLException;
}
