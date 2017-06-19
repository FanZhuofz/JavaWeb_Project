<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	
	function checkForm(){
		var linkName=document.getElementById("linkName").value;
		var linkUrl=document.getElementById("linkUrl").value;
		var linkEmail=document.getElementById("linkEmail").value;
		var orderNum=document.getElementById("orderNum").value;
		if(linkName==null||linkName==""){
			document.getElementById("error").innerHTML="链接名称不能为空！";
			return false;
		}
		if(linkUrl==null||linkUrl==""){
			document.getElementById("error").innerHTML="链接地址不能为空！";
			return false;
		}
		if(linkEmail==null||linkEmail==""){
			document.getElementById("error").innerHTML="联系人邮件不能为空！";
			return false;
		}
		if(orderNum==null||orderNum==""){
			document.getElementById("error").innerHTML="排列顺序不能为空！";
			return false;
		}else{
			 var type="^[0-9]*[1-9][0-9]*$"; 
		     var re=new RegExp(type); 
	         if(orderNum.match(re)==null){ 
	           alert("排列顺序必须为正整数!"); 
	           return false;
	        }
			return true;
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
		<form action="link?action=save" method="post"  onsubmit="return checkForm()">
		<table cellpadding="5">
			<tr>
				<td><label>链接名称：</label> </td>
				<td>
					<input id="linkName" name="linkName" type="text"  value="${link.linkName }"/>
				</td>
			</tr>
			<tr>
				<td><label>链接地址：</label> </td>
				<td>
					<input id="linkUrl" name="linkUrl" type="text"  value="${link.linkUrl }"/>
				</td>
			</tr>
			<tr>
				<td><label>联系人邮件：</label> </td>
				<td>
					<input id="linkEmail" name="linkEmail" type="text"  value="${link.linkEmail }"/>
				</td>
			</tr>
			<tr>
				<td><label>排列顺序：</label> </td>
				<td>
					<input id="orderNum" name="orderNum" type="text"  value="${link.orderNum }"/>
				</td>
			</tr>
			<tr>
				<td><input type="hidden" id="linkId" name="linkId" value="${link.linkId }" />&nbsp;</td>
				<td><input class="btn btn-primary" type="submit" value="保存友情链接"/>&nbsp;&nbsp;
				<input class="btn btn-primary" type="button" value="返回" onclick="javascript:history.back()"/>&nbsp;&nbsp;<font id="error" color="red">${error }</font></td>
			</tr>
		</table>
		</form>
	</div>
</div>
