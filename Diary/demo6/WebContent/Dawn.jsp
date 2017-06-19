<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简易计算器</title>
</head>
<body style="text-align:center;">
	<jsp:useBean id="calcBean" class="dawn.java.demo6.Dawn"></jsp:useBean>
    <jsp:setProperty property="*" name="calcBean" />
    <%
        calcBean.calculate();
    %>
    <center>
    <br/>---------------------------------------------------------------</br>
			    <font size="4">
					  学号：B14060405 &nbsp;&nbsp; 姓名：范 卓
			    </font>
	<br/>---------------------------------------------------------------</br>
    <br/>*************************************************<br/>
    	计算结果是:
	    <jsp:getProperty property="firstNum" name="calcBean" />
	    <jsp:getProperty property="operator" name="calcBean" />
	    <jsp:getProperty property="secondNum" name="calcBean" />
	    =<jsp:getProperty property="result" name="calcBean" />
    <br/>*************************************************<br/><br/>
    <form action="/demo6/Dawn.jsp" method="post">
	<table  width="60%" border="1" >
		<tr>
			<td colspan="2">简单计算器</td>
		</tr>
		<tr>
			<td>第一个参数</td>
			<td>
				<input type="text" name="firstNum">
			</td>
		</tr>
		<tr>
			<td>操作符</td>
			<td>
				<select name="operator">
					<option value="+">+</option>
					<option value="-">-</option>
					<option value="*">*</option>
					<option value="/">/</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>第二个参数</td>
			<td>
				<input type="text" name="secondNum">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="计算">
			</td>
			
		</tr>	
	</table>
	</form>
</center>
</body>
</html>