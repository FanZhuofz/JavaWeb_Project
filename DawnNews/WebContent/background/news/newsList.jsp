<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function newsDelete(newsId){
		if(confirm("确认要删除这条内容吗?")){
			$.post("news?action=delete",{newsId:newsId},
				function(delFlag){
					var flag = eval('('+delFlag+')');
					if(flag){
						alert("删除成功！");
						window.location.href="${pageContext.request.contextPath}/news?action=backList";
					}else{
						alert("删除失败！");
					}
				}
			);
		}
	}
</script>
<div class="data_list backMain">
	<div class="dataHeader navi">
		${navCode }
	</div>
	<div class="search_content" style="vertical-align:middle;">
		<form action="${pageContext.request.contextPath}/news?action=backList" method="post" style="margin-bottom: 5px;">
		标题：<input type="text" id="s_title" name="s_title" style="width: 180px;" value="${s_title }"/>&nbsp;&nbsp;发布日期：<input type="text" id="s_bPublishDate" name="s_bPublishDate" value="${s_bPublishDate }" class="Wdate" onClick="WdatePicker()" style="width: 100px;"/>&nbsp;到&nbsp;<input type="text" id="s_aPublishDate" name="s_aPublishDate" value="${s_aPublishDate }" class="Wdate" onClick="WdatePicker()" style="width: 100px;"/>&nbsp;&nbsp;<button class="btn btn-primary" type="submit" style="margin-top: -8px;">查询</button>
		</form>
	</div>
	<div class="data_content">
	   <table class="table table-bordered table-hover">
		 <tr>
			<th>序号</th>
			<th>标题</th>
			<th>类别</th>
			<th>发布时间</th>
			<th>操作</th>
		</tr>
		<c:forEach var="backNews" items="${backNewsList }" varStatus="status" >
			<tr>
				<td>${status.index+1 }</td>
				<td>${backNews.title }</td>
				<td>${backNews.typeName }</td>
				<td><fmt:formatDate value="${backNews.publishDate }" type="date" pattern="yyyy-MM-dd" /></td>
				<td><button class="btn-mini btn-info" type="button" onclick="javascript:window.location='news?action=preSave&newsId=${backNews.newsId }'">修改</button>&nbsp;&nbsp;<button class="btn-mini btn-danger" type="button" onclick="newsDelete(${backNews.newsId })">删除</button></td>
			</tr>
		</c:forEach>
	  </table>
	</div>
	<div class="pagination pagination-centered">
	 	 <ul>
	    	${pageCode }
	 	 </ul>
	</div>
</div>