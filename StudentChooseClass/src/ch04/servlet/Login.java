package ch04.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 针对登录页面的Servlet
 * @author Administrator
 * @version 1.0
 */
public class Login extends Common
{
    public void doGet ( HttpServletRequest request, 
                        HttpServletResponse response )
        throws ServletException, IOException
    {
        //设置提交表单的中文编码
       // request.setCharacterEncoding("GBK");
        HttpSession mySession = request.getSession(true);
        
        //清空错误消息
        mySession.setAttribute("errMsg","");
        
        //是否进入默认页面
        if ( !request.getParameterNames().hasMoreElements() )
        {
            response.sendRedirect("。./login.jsp");
            return;
        }
        
        //得到用户输入信息
        String sUsername = request.getParameter("username");
        String sPassword = request.getParameter("password");
        String sRole = request.getParameter("role");

        //如果用户是提交表单
        if ( sUsername != null && sUsername.length() > 0 )
        {
            //校验用户输入信息
            String sRealname = getUserRealname( sUsername, sPassword, sRole );
            
            if ( sRealname == null )
            {
                //出错了设置变量并重新显示
                mySession.setAttribute( "errMsg", "登录失败！请重新输入相关信息！" );
                mySession.setAttribute( "username", sUsername );
                mySession.setAttribute( "role", sRole );
                response.sendRedirect("../login.jsp");
                return;
            }
            else
            {
                mySession.setAttribute( "username", sUsername );
                mySession.setAttribute( "realname", sRealname );
                //根据用户角色的不同决定迁移到哪个页面
                if ( sRole.equals( "0" ) )
                {
                    //学生角色则迁移到选课一览页面
                    response.sendRedirect( "../servlet/ChooseCourse" );
                }
                else
                {
                    //教师角色则迁移到选课结果一览页面
                    response.sendRedirect( "../servlet/CourseList" );
                }
                return;
            }
        }
        //如果用户非法进入这个页面
        else
        {
            response.sendRedirect("../login.jsp");
            return;
        }
    }
    
    public void doPost ( HttpServletRequest request, 
                           HttpServletResponse response )
        throws ServletException, IOException 
    {
        doGet( request, response );
    }
    
    private String getUserRealname(String sUsername, String sPassword, String sRole)
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
            String sQuery = "select * from user where username='" + sUsername + "' "
                          + "and password='" + sPassword + "' and role='" + sRole + "' ";
            rs = stmt.executeQuery( sQuery );
            String sRealname = null;
            if ( rs.next() )
            {
                sRealname = rs.getString( "realname" );
            }
            return sRealname;
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