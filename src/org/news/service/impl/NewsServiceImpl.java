package org.news.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.news.dao.NewsDao;
import org.news.dao.impl.CommentsDaoImpl;
import org.news.dao.impl.NewsDaoImpl;
import org.news.entity.News;
import org.news.service.NewsService;
import org.news.util.DatabaseUtil;
import org.news.util.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NewsServiceImpl implements NewsService {

    @Override
    public List<News> findAllNews() throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            return new NewsDaoImpl(conn).getAllnews();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }

    @Override
    public List<News> findAllNewsByTid(int tid) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            return new NewsDaoImpl(conn).getAllnewsByTID(tid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }

    @Override
    public List<News> findAllNewsByTname(String tname) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            return new NewsDaoImpl(conn).getAllnewsByTname(tname);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }

    @Override
    public List<News> findLatestNewsByTid(int tid, int limit)
            throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            return new NewsDaoImpl(conn).getLatestNewsByTID(tid, limit);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }

    @Override
    public List<List<News>> findLatestNewsByTid(Map<Integer, Integer> topicsMap)
            throws SQLException {
        Connection conn = null;
        try {
            List<List<News>> result = null;
            if (topicsMap != null && topicsMap.size() != 0) {
                conn = DatabaseUtil.getConnection();
                NewsDao newsDao = new NewsDaoImpl(conn);

                result = new ArrayList<List<News>>();
                Iterator<Entry<Integer, Integer>> topics = topicsMap.entrySet()
                        .iterator();
                while (topics.hasNext()) {
                    Entry<Integer, Integer> oneTopic = topics.next();
                    result.add(newsDao.getLatestNewsByTID(oneTopic.getKey(),
                            oneTopic.getValue()));
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }

    @Override
    public News findNewsByNid(int nid) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            return new NewsDaoImpl(conn).getNewsByNID(nid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }

    @Override
    public int deleteNews(int nid) throws SQLException {
        Connection conn = null;
        int result;
        try {
            conn = DatabaseUtil.getConnection();
            conn.setAutoCommit(false);
            // 删除相关评论
            new CommentsDaoImpl(conn).deleteCommentsByNid(nid);
            // 删除新闻
            result = new NewsDaoImpl(conn).deleteNews(nid);
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

    // 分页获取新闻
    public void findPageNews(Page pageObj) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            NewsDao newsDao = new NewsDaoImpl(conn);
            int totalCount = newsDao.getTotalCount();
            pageObj.setTotalCount(totalCount); // 设置总数量并计算总页数
            if (totalCount > 0) {
                if (pageObj.getCurrPageNo() > pageObj.getTotalPageCount())
                    pageObj.setCurrPageNo(pageObj.getTotalPageCount());
                List<News> newsList = newsDao.getPageNewsList(
                        pageObj.getCurrPageNo(), pageObj.getPageSize());
                pageObj.setNewsList(newsList);
            } else {
                pageObj.setCurrPageNo(0);
                pageObj.setNewsList(new ArrayList<News>());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }

    @Override
    public int addNews(News news) throws SQLException {
        Connection conn = null;
        int result;
        try {
            conn = DatabaseUtil.getConnection();
            conn.setAutoCommit(false);
            // 添加新闻
            news.setNcreatedate(new Date());
            news.setNmodifydate(news.getNcreatedate());
            result = new NewsDaoImpl(conn).addNews(news);
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
    public int modifyNews(News news) throws SQLException {
        Connection conn = null;
        int result;
        try {
            conn = DatabaseUtil.getConnection();
            conn.setAutoCommit(false);
            // 添加新闻
            news.setNmodifydate(new Date());
            result = new NewsDaoImpl(conn).updateNews(news);
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
    
    private String readAll(Reader rd) throws IOException {
    	StringBuilder sb = new StringBuilder();
    	int cp;
    	while ((cp = rd.read()) != -1) {
    	sb.append((char) cp);
    	}
    	return sb.toString();
    	}
    
    public void autoGetNews(String catid) throws IOException {
    	System.out.println(catid);
    	String sbcatid=new String(catid.getBytes(),"utf-8");
//    	String url = "http://api01.idataapi.cn:8000/news/toutiao?apikey=zw7a8Msjgw7i6jY3iWan0t3Tu09dfVB4Or6pbqh3ic9IYvenqgqIt23cFqE9gnIZ&catid="+catid+"&contentType=3&pageToken=0";
    	String url = "http://api01.idataapi.cn:8000/news/toutiao?apikey=zw7a8Msjgw7i6jY3iWan0t3Tu09dfVB4Or6pbqh3ic9IYvenqgqIt23cFqE9gnIZ&catid="+sbcatid+"&contentType=3&pageToken=0";
    	System.out.println(url);
    	URL realUrl = new URL(url);
    	URLConnection conn = realUrl.openConnection();
    	InputStream instream = conn.getInputStream();
    	try {
    	BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
    	String jsonText = readAll(rd);
    	JSONObject json = JSONObject.fromObject(jsonText);
    	JSONArray array=json.getJSONArray("data");
    	for(int i=0;i<array.size();i++) {
    		News news=new News();	
    		JSONObject jsonobj=array.getJSONObject(i);
    		news.setNcreatedate(new Date());
    		news.setNtid(3);
    		news.setNauthor(jsonobj.getString("posterScreenName"));
    		news.setNtitle(jsonobj.getString("title"));
    		news.setNcontent(jsonobj.getString("content"));
    		news.setNsummary(jsonobj.getString("title"));
    		if(jsonobj.getJSONArray("imageUrls").isArray()) {
    			JSONArray imgarr=jsonobj.getJSONArray("imageUrls");
    			news.setNpicpath(imgarr.getString(0));
    		}
    		try {
				addNews(news);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		
    	}
    	
    	
    	
    	} finally {
    	instream.close();
    	}
    	}

	@Override
	public List<News> findAllNewsByEditor(String uname) throws SQLException {
		Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            return new NewsDaoImpl(conn).findAllNewsByEditor(uname);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }


	}
    	
   
    
    
}
