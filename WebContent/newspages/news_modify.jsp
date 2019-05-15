<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<%@include file="console_element/top.jsp" %>

<script type="text/javascript">
	function check(){
		var ntitle = document.getElementById("ntitle");
		var nauthor = document.getElementById("nauthor");
		var nsummary = document.getElementById("nsummary");
		var ncontent = document.getElementById("ncontent");
		
		if(ntitle.value == ""){
			alert("标题不能为空！！");
			ntitle.focus();
			return false;
		}else if(nauthor.value == ""){
			alert("作者不能为空！！");
			nauthor.focus();
			return false;
		}else if(nsummary.value == ""){
			alert("摘要不能为空！！");
			nsummary.focus();
			return false;
		}else if(ncontent.value == ""){
			alert("内容不能为空！！");
			ncontent.focus();
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
    <h1 id="opt_type"> 编辑新闻： </h1>
    <form action="../util/news?opr=modifyNews&nid=${news.nid}"
          method="post" enctype="multipart/form-data" onsubmit="return check()">
      <p>
        <label> 主题 </label>
        <select name="ntid">
          <c:forEach items="${requestScope.topics}" var="topic">
            <c:choose>
              <c:when test="${news.ntid == topic.tid}">
                <option value='${topic.tid}' selected="selected">${topic.tname}</option></c:when>
              <c:otherwise><option value='${topic.tid}'>${topic.tname}</option></c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </p>
      <p>
        <label> 标题 </label>
        <input name="ntitle" id="ntitle" type="text" class="opt_input" value="${news.ntitle}"/>
      </p>
      <p>
        <label> 作者 </label>
        <input name="nauthor" id="nauthor" type="text" class="opt_input" value="${news.nauthor}"/>
      </p>
      <p>
        <label> 摘要 </label>
        <textarea name="nsummary" id="nsummary" cols="40" rows="3">${news.nsummary}</textarea>
      </p>
      <p>
        <label> 内容 </label>
        <textarea name="ncontent" id="ncontent" cols="70" rows="10">${news.ncontent}</textarea>
      </p>
      <p>
        <label> 上传图片 </label>
        <input name="file" id="file" type="file" class="opt_input" />
      </p>
      <input type="submit" value="提交" class="opt_sub" />
      <input type="reset" value="重置" class="opt_sub" />
    </form>
    <h1 id="opt_type">
		修改新闻评论：
	</h1>
      <table width="80%" align="left">
      <c:choose>
        <c:when test="${empty news.comments}">
            <tr><td colspan="6"> 暂无评论！ </td></tr>
            <tr>
                <td colspan="6"><hr />
                </td>
            </tr>
        </c:when>
        <c:otherwise>
          <c:forEach items="${news.comments}" var="comment">
       		<tr>
	          <td> 留言人： </td>
	          <td>${comment.cauthor}</td>
	          <td> IP： </td>
	          <td>${comment.cip}</td>
	          <td> 留言时间： </td>
	          <td><fmt:formatDate value="${comment.cdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	          <td><a href="../util/news?opr=deleteComment&cid=${comment.cid}&nid=${news.nid}">删除</a></td>
	        </tr>
	        <tr>
	          <td colspan="6">${comment.ccontent}</td>
	        </tr>
	        <tr>
	          <td colspan="6"><hr />
	          </td>
	        </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
      </table>
  </div>
</div>
<%--
	request.removeAttribute("news");
	request.removeAttribute("topics");
--%>
<%@ include file="console_element/bottom.html" %>