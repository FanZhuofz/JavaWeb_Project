<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page language="java" import="java.util.*" %>

<%
if(session.getAttribute("username") == null )
{
    session.invalidate();
    response.sendRedirect("login.jsp");
    return;
}
%>