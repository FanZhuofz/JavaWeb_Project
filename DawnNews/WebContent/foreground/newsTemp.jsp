<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>论坛</title>
<link href="${pageContext.request.contextPath}/style/news.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

</head>
<body>
<div class="container">
	<jsp:include page="/foreground/common/head.jsp"/>
	<div class="row-fluid">
		<div class="span8">
			<jsp:include page="${mainPage }"/>
		</div>
		<div class="span4">
			<div class="data_list hot_news_list" >
				<div class="dataHeader">最新论坛</div>
				<div class="datas">
					<ul>
						<c:forEach var="newestNews" items="${newestNewsList }">
							<li><a href="news?action=show&newsId=${newestNews.newsId }" title="${newestNews.title }">${fn:substring(newestNews.title,0,22)}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="data_list hot_news_list" >
				<div class="dataHeader">热门论坛</div>
				<div class="datas">
					<ul>
						<c:forEach var="hotNews" items="${hotNewsList }">
							<li><a href="news?action=show&newsId=${hotNews.newsId }" title="${hotNews.title }">${fn:substring(hotNews.title,0,22)}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/foreground/common/foot.jsp"/>
</div>
</body>
</html>