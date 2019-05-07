package org.news.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.news.dao.NewsDao;
import org.news.dao.impl.NewsDaoImpl;
import org.news.entity.News;
import org.news.util.DatabaseUtil;
import org.news.util.Page;

public class PageTest {

    public void pageTest() {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            NewsDao newsDao = new NewsDaoImpl(conn);
            int totalCount = newsDao.getTotalCount();
            Page page=new Page();
            page.setCurrPageNo(3);              //设置当前页面
            page.setPageSize(5);                //设置每页条数
            page.setTotalCount(totalCount);     //设置总数量
            System.out.println("新闻总数量是：" + page.getTotalCount());
            System.out.println("每页条数是：" + page.getPageSize());
            System.out.println("总页数：" + page.getTotalPageCount());
            System.out.println("当前是第" + page.getCurrPageNo() + "页：");
            List<News> newsList = newsDao
                .getPageNewsList(page.getCurrPageNo(), page.getPageSize());
            page.setNewsList(newsList);
            for (News news : page.getNewsList()) {
                System.out.println(news.getNid() + "\t" + news.getNtitle()
                                    + "\t" + news.getNcreatedate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }
}
