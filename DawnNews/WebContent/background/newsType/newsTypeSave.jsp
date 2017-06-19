<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	
	function checkForm(){
		var typeName=document.getElementById("typeName").value;
		if(typeName==null||typeName==""){
			document.getElementById("error").innerHTML="论坛类别名称不能为空！";
			return false;
		}
		return true;
	}
</script>
</script>
<div class="data_list backMain">
	<div class="dataHeader navi">
		${navCode }
	</div>
	<div class="data_content">
		<form action="newsType?action=save" method="post"  onsubmit="return checkForm()">
		<table cellpadding="5">
			<tr>
				<td><label>论坛类别名称：</label> </td>
				<td>
					<input id="typeName" name="typeName" type="text"  value="${newsType.typeName }"/>
				</td>
			</tr>
			<tr>
				<td><input type="hidden" id="newsTypeId" name="newsTypeId" value="${newsType.newsTypeId }" />&nbsp;</td>
				<td><input class="btn btn-primary" type="submit" value="保存论坛类别"/>&nbsp;&nbsp;
				<input class="btn btn-primary" type="button" value="返回" onclick="javascript:history.back()"/>&nbsp;&nbsp;<font id="error" color="red">${error }</font></td>
			</tr>
		</table>
		</form>
	</div>
</div>
