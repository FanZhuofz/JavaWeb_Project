<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="span9">
		<div>
			<img src="${pageContext.request.contextPath}/images/logo.png"/>
		</div>
	</div>
	<div class="span3">
		<div>
			<p>
				<img src="images/qq.png"> &nbsp; 
				<a target="_blank" href="tencent://message/?uin=994825661&Site" class="mkeButton" style="margin-top:30px;">
				QQ交谈</a>
			</p>
			<p>
				<img src="images/email.png"> &nbsp; 
				<a target="_blank" href="mailto:fans@e-team.cn" class="mkeButton">发邮件</a>
			</p>
		</div>
	</div>
</div>
<div class="row-fluid">
	<div class="span12">
		<div class="navbar">
		  <div class="navbar-inner">
		    <a class="brand" href="goIndex">首页</a>
		    <ul class="nav">
		      <c:forEach var="newsType" items="${newsTypeList }">
		      	 <li><a href="news?action=list&typeId=${newsType.newsTypeId }">${newsType.typeName }</a></li>
		      </c:forEach>
		    </ul>
		  </div>
		</div>
	</div>
</div>