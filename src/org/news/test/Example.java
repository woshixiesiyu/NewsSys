package org.news.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Date;

import org.news.entity.News;
import org.news.service.impl.NewsServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.PrintWriter;
import java.net.URLConnection;

public class Example {
private static String readAll(Reader rd) throws IOException {
StringBuilder sb = new StringBuilder();
int cp;
while ((cp = rd.read()) != -1) {
sb.append((char) cp);
}
return sb.toString();
}

public static JSONObject postRequestFromUrl(String url, String body) throws IOException, JSONException {
URL realUrl = new URL(url);
URLConnection conn = realUrl.openConnection();
conn.setDoOutput(true);
conn.setDoInput(true);
PrintWriter out = new PrintWriter(conn.getOutputStream());
out.print(body);
out.flush();

InputStream instream = conn.getInputStream();
try {
 BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
String jsonText = readAll(rd);
JSONObject json = JSONObject.fromObject(jsonText);
return json;
} finally {
instream.close();
}
}

public static JSONObject getRequestFromUrl(String url) throws IOException, JSONException {
URL realUrl = new URL(url);
URLConnection conn = realUrl.openConnection();
InputStream instream = conn.getInputStream();
try {
BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
String jsonText = readAll(rd);
JSONObject json = JSONObject.fromObject(jsonText);
return json;
} finally {
instream.close();
}
}
public static void main(String[] args) throws IOException, JSONException, SQLException {

// 请求示例 url 默认请求参数已经做URL编码
String url = "http://api01.idataapi.cn:8000/news/toutiao?apikey=zw7a8Msjgw7i6jY3iWan0t3Tu09dfVB4Or6pbqh3ic9IYvenqgqIt23cFqE9gnIZ&catid=news_military&contentType=3&pageToken=0";
//String s="http://api01.idataapi.cn:8000/news/toutiao?apikey=zw7a8Msjgw7i6jY3iWan0t3Tu09dfVB4Or6pbqh3ic9IYvenqgqIt23cFqE9gnIZ&catid=news_world&contentType=3&pageToken=0";
//String url=new String(s.getBytes("UTF-8"),"ISO-8859-1");


//String iso8859 = new String(sb.toString().getBytes("iso8859-1"));
//String gbk = new String(sb.toString().getBytes("gbk"));
//String utf8 = new String(sb.toString().getBytes("utf-8"));
//if(iso8859.equals(sb.toString())){
//    System.out.println("iso8859");
//}else  if(gbk.equals(sb.toString())){
//    System.out.println("gbk");
//}else  if(utf8.equals(sb.toString())){
//    System.out.println("utf8");
//}


JSONObject json = getRequestFromUrl(url);;
JSONArray array=json.getJSONArray("data");
NewsServiceImpl newsServiceImpl=new NewsServiceImpl();
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
	newsServiceImpl.addNews(news);
	
}



}
}