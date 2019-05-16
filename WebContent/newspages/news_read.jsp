<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link href="css/read.css" rel="stylesheet" type="text/css" />
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
	  		function checkComment(){
	  			var cauthor = document.getElementById("cauthor");
	  			var content = document.getElementById("ccontent");
	  			if(cauthor.value == ""){
	  				alert("用户名不能为空！！");
	  				return false;
	  			}else if(content.value == ""){
	  				alert("评论内容不能为空！！");
	  				return false;
	  			}
	  			return true;
	  		}
	  	</script>
</head>
<body>
<div id="header">
  
  
  <nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="http://localhost:8080/NewsSys/">新闻中国</a>
    </div>
    <div>
    

      		<div>
        		<p class="navbar-text navbar-right">欢迎&nbsp;&nbsp;<span>${user.uname}</span>&nbsp;&nbsp;登录本网站&nbsp;&nbsp;<a href="util/user?opr=logout">退出账号</a>&nbsp;&nbsp;</p>
    		</div>

    </div>
    </div>
</nav> 
</div>
<div id="container">
  <%@ include file="../index-elements/index_sidebar.jsp" %>
    <div class="main">
    <div class="content">
   
      <ul class="classlist">
        <table width="80%" align="center">
          <tr width="100%">
            <td colspan="2" align="center"><h4>${news.ntitle}</h4></td>
          </tr>
          <tr>
            <td colspan="2"><hr />
            </td>
          </tr>
          <tr>
            <td align="center">作者：${news.nauthor}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
            <td align="left">发布时间：<fmt:formatDate value="${news.ncreatedate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
          </tr>          
          <tr>
          <td>
          		<img width="80%" height="150px" src="${news.npicpath}" alt="" />
          </td>
          
          </tr>
          
          <tr>
            <td colspan="2" align="center"></td>
          </tr>
          <tr>
            <td colspan="2">${news.ncontent}</td>
          </tr>
          <tr>
            <td colspan="2"><hr />
            </td>
          </tr>
        </table>
      </ul>
      <ul class="classlist">
        <table width="80%" align="center">
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
      </ul>
      
      <c:choose>
      	<c:when test="${not empty sessionScope.user}">
						<ul class="classlist">
							<form action="util/news?opr=addComment" method="post"
								onSubmit="return checkComment()">
								<input type="hidden" name="nid" value="${news.nid}" />
								<table width="80%" align="center">
									<tr>
										<td>评 论</td>
									</tr>
									<tr>
										<td>用户名：</td>
										<td>
										<input id="cauthor" name="cauthor"
											value="${sessionScope.user.uname}" readonly="readonly"
											style="border: 0px;" />
											 IP： <input name="cip" id="cip"
											value="${pageContext.request.remoteAddr}" readonly="readonly"
											style="border: 0px;" />
											</td>
									</tr>
									<tr>
										<td colspan="2"><textarea name="ccontent" id="ccontent"
												cols="70" rows="10"></textarea></td>
									</tr>
									<tr>
										<td><input name="submit" value="发  表" type="submit" /></td>
									</tr>
								</table>
							</form>
						</ul>



					</c:when>
					<c:otherwise>
						<a href="#">登录后可进行评论</a>
					
					</c:otherwise>
      
      
      </c:choose>
      
      
      
    </div>
  </div>

</div>
<%--
    request.removeAttribute("news_view");
    request.removeAttribute("comments_view");
--%>
<%@ include file="../index-elements/index_bottom.html"%>
</body>
</html>
