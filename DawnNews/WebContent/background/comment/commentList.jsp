<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#checkedAll").click(function(){
		  if($(this).prop("checked") == true){ //check all
			  $("input[name='commentIds']").prop("checked",true); 
		  }else{
			  $("input[name='commentIds']").prop("checked",false); 
		  }
	   });
    });
	  
	function commentDelete(commentId){
		if(confirm("确认要删除这条评论吗?")){
			$.post("comment?action=delete",{commentIds:commentId},
				function(result){
					 var result = eval('('+result+')');
					if(result.success){
						alert("删除成功！");
						window.location.href="${pageContext.request.contextPath}/comment?action=backList";
					}else{
						alert(result.errorMsg);
					}
					}
			);
		}
	}
	
	function commentsDelete(){
		var chk_value =[];    
		 $('input[name="commentIds"]:checked').each(function(){    
		   chk_value.push($(this).val());    
		 });   
		 if(chk_value.length==0){
			alert("请选择要删除的数据！");
			return;
		  }
		 var commentIds=chk_value.join(",");
		 if(confirm("确认要删除这些评论吗?")){
				$.post("comment?action=delete",{commentIds:commentIds},
					function(result){
						 var result = eval('('+result+')');
						if(result.success){
							alert("成功删除"+result.delNums+"条数据！");
							window.location.href="${pageContext.request.contextPath}/comment?action=backList";
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
	<div class="search_content" style="vertical-align:middle;padding-right: 20px;">
		<div style="float: left;padding-top: 10px;">
			<button class="btn-mini btn-danger" type="submit" onclick="commentsDelete()">批量删除</button>
		</div>
		<div style="float: right;">
			<form action="${pageContext.request.contextPath}/comment?action=backList" method="post" style="margin-bottom: 5px;">
			评论日期：<input type="text" id="s_bCommentDate" name="s_bCommentDate" value="${s_bCommentDate }" class="Wdate" onClick="WdatePicker()" style="width: 100px;"/>&nbsp;到&nbsp;<input type="text" id="s_aPublishDate" name="s_aPublishDate" value="${s_aPublishDate }" class="Wdate" onClick="WdatePicker()" style="width: 100px;"/>&nbsp;&nbsp;<button class="btn btn-primary" type="submit" style="margin-top: -8px;">查询</button>
			</form>
		</div>
	</div>
	<div class="data_content">
	   <table class="table table-bordered table-hover">
		 <tr>
		 	<th><input type="checkbox" id="checkedAll"/></th>
			<th>序号</th>
			<th>评论内容</th>
			<th>评论主题</th>
			<th>评论时间</th>
			<th>操作</th>
		</tr>
		<c:forEach var="backComment" items="${commentBackList }" varStatus="status" >
			<tr>
				<td><input type="checkbox" name="commentIds"  value="${backComment.commentId }"/></td>
				<td>${status.index+1 }</td>
				<td><a title="${backComment.content }">${fn:substring(backComment.content,0,15) }...</a></td>
				<td><a title="${backComment.newsTitle }" target="_blank" href="news?action=show&newsId=${backComment.newsId }">${fn:substring(backComment.newsTitle,0,15) }...</a></td>
				<td><fmt:formatDate value="${backComment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><button class="btn-mini btn-danger" type="button" onclick="commentDelete(${backComment.commentId })">删除</button></td>
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