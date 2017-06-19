package ch04.servlet;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServlet;

/**
 * 针对一些共通处理的父类
 * @author Administrator
 * @version 1.0
 */
public class Common extends HttpServlet
{
    public Connection getDBConnection()
    {
        //尝试连接数据库
        try
        {
            //载入MySQL的JDBC驱动类
            Class.forName( "com.mysql.jdbc.Driver" );
            //获得数据库连接
            Connection conn = DriverManager.getConnection( "jdbc:mysql://123.206.33.224:3306/ch04","root","0211" );
            
            return conn;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}