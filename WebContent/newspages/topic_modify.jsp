<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<%@ include file="console_element/top.jsp" %>
<script type="text/javascript">
	function check(){
		var tname = document.getElementById("tname");

		if(tname.value == ""){
			alert("请输入主题名称！！");
			tname.focus();
			return false;
		}		
		return true;
	}
</script>
<div id="admin_bar">
  <div id="status">管理员：${user.uname } 登录  &#160;&#160;&#160;&#160; <a href="http://localhost:8080/NewsSys/util/user?opr=logout">退出登录</a>&#160;&#160;&#160;&#160;<a href="http://localhost:8080/NewsSys">返回首页</a></div>
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
    <h1 id="opt_type"> 修改主题： </h1>
    <form action="../util/topics?opr=update" method="post" onsubmit="return check()">
      <p>
        <label> 主题名称 </label>
        <input name="tname" type="text" class="opt_input" value="${param.tname}"/>
        <input name="tid" type="hidden" value="${param.tid}"/>
      </p>
      <input name="action" type="hidden" value="addtopic"/>
      <input type="submit" value="提交" class="opt_sub" />
      <input type="reset" value="重置" class="opt_sub" />
    </form>
  </div>
</div>
<%@ include file="console_element/bottom.html" %>
