package ch04.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch04.Course;

/**
 * 针对学生选课登录页面的Servlet
 * @author Administrator
 * @version 1.0
 */
public class ChooseCourse extends Common
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
            //如果是默认进入页面，则选出所有备选的课程
            String sUsername = (String)mySession.getAttribute( "username" );
            Vector allCourses = getAllCourses( sUsername );
            mySession.setAttribute( "courses", allCourses );
            response.sendRedirect("../chooseCourse.jsp");
            return;
        }
        else
        {
            //获取表单变量
            String sCourseId = request.getParameter("courseId");
            String sUsername = (String)mySession.getAttribute("username");
            //尝试进行选择
            boolean bChoose = chooseCourse( sUsername, sCourseId );
            //如果选课成功，则跳转到选课结果一览页面
            if ( bChoose )
            {
                response.sendRedirect("../servlet/ViewCourse");
                return;
            }
            else
            {
                mySession.setAttribute("errMsg","你选择的课程" + sCourseId + "已经满员，请选择其他课程。");
                Vector allCourses = getAllCourses( sUsername );
                mySession.setAttribute( "courses", allCourses );
                response.sendRedirect("../chooseCourse.jsp");
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
    
    //获取用户所有可以使用的课程列表
    private Vector getAllCourses( String sUsername )
    {
        //获得数据库连接
        Connection conn = this.getDBConnection();
        if ( conn == null )
        {
            return null;
        }
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = conn.createStatement();
            //执行SQL语句
            String sQuery = "select course.*,IF(elective.course_id, count(*), 0) as amount "
                          + "from course left join elective "
                          + "on course.course_id = elective.course_id "
                          + "where course.course_id not in "
                          + "(select distinct course_id from elective where username='" + sUsername + "') "
                          + "group by course.course_id";
            rs = stmt.executeQuery( sQuery );
            //留着在页面上显示的课程列表
            Vector courses = new Vector();
            while ( rs.next() )
            {
                Course course = new Course();
                course.setCourseId( rs.getString( "course_id" ) );
                course.setCourseName( rs.getString( "course_name" ) );
                course.setTeacher( rs.getString( "teacher" ) );
                course.setPoint( rs.getInt( "point" ) );
                course.setTime1( rs.getString( "time_1" ) );
                course.setTime2( rs.getString( "time_2" ) );
                course.setLimited( rs.getInt( "limited" ) );
                course.setAmount( rs.getInt( "amount" ) );
                
                courses.add(course);
            }
            return courses;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
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
    
    //为用户进行选课
    private synchronized boolean chooseCourse( String sUsername, String sCourseId )
    {
        //获得数据库连接
        Connection conn = this.getDBConnection();
        if ( conn == null )
        {
            return false;
        }
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            stmt = conn.createStatement();
            //执行SQL语句
            String sQuery = "select course.limited,IF(elective.course_id, count(*), 0) as amount "
                          + "from course left join elective "
                          + "on course.course_id = elective.course_id "
                          + "where course.course_id = '" + sCourseId + "' "
                          + "group by course.course_id";
            rs = stmt.executeQuery( sQuery );
            //检查是否选课人数已满
            if ( rs.next() )
            {
                int iLimited = rs.getInt("limited");
                int iAmount = rs.getInt("amount");
                if ( iAmount >= iLimited )
                {
                    return false;
                }
            }
            //尝试进行选课
            String sUpdateQuery = "insert into elective set "
                                + "username='" + sUsername + "', course_id='" + sCourseId + "'";
            stmt.executeUpdate( sUpdateQuery );
            
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
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