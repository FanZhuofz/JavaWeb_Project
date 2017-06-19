<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function newsTypeDelete(newsTypeId){
		if(confirm("确认要删除这条论坛类别吗?")){
			$.post("newsType?action=delete",{newsTypeId:newsTypeId},
				function(result){
				    var result = eval('('+result+')');
					if(result.success){
						alert("删除成功！");
						window.location.href="${pageContext.request.contextPath}/newsType?action=backList";
					}else{
						alert(result.errorMsg);
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
	<div class="data_content">
	   <table class="table table-bordered table-hover">
		 <tr>
			<th>序号</th>
			<th>论坛类别</th>
			<th>操作</th>
		</tr>
		<c:forEach var="newsTypeBack" items="${newsTypeBackList }" varStatus="status" >
			<tr>
				<td>${status.index+1 }</td>
				<td>${newsTypeBack.typeName }</td>
				<td><button class="btn-mini btn-info" type="button" onclick="javascript:window.location='newsType?action=preSave&newsTypeId=${newsTypeBack.newsTypeId }'">修改</button>&nbsp;&nbsp;<button class="btn-mini btn-danger" type="button" onclick="newsTypeDelete(${newsTypeBack.newsTypeId })">删除</button></td>
			</tr>
		</c:forEach>
	  </table>
	</div>
</div>