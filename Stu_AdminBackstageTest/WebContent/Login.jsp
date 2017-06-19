<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理系统登录</title>
<script type="text/javascript">
	function resetValue(){
		document.getElementById("userName").value="";
		document.getElementById("password").value="";
	}
</script>
<style type="text/css">
	a:link,a:visited{
		font-size:13px;
		text-decoration:none;  /*超链接无下划线*/
	}
	a:hover{
	 	text-decoration:underline;  /*鼠标放上去有下划线*/
	 	color:red;
	}
	.aa{
		font-size:14px;
		font-weight:bold;
		background:aqua;
	}
	.bb{
		font-weight:bold;
		background:#82D900;
	}
	.button {
		width:70px; height:25px;
		border-bottom-color:aqua;
		border-left-color:aqua;
		border-right-color:aqua;
		border-top-color:aqua;
		background:#AE8F00;
		font-size: 16px;
		font-family: "宋体";
		font-weight:bold;
	}
	.button:hover{
		color: #339966;
		background-color: #8CEA00;
	}
</style>

</head>
<body background="./images/bg.jpg">
<center>
<br/><br/><br/><br/><br/><br/><br/><br/>
<table border="0" width="540" height="80" class="bb">
	<tr>
		<td align="center">
			<h1>学生信息管理系统内容后台管理界面</h1>
		</td>
	</tr>
<table>
	<form action="login" method="post">
		<table border="0" width="540" height="260" class="aa">
			<tr>
				<td align="right">帐  &nbsp; 号： </td>
				<td align="left"><input type="text" value="${userName }" name="userName" id="userName"/></td>
			</tr>
			<tr>
				<td align="right">密  &nbsp; 码： </td>
				<td align="left"><input type="password" value="${password }" name="password" id="password"/></td>
			</tr>
			<tr height="30">
				<td width=150 align="right"><input type="submit" value="登 陆" class="button"></td>
				<td width=350 align="center"><input type="reset" value="重 置" onclick="resetValue()" class="button"></td>
			</tr>
			<tr height="50">
				<td width="40%"></td>
				<td colspan="3">
					<font color="red">${error }</font>
				</td>
			</tr>
			<tr height="20">
			<font size="3px">
				<td align="right">
				版权所有：Dawn 
				</td>
				<td align="left">
				&nbsp; Email：fans@e-team.cn 
				</td>
			</font>
			</tr>
		</table>
	</form>
</center>
</body>
</html>