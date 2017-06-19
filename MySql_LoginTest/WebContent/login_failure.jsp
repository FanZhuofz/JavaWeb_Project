<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>登陆失败</title></head>
<body>
<center>
<h1>登陆操作</h1>
<h2><font color="green"><%=request.getParameter("name")%></font>
用户不存在，<a href="login.jsp">返回登陆页面</a>！</h2>
</center>
</body>
</html>