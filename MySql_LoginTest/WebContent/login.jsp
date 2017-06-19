<%@page language="java" contentType="text/html" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>登陆页面</title></head>
<body>
<center>
<h1>登陆操作</h1>
<hr>
	<form action="login_check.jsp" method="post">
		<table border="1">
			<tr>
				<td colspan="2">
					用户登陆
				</td>
			</tr>
			<tr>
				<td>用户：</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="登陆">
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
	</form>
</center>
</body>
</html>