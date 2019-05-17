package org.news.test;

import java.sql.SQLException;
import java.util.List;

import org.news.entity.News;
import org.news.service.impl.NewsServiceImpl;

public class test {

	public static void main(String[] args) throws SQLException {
		List<News> list=new NewsServiceImpl().findAllNewsByKey("巴");
		
		for(News news:list) {
			
			System.out.println(news.getNtitle());
			
		}

	}

}
