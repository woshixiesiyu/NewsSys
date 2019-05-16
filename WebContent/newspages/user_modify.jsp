<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<%@include file="console_element/top.jsp" %>

<script type="text/javascript">
	function check(){
		var uname = document.getElementById("uname");
		var upwd = document.getElementById("upwd");

		if(uname.value == ""){
			alert("用户名不能为空！！");
			ntitle.focus();
			return false;
		}else if(upwd.value == ""){
			alert("密码不能为空！！");
			nauthor.focus();
			return false;
		}
		return true;
	}
</script>
<div id="admin_bar">
  <div id="status">管理员：${user.uname }登录  &#160;&#160;&#160;&#160; <a href="http://localhost:8080/NewsSys/util/user?opr=logout">退出登录</a>&#160;&#160;&#160;&#160;<a href="http://localhost:8080/NewsSys">返回首页</a></div>
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
			<li><a href="../util/news?opr=autoGet">自动获取新闻源</a></li>
			<li>用户管理</li>
			<li><a href="../util/user?opr=user_per">用户信息管理</a></li>
			<li><a href="../newspages/editor_add.jsp">添加新闻编辑员</a></li>
		</ul>
</div>
  <div id="opt_area">
    <h1 id="opt_type"> 编辑用户： </h1>
    <form action="../util/user?opr=update"
          method="post"  onsubmit="return check()">
     
     	<input  type="hidden" name="uid" value="${requestScope.user.uid}"/>
      <p>
        <label> 用户名 </label>
        <input name="uname" id="uname" type="text"  value="${requestScope.user.uname}"/>
      </p>
      <p>
        <label> 密码 </label>
        <input name="upwd" id="upwd" type="text"  value="${requestScope.user.upwd}"/>
      </p>

      <input type="submit" value="提交" class="opt_sub" />
      <input type="reset" value="重置" class="opt_sub" />
    </form>
   
  </div>
</div>
<%--
	request.removeAttribute("news");
	request.removeAttribute("topics");
--%>
<%@ include file="console_element/bottom.html" %>