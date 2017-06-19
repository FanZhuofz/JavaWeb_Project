<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function searchStudent(){
		$('#dg').datagrid('load',{
			userName:$('#s_userName').val()
		});
	}
	
	function deleteStudent(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("studentDelete",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function openStudentAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加学生信息");
		url="studentSave";
	}
	
	function openStudentModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑学生信息");
		$("#fm").form("load",row);
		url="studentSave?id="+row.id;
	}
	
	function closeStudentDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function resetValue(){
		$("#userName").val("");
		$("#password").val("");
		$("#name").val("");
		$("#age").val("");
		$("#sex").val("");
		$("#dept").val("");
		$("#tel").val("");
		$("#email").val("");
	}
	
	
	function saveStudent(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
</script>
</head>
<body>
	<table id="dg" title="学生 信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="studentList" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="userName" width="50">学号</th>
				<th field="password" width="50">密码</th>
				<th field="name" width="50">姓名</th>
				<th field="age" width="30">年龄</th>
				<th field="sex" width="30">性别</th>
				<th field="dept" width="80">系别</th>
				<th field="tel" width="50">电话</th>
				<th field="email" width="60">邮箱</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<a href="javascript:openStudentAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openStudentModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteStudent()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>&nbsp;学生学号：&nbsp;<input type="text" name="s_userName" id="s_userName"/><a href="javascript:searchStudent()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 300px;height: 330px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>学 号：</td>
					<td><input type="text" name="userName" id="userName" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>密 码：</td>
					<td><input type="password" name="password" id="password" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>姓 名：</td>
					<td><input type="text" name="name" id="name" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>年 龄：</td>
					<td><input type="age" name="age" id="age" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>性 别：</td>
					<td><input type="text" name="sex" id="sex" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>系 别：</td>
					<td><input type="text" name="dept" id="dept" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>电 话：</td>
					<td><input type="text" name="tel" id="tel" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>Email：</td>
					<td><input type="email" name="email" id="email" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveStudent()" class="easyui-linkbutton" iconCls="icon-ok">保 存</a>
		<a href="javascript:closeStudentDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取 消</a>
	</div>
</body>
</html>