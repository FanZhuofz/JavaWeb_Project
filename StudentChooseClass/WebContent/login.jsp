<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page language="java" import="java.util.*" %>
<%@page language="java" import="ch04.*" %>
<%
String sUsername = (String)session.getAttribute("username");
if ( sUsername == null )
{
    sUsername = "";
}
String sRole = (String)session.getAttribute("role");
if ( sRole == null )
{
    sRole = "0";
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>请登录选课系统</title>
<script language="JAVASCRIPT" src="common/cmnScript.js"></script>
  <script language="JAVASCRIPT" src="login.js"></script>
  <link rel="stylesheet" type="text/css" href="common\cmnStyle.css" TITLE="common"></link>

  <script language="JAVASCRIPT">
<%
    if ( session.getAttribute("errMsg") != null )
    {
%>
        var sErrMsg = "<%=session.getAttribute("errMsg")%>";
<%
    }
    else
    {
%>
        var sErrMsg = "";
<%
    }
%>
  </script>
</head>

<body bgcolor="#C1FFE4">
<table border=0 cellpadding=0 cellspacing=0 height=100>
  <tr>
    <td>
      &nbsp;
    </td>
  </tr>
</table>
<form name="form_main" action="servlet/Login" method="post" 
      onsubmit="return checkInput();" onreset="resetForm()">
<table border=0 cellpadding=0 cellspacing=2 bgcolor="#FF44FF" align=center>
  <tr>
    <td>
      <table border=0 cellpadding=0 cellspacing=0 bgcolor="#4DFFFF" width=500 height="300">
        <tr>
          <td>
          	<br><center><font face="黑体" color="red" size="16"> 学 生 选 课 系 统 </font></center><br/><hr color="#000000">
          </td>
        </tr>
        <tr>
          <td height=5></td>
        </tr>
        <tr>
          <td align=center>
            <table border=0 cellpadding=0 cellspacing=0 bgcolor="#4DFFFF">
              <tr>
                <td align=right><h2>用户名：</h2></td>
                <td>
                  <input type="text" name="username" length=10 maxlength=20 value="<%=sUsername%>"></input>
                </td>
              </tr>
              <tr>
                <td align=right><h2>密&nbsp;码：</h2></td>
                <td>
                  <input type="password" name="password" length=10 maxlength=20></input>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td height=5></td>
        </tr>
        <tr>
          <td align=center>
            <input type="radio" name="role" value="0" style="border-width: 0px;"
<%
if( sRole.equals("0") )
{
%>
            checked
<%
}
%>
          ><font size="6">学生 &nbsp;</font>
          <input type="radio" name="role" value="1" style="border-width: 0px;"
<%
if( sRole.equals("1") )
{
%>
            checked
<%
}
%>
          ><font size="6">教师</font>
          </td>
        </tr>
        <tr>
          <td height=5></td>
        </tr>
        <tr>
          <td align=center>
            <input type="submit" value="登 录" class="button0"> &nbsp; &nbsp; &nbsp; &nbsp;
            <input type="reset" value="清 除" class="button0">
          </td>
        </tr>
        <tr>
          <td height=10></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</form>
</body>
</html>