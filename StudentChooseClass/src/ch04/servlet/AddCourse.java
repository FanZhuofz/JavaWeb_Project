package ch04.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch04.Course;

/**
 * 针对教师添加课程页面的Servlet
 * @author Administrator
 * @version 1.0
 */
public class AddCourse extends Common
{
    public void doGet ( HttpServletRequest request, 
                        HttpServletResponse response )
        throws ServletException, IOException
    {
        //设置提交表单的中文编码
        request.setCharacterEncoding("GBK");
        HttpSession mySession = request.getSession(true);
        
        //清空错误消息
        mySession.setAttribute("errMsg","");
        
        //是否进入默认页面
        if ( !request.getParameterNames().hasMoreElements() )
        {
            //如果是默认进入页面，则单纯显示一个空的页面
            Course course = new Course();
            mySession.setAttribute( "course", course );
            response.sendRedirect("../addCourse.jsp");
            return;
        }
        else
        {
            //获取表单变量
            String sCourseId = request.getParameter("courseId");
            String sCourseName = request.getParameter("courseName");
            String sTeacher = request.getParameter("teacher");
            int iPoint = new Integer(request.getParameter("point")).intValue();
            String sTime1 = request.getParameter("time1D") + request.getParameter("time1T");
            String sTime2 = request.getParameter("time2D") + request.getParameter("time2T");
            int iLimited = new Integer(request.getParameter("limited")).intValue();
            Course newCourse = new Course();
            newCourse.setCourseId( sCourseId );
            newCourse.setCourseName( sCourseName );
            newCourse.setTeacher( sTeacher );
            newCourse.setPoint( iPoint );
            newCourse.setTime1( sTime1 );
            newCourse.setTime2( sTime2 );
            newCourse.setLimited( iLimited );
            
            //尝试进行提交
            String sErrMsg = addCourse( newCourse );
            //如果提交成功，则跳转到课程一览页面
            if ( sErrMsg.equals("") )
            {
                response.sendRedirect("../servlet/CourseList");
                return;
            }
            else
            {
                mySession.setAttribute( "errMsg" , sErrMsg );
                mySession.setAttribute( "course", newCourse );
                response.sendRedirect("../addCourse.jsp");
                return;
            }
        }
    }
    
    public void doPost ( HttpServletRequest request, 
                           HttpServletResponse response )
        throws ServletException, IOException 
    {
        doGet( request, response );
    }
        
    //为用户进行选课
    private String addCourse( Course newCourse )
    {
        //获得数据库连接
        Connection conn = this.getDBConnection();
        if ( conn == null )
        {
            return "获取数据库连接失败！";
        }
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = conn.createStatement();
            //执行SQL语句判断该ID是否已经被使用
            String sQuery = "select * from course where course_id='" + newCourse.getCourseId() + "' ";
            rs = stmt.executeQuery( sQuery );
            //检查是否选课人数已满
            if ( rs.next() )
            {
                return "该课程编号已经存在，请重新输入！";
            }
            //尝试进行登录
            String sUpdateQuery = "insert into course set "
                                + "course_id='" + newCourse.getCourseId() + "', "
                                + "course_name='" + newCourse.getCourseName() + "', "
                                + "teacher='" + newCourse.getTeacher() + "', "
                                + "point=" + newCourse.getPoint() + ", "
                                + "time_1='" + newCourse.getTime1() + "', "
                                + "time_2='" + newCourse.getTime2() + "', "
                                + "limited=" + newCourse.getLimited() ;
            stmt.executeUpdate( sUpdateQuery );
            
            return "";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "登录失败！";
        }
        finally
        {
            try
            {
                rs.close();
                stmt.close();
                conn.close();
            }catch(Exception ex)
            {
            }
        }
    }
}