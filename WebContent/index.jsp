<%@page import="org.news.util.Page"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
<c:when test="${requestScope.list1 == null && requestScope.list2 == null && requestScope.list3 == null}">
    <jsp:forward page="/util/news?opr=listTitle"></jsp:forward>
</c:when>
<c:otherwise>
<c:set var="totalPages" value="${requestScope.pageObj.totalPageCount}"/>
<c:set var="pageIndex" value="${requestScope.pageObj.currPageNo}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻中国</title>
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function check(){
		var login_username = document.getElementById("uname");
		alert(login_username+"sadfsdf");
		var login_password = document.getElementById("upwd");
		if(login_username.value == ""){
			alert("用户名不能为空！请重新填入！");
			login_username.focus();	
			return false;
		}else if(login_password.value == ""){
			alert("密码不能为空！请重新填入！");
			login_password.focus();
			return false;
		}
		return true;
	}
	
	function focusOnLogin(){
		var login_username = document.getElementById("uname");
		login_username.focus();	
	}
</script>
</head>

<body onload="focusOnLogin()">
  
<div id="header">

 <nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="http://localhost:8080/NewsSys/">新闻中国</a>
    </div>
    
     <form class="form-inline navbar-left" role="form" action="util/news?opr=search" method="post"  style="margin-top: 8px;;">
  	<div class="form-group">
    <label class="sr-only" for="name">关键字</label>
    <input type="text" class="form-control" id="name" placeholder="请输入关键字搜索新闻" name="keyname">
  </div>
  <button type="submit" class="btn btn-default">Search</button>
	</form>
    
    <div >
         <c:choose>
      	<c:when test="${not empty sessionScope.user.uname}">
      		<div>
        		<p class="navbar-text navbar-right">欢迎&nbsp;&nbsp;<span>${sessionScope.user.uname}</span>&nbsp;&nbsp;登录本网站&nbsp;&nbsp;<a href="util/user?opr=logout">退出账号</a>&nbsp;&nbsp;</p>
    		</div>
      	
      	
      	</c:when>
      	
      	<c:otherwise>
    
        <form class="navbar-form navbar-right" role="search" action="util/user" method="post"  >
        <input type="hidden" name="opr" value="login"/>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="用户名" name="uname" id="uname">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="密码" name="upwd" id="upwd">
            </div>
            <button type="submit" class="btn btn-default">登录</button>&#160;&#160;
            <a href="#" data-toggle="modal" data-target="#myModal">注册账号</a>
        </form>
		</c:otherwise>

</c:choose>
    </div>
    </div>
</nav> 
  <div id="nav">
   <!--  <div id="logo"> <img src="images/logo.jpg" alt="新闻中国" /> </div> -->
    <!--mainnav end-->
  </div>
</div>
<div id="container">

<%@ include file="index-elements/index_sidebar.jsp"%>

<script type="text/javascript">
	function check(){
		var register_username = document.getElementById("r_uname");
		var register_password = document.getElementById("r_upwd");
		if(register_username.value == ""){
			alert("用户名不能为空！请重新填入！");
			register_username.focus();	
			return false;
		}else if(register_password.value == ""){
			alert("密码不能为空！请重新填入！");
			register_password.focus();
			return false;
		}
		return true;
	}
	
	function focusOnLogin(){
		var register_username = document.getElementById("r_uname");
		register_username.focus();	
	}
</script>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">注册账号</h4>
            </div>
           
            <form action="util/user" method="post" onsubmit="return check()">
						<input type="hidden" name="opr" value="register"/>
						<div class="form-group" >
							<label for="name">用户名</label> <input type="text"
								class="form-control" placeholder="文本输入" id="r_uname" name="uname">
						</div>
						<div class="form-group" >
							<label for="name">密码</label> <input type="password"
								class="form-control" placeholder="文本输入" id="r_upwd" name="upwd">
						</div>

						<div class="modal-footer">
							<button type="reset" class="btn btn-default"
								data-dismiss="modal">重置</button>
							<button type="submit" class="btn btn-success">确认注册</button>
						</div>

		   </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>









 
  <div class="main">
    <!-- <div class="class_type"> <h1>新闻中心</h1> </div> -->
    
   
    <div class="content">
      <ul class="class_date">
      <c:forEach items="${requestScope.list}" var="topic" varStatus="i">
        <c:if test="${i.count % 11 == 1}"><li id='class_month'></c:if>
        <a  class="label label-info" style="display:inline-block;margin-bottom: 2px;font-size: 110%" href="util/news?opr=listTitle&tid=${topic.tid}"><b>${topic.tname}</b></a>
        <c:if test="${i.count % 11 == 0}"></li></c:if>
        <c:set var="n" value="${i.count}"/>
      </c:forEach>
      <c:if test="${n % 11 != 0}"></li></c:if>
      </ul>
      <ul class="classlist">
      <c:choose>
        <c:when test="${requestScope.list4 == null}"><h6>出现错误，请稍后再试或与管理员联系</h6></c:when>
        <c:when test="${empty requestScope.list4}"><h6>抱歉，没有找到相关的新闻</h6></c:when>
        <c:otherwise>
          <c:forEach items="${requestScope.list4}" var="news" varStatus="i">
            <li>
            <a href='util/news?opr=readNew&nid=${news.nid}'>${news.ntitle}</a>
            <span style="font-size: 10px"><fmt:formatDate value="${news.ncreatedate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            </li>
            <c:if test="${i.count % 5 == 0}"><li class='space'></li></c:if>
          </c:forEach>
        </c:otherwise>
      </c:choose>
      <p align="center"> 当前页数:[${pageIndex}/${totalPages}]&nbsp;
      <c:if test="${pageIndex > 1}">   
          <a href="util/news?opr=listTitle&pageIndex=1">首页</a>&nbsp;
          <a href="util/news?opr=listTitle&pageIndex=${pageIndex - 1}">上一页</a>
      </c:if>
      <c:if test="${pageIndex < totalPages}">
          <a href="util/news?opr=listTitle&pageIndex=${pageIndex + 1}">下一页</a>
          <a href="util/news?opr=listTitle&pageIndex=${totalPages}">末页</a> 
      </c:if></p>
      </ul>
    </div>
    <!--<%@ include file="index-elements/index_rightbar.html"%>-->
  </div>
</div>

<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>
</c:otherwise>
</c:choose>