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
    <li><a href="../util/user?opr=user_per">用户登录权限</a></li>
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
