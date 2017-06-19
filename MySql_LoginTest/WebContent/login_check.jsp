<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>登录操作</title></head>
<body>
<center>
<h1>登陆操作</h1>
<hr>
<%!	// 定义若干个数据库的连接常量
	public static final String DBDRIVER = "com.mysql.jdbc.Driver" ;
	public static final String DBURL = "jdbc:mysql://123.206.33.224:3306/store" ;//test是数据库名字
	public static final String DBUSER = "root" ;
	public static final String DBPASS = "0211" ;
%>
<%
	Connection conn = null ;		// 数据库连接
	PreparedStatement pstmt = null ;	// 数据库预处理操作
	ResultSet rs = null ;		// 查询要处理结果集
	boolean flag = false ;	// 保存标记
	String name = null ;	// 保存真实姓名
%>
<%
try{
%>
<%
	Class.forName(DBDRIVER) ;
	conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS) ;
	String sql = "SELECT * FROM store1 WHERE name=? AND pass=?" ;
	pstmt = conn.prepareStatement(sql) ;
	pstmt.setString(1,request.getParameter("name")) ;
	pstmt.setString(2,request.getParameter("pass")) ;
	rs = pstmt.executeQuery() ;	// 查询
	if(rs.next()){	// 如果有数据，则可以执行
		flag = true ;	//  表示登陆成功
		name = rs.getString(1) ;
	}	
%>
<%
}catch(Exception e)	{
	e.printStackTrace() ;
}
finally{
	try{
		rs.close() ;
		pstmt.close() ;
		conn.close() ;
	} catch(Exception e){}
}
%>
<%
	if(flag){	// 登陆成功
%>
		<jsp:forward page="login_success.jsp">
			<jsp:param name="name" value="<%=name%>"/>
		</jsp:forward>
<%
	} else {		// 登陆失败
%>
		<jsp:forward page="login_failure.jsp"/>
<%
	}
%>
</center>
</body>
</html>