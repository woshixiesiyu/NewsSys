<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>添加主题--管理后台</title>
<link href="../css/admin.css" rel="stylesheet" type="text/css" />
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div id="header">
  <div id="welcome">欢迎使用新闻管理系统！</div>
  <div id="nav">
    <div id="logo"><img src="../images/logo.jpg" alt="新闻中国" /></div>
  
  </div>
</div>
<div id="admin_bar">
  <div id="status">管理员：${user.uname } 登录  &#160;&#160;&#160;&#160; <a href="http://localhost:8080/NewsSys/util/user?opr=logout">退出登录</a>&#160;&#160;&#160;&#160;<a href="http://localhost:8080/NewsSys">返回首页</a></div>
  <div id="channel"> </div>
</div>
<div id="main">
  <div id="opt_list">
 
  <ul class="list-group">
  	<li>新闻管理</li>
    <li><a href="../util/news?opr=toAddNews">添加新闻</a></li>
    <li><a href="../util/news?opr=list">编辑新闻</a></li>
    <li><a href="../newspages/topic_add.jsp">添加主题</a></li>
    <li><a href="../util/topics?opr=list">编辑主题</a></li>
   
  </ul>
</div>
  <div id="opt_area">    
    <script type="text/javascript" >
    function clickdel(nid,role){
        if (confirm("此新闻的相关评论也将删除，确定删除吗？"))
            window.location="../util/news?opr=delete&nid="+nid+"&role="+role;
    }
	
</script>
    <ul class="classlist">
      <c:forEach items="${sessionScope.editorlist}" var="news" varStatus="i">
	    <li>${news.ntitle}<span> 作者：${news.nauthor} &#160;&#160;&#160;&#160; 
<a href='../util/news?opr=toModifyNews&nid=${news.nid}'>修改</a> &#160;&#160;&#160;&#160; 
<a href="../util/news?opr=delete&nid=${news.nid}&role=${user.role}">删除</a></span> </li>
	    <c:if test="${i.count % 5 == 0}">
          <li class='space'></li>
        </c:if>
	  </c:forEach>
	  <c:remove var="list" scope="session"/>
    </ul>
  </div>
</div>
<div id="footer">
  <%@ include file="console_element/bottom.html" %>
</div>
</body>
</html>