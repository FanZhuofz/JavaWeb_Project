<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkChange(){
		if(document.getElementById("isImage").checked){
		   $("#hdtp").show();
		}else{
		   $("#hdtp").hide();	
		}
	}
	
	function checkForm(){
		var title=document.getElementById("title").value;
		var author=document.getElementById("author").value;
		var typeId=document.getElementById("typeId").value;
		var isImage=document.getElementById("isImage").checked;
		var content=CKEDITOR.instances.content.getData();
		if(title==null||title==""){
			document.getElementById("error").innerHTML="标题不能为空！";
			return false;
		}
		if(author==null||author==""){
			document.getElementById("error").innerHTML="作者不能为空！";
			return false;
		}
		if(typeId==null||typeId==""){
			document.getElementById("error").innerHTML="请选择类别！";
			return false;
		}
		if(content==null||content==""){
			document.getElementById("error").innerHTML="内容不能为空！";
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
		<form action="news?action=save" method="post" enctype="multipart/form-data" onsubmit="return checkForm()">
		<table cellpadding="5" width="100%">
			<tr>
				<td><label>标题：</label> </td>
				<td>
					<input id="title" name="title" type="text" class="input-xxlarge" value="${news.title }"/>
				</td>
			</tr>
			<tr>
				<td><label>作者：</label> </td>
				<td>
					<input id="author" name="author" type="text" value="${news.author }"/>
				</td>
			</tr>
			<tr>
				<td><label>类别： </label></td>
				<td>
					<select id="typeId" name="typeId">
						<option value="">请选择类别..</option>
						<c:forEach var="newsType" items="${newsTypeList }">
							<option value="${newsType.newsTypeId }" ${newsType.newsTypeId==news.typeId?'selected':'' }>${newsType.typeName }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><label>属性：</label></td>
				<td>
					<div>
						<label class="checkbox inline">
						  <input type="checkbox" name="isHead" id="isHead" value="1" ${news.isHead==1?'checked':'' }>头条
						</label>
						<label class="checkbox inline">
						  <input type="checkbox" id="isImage" name="isImage" onclick="checkChange()" value="1" ${news.isImage==1?'checked':'' }>幻灯
						</label>
						<label class="checkbox inline">
						  <input type="checkbox" id="isHot" name="isHot" value="1" ${news.isHot==1?'checked':'' }>热点
						</label>
					</div>
				</td>
			</tr>
			<tr id="hdtp" style="display: none;">
				<td><label>幻灯图片：</label></td>
				<td>
					<input type="file" id="picFile" name="picFile"/>
					<input type="hidden" id="imageName" name="imageName" value="${news.imageName }"/>
				</td>
			</tr>
			<tr>
				<td valign="top"><label>内容：</label></td>
				<td>
					<textarea class="ckeditor" id="content" name="content" >${news.content }</textarea>
				</td>
			</tr>
			<tr>
				<td><input type="hidden" id="newsId" name="newsId" value="${news.newsId }" />&nbsp;</td>
				<td><input class="btn btn-primary" type="submit" value="保存"/>&nbsp;&nbsp;
				<input class="btn btn-primary" type="button" value="返回" onclick="javascript:history.back()"/>&nbsp;&nbsp;<font id="error" color="red">${error }</font></td>
			</tr>
		</table>
		</form>
	</div>
</div>
<script>
	if('${news.isImage}'==1){
		$("#hdtp").show();
	}
</script>