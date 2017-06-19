<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page language="java" import="java.util.*" %>
<%@page language="java" import="ch04.*" %>
<%@ include file="inc/cmnAuthenticate.jsp" %>
<%
Course course = (Course)session.getAttribute("course");
if ( course == null )
{
    course = new Course();
}
//显示学分和人数的地方
String sPoint = "";
String sLimited = "";
if ( course.getPoint() != 0 )
{
    sPoint = sPoint + course.getPoint();
}
if ( course.getLimited() != 0 )
{
    sLimited = sLimited + course.getLimited();
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加课程</title>
	<script language="JAVASCRIPT" src="common/cmnScript.js"></script>
	<script language="JAVASCRIPT" src="addCourse.js"></script>
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

<body>
<center><h1>课 程 添 加</h1></center>
<table border=0 cellpadding=0 cellspacing=0>
  <tr>
    <td>
      &nbsp;
    </td>
  </tr>
</table>
<form name="form_main" action="servlet/AddCourse" method="post" 
      onsubmit="return checkInput();" onreset="resetForm()">
<table border=0 cellpadding=0 cellspacing=2 bgcolor="#000000" align=center>
  <tr>
    <td>
      <table border=0 cellpadding=0 cellspacing=0 bgcolor="#ffffff" width=1020>
        <tr>
          <td align=left height=30>
            欢迎你，<font color=blue><%=session.getAttribute("realname")%></font>！
          </td>
          <td align=right>
            <a href="servlet/CourseList">课程情况一览</a>
            &nbsp;| &nbsp;
            <a href="logout.jsp">退出登录</a>
            &nbsp;
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td>
      <table border=0 cellpadding=0 cellspacing=0 bgcolor="#ffffff" width=1020>
        <tr>
          <td>
            <br>&nbsp;&nbsp;请输入新课程详细信息，并点击登录按钮：
          </td>
        </tr>
        <tr>
          <td height=10>
          </td>
        </tr>
        <tr>
          <td align=center valign=top>
            <table border=0 cellpadding=1 cellspacing=1 bgcolor="#000000" width=1000>
              <tr>
                <td width=120 class="td_label" align="center" valign="center">
                  课程编号
                </td> 
                <td width=600 class="td_content" align="left" valign="center">
                  <input name="courseId" type="text" size="20" maxlength="6" value="<%=course.getCourseId()%>">
                </td>
              </tr>
              <tr>
                <td width=80 class="td_label" align="center" valign="center">
                  课程名称
                </td> 
                <td width=600 class="td_content" align="left" valign="center">
                  <input name="courseName" type="text" size="40" maxlength="20" value="<%=course.getCourseName()%>">
                </td>
              </tr>
              <tr>
                <td width=80 class="td_label" align="center" valign="center">
                  教师姓名
                </td> 
                <td width=600 class="td_content" align="left" valign="center">
                  <input name="teacher" type="text" size="20" maxlength="20" value="<%=course.getTeacher()%>">
                </td>
              </tr>
              <tr>
                <td width=80 class="td_label" align="center" valign="center">
                  学分
                </td> 
                <td width=600 class="td_content" align="left" valign="center">
                  <input name="point" type="text" size="3" maxlength="1" value="<%=sPoint%>">
                  （1～6）
                </td>
              </tr>
              <tr>
                <td class="td_label" align="center" valign="center" rowspan=2 height=40>
                  上课时间
                </td> 
                <td class="td_content" align="left" valign="top">
                  第一次：&nbsp;<span class="sel_span">
                    <div class="sel_div">
                      <select name="time1D" class="sel">
                        <option value="1"
<%
if ( course.getTime1().length() > 1 && course.getTime1().substring( 0, 1 ).equals("1") )
{
%>
                        selected
<%
}
%>
                        >星期一</option>
                        <option value="2"
<%
if ( course.getTime1().length() > 1 && course.getTime1().substring( 0, 1 ).equals("2") )
{
%>
                        selected
<%
}
%>
                        >星期二</option>
                        <option value="3"
<%
if ( course.getTime1().length() > 1 && course.getTime1().substring( 0, 1 ).equals("3") )
{
%>
                        selected
<%
}
%>
                        >星期三</option>
                        <option value="4"
<%
if ( course.getTime1().length() > 1 && course.getTime1().substring( 0, 1 ).equals("4") )
{
%>
                        selected
<%
}
%>
                        >星期四</option>
                        <option value="5"
<%
if ( course.getTime1().length() > 1 && course.getTime1().substring( 0, 1 ).equals("5") )
{
%>
                        selected
<%
}
%>
                        >星期五</option>
                      </select>
                    </div>
                  </span>
                  &nbsp;
                  <span class="sel_span">
                    <div class="sel_div">
                      <select name="time1T" class="sel">
                        <option value="1"
<%
if ( course.getTime1().length() > 1 && course.getTime1().substring( 1, 2 ).equals("1") )
{
%>
                        selected
<%
}
%>
                        >08:30～10:05</option>
                        <option value="2"
<%
if ( course.getTime1().length() > 1 && course.getTime1().substring( 1, 2 ).equals("2") )
{
%>
                        selected
<%
}
%>
                        >10:15～11:50</option>
                        <option value="3"
<%
if ( course.getTime1().length() > 1 && course.getTime1().substring( 1, 2 ).equals("3") )
{
%>
                        selected
<%
}
%>
                        >13:40～15:15</option>
                        <option value="4"
<%
if ( course.getTime1().length() > 1 && course.getTime1().substring( 1, 2 ).equals("4") )
{
%>
                        selected
<%
}
%>
                        >15:25～17:00</option>
                        <option value="5"
<%
if ( course.getTime1().length() > 1 && course.getTime1().substring( 1, 2 ).equals("5") )
{
%>
                        selected
<%
}
%>
                        >19:00～21:30</option>
                      </select>
                    </div>
                  </span>
                </td>
              </tr>
              <tr>
                <td class="td_content" align="left" valign="top">
                  第二次：&nbsp;<span class="sel_span">
                    <div class="sel_div">
                      <select name="time2D" class="sel">
                        <option value="1"
<%
if ( course.getTime2().length() > 1 && course.getTime2().substring( 0, 1 ).equals("1") )
{
%>
                        selected
<%
}
%>
                        >星期一</option>
                        <option value="2"
<%
if ( course.getTime2().length() > 1 && course.getTime2().substring( 0, 1 ).equals("2") )
{
%>
                        selected
<%
}
%>
                        >星期二</option>
                        <option value="3"
<%
if ( course.getTime2().length() > 1 && course.getTime2().substring( 0, 1 ).equals("3") )
{
%>
                        selected
<%
}
%>
                        >星期三</option>
                        <option value="4"
<%
if ( course.getTime2().length() > 1 && course.getTime2().substring( 0, 1 ).equals("4") )
{
%>
                        selected
<%
}
%>
                        >星期四</option>
                        <option value="5"
<%
if ( course.getTime2().length() > 1 && course.getTime2().substring( 0, 1 ).equals("5") )
{
%>
                        selected
<%
}
%>
                        >星期五</option>
                      </select>
                    </div>
                  </span>
                  &nbsp;
                  <span class="sel_span">
                    <div class="sel_div">
                      <select name="time2T" class="sel">
                        <option value="1"
<%
if ( course.getTime2().length() > 1 && course.getTime2().substring( 1, 2 ).equals("1") )
{
%>
                        selected
<%
}
%>
                        >08:30～10:05</option>
                        <option value="2"
<%
if ( course.getTime2().length() > 1 && course.getTime2().substring( 1, 2 ).equals("2") )
{
%>
                        selected
<%
}
%>
                        >10:30～11:50</option>
                        <option value="3"
<%
if ( course.getTime2().length() > 1 && course.getTime2().substring( 1, 2 ).equals("3") )
{
%>
                        selected
<%
}
%>
                        >13:40～15:15</option>
                        <option value="4"
<%
if ( course.getTime2().length() > 1 && course.getTime2().substring( 1, 2 ).equals("4") )
{
%>
                        selected
<%
}
%>
                        >15:25～17:00</option>
                        <option value="5"
<%
if ( course.getTime2().length() > 1 && course.getTime2().substring( 1, 2 ).equals("5") )
{
%>
                        selected
<%
}
%>
                        >19:00～21:30</option>
                      </select>
                    </div>
                  </span>
                </td>
              </tr>
              <tr>
                <td width=80 class="td_label" align="center" valign="center">
                  限制人数
                </td> 
                <td width=600 class="td_content" align="left" valign="center">
                  <input name="limited" type="text" size="6" maxlength="3" value="<%=sLimited%>">
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td height=10></td>
        </tr>
        <tr>
          <td height=30 align=center>
            <input type="submit" value="提 交" class="button0"></input> &nbsp; &nbsp; 
            <input type="reset" value="取 消" class="button0"></input>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</form>
</body>
</html>