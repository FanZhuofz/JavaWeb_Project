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
 * 针对学生选课登录结果页面的Servlet
 * @author Administrator
 * @version 1.0
 */
public class ViewCourse extends Common
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
        
        //选出所有备选的课程
        String sUsername = (String)mySession.getAttribute( "username" );
        Vector allCourses = getAllSelectedCourse( sUsername );
        mySession.setAttribute( "courses", allCourses );
        response.sendRedirect("../viewCourse.jsp");
        return;
    }
    
    public void doPost ( HttpServletRequest request, 
                           HttpServletResponse response )
        throws ServletException, IOException 
    {
        doGet( request, response );
    }
    
    //获取用户所有已经选取的课程列表
    private Vector getAllSelectedCourse( String sUsername )
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
            String sQuery = "select course.* from course, elective "
                          + "where course.course_id = elective.course_id "
                          + "and elective.username='" + sUsername + "' "
                          + "order by course_id";
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

}