package ch04.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch04.Course;

//@WebServlet("/DeleteCourse")
public class DeleteCourse extends Common {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sCourseId = (String) request.getAttribute("courseId");
		Course newCourse = new Course();
        newCourse.setCourseId( sCourseId );
        String sErrMsg = DeleteCourse( newCourse );
        //如果登录成功，则跳转到课程一览页面
        if ( sErrMsg.equals("") )
        {
            response.sendRedirect("../servlet/CourseList");
            return;
        }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String DeleteCourse(Course newCourse) {
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
           // String sQuery = "select * from course where course_id='" + newCourse.getCourseId() + "' ";
           // rs = stmt.executeQuery( sQuery );
            //检查是否选课人数已满
           /* if ( rs.next() )
            {
                return "该课程编号已经存在，请重新输入！";
            }*/
            //尝试进行登录
            String sUpdateQuery = "Delete from course where "
                                + "course_id='" + newCourse.getCourseId();
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
