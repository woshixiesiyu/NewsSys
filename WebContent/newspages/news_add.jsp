<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加主题--管理后台</title>
<link href="../css/admin.css" rel="stylesheet" type="text/css" />
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div id="header">
  <div id="welcome">欢迎使用新闻管理系统！</div>
  <div id="nav">
    <div id="logo"><img src="../images/logo.jpg" alt="新闻中国" /></div>
    <div id="a_b01"><img src="../images/a_b01.gif" alt="" /></div>
  </div>
</div>
<div id="admin_bar">
  <div id="status">管理员：${user.uname} 登录  &#160;&#160;&#160;&#160; <a href="http://localhost:8080/NewsSys/util/user?opr=logout">退出登录</a>&#160;&#160;&#160;&#160;<a href="http://localhost:8080/NewsSys">返回首页</a></div>
  <div id="channel"> </div>
</div>
<div id="main">
  <div id="opt_list">
  <ul class="list-group">
  	<li>新闻管理</li>
    <li><a href="../util/news?opr=toAddNews">添加新闻</a></li>
     <c:choose>
    <c:when test="${user.role eq '管理员' }">
    <li><a href="../util/news?opr=list">编辑新闻</a></li>
    </c:when>
    <c:otherwise>
    	<li><a href="../util/news?opr=editorlist">编辑新闻</a></li>
    </c:otherwise>
    </c:choose>
    <li><a href="../newspages/topic_add.jsp">添加主题</a></li>
    <li><a href="../util/topics?opr=list">编辑主题</a></li>
    
    <c:choose>
    <c:when test="${user.role eq '管理员' }">
    
    	<li><a href="../util/news?opr=autoGet">自动获取新闻源</a></li>
    	<li>用户管理</li>
    	<li><a href="../util/user?opr=user_per">用户信息管理</a></li>
    	<li><a href="../newspages/editor_add.jsp">添加新闻编辑员</a></li>
    </c:when>
    </c:choose>
  </ul>
</div>
  <div id="opt_area">
    <h1 id="opt_type"> 手动添加新闻： </h1>
    <form action="../util/news?opr=addNews&role=${user.role }" method="post" enctype="multipart/form-data">
      <p>
        <label> 主题 </label>
        <select name="ntid">
          <option value=""></option>
          <c:forEach items="${requestScope.topics}" var="topic">
            <option value='${topic.tid}'>${topic.tname}</option>
          </c:forEach>
        </select>
      </p>
      <p>
        <label> 标题 </label>
        <input name="ntitle" type="text" class="opt_input" />
      </p>
      <p>
        <label> 作者 </label>
        <input name="nauthor" type="text" class="opt_input" />
      </p>
      <p>
        <label> 摘要 </label>
        <textarea name="nsummary" cols="40" rows="3"></textarea>
      </p>
      <p>
        <label> 内容 </label>
        <textarea name="ncontent" cols="70" rows="10"></textarea>
      </p>
      <p>
        <label> 上传图片 </label>
        <input name="file" type="file" class="opt_input" />
      </p>
      <input type="submit" value="提交" class="opt_sub" />
      <input type="reset" value="重置" class="opt_sub" />
    </form>
  </div>
</div>
<div id="footer">
  <%@ include file="console_element/bottom.html" %>
</div>
</body>
</html>
