package xyz.dawnfan.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import xyz.dawnfan.dao.NewsDao;
import xyz.dawnfan.dao.NewsTypeDao;
import xyz.dawnfan.model.News;
import xyz.dawnfan.model.NewsType;
import xyz.dawnfan.util.DbUtil;
import xyz.dawnfan.util.ResponseUtil;

public class InitServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil=new DbUtil();
	NewsDao newsDao=new NewsDao();
	NewsTypeDao newsTypeDao=new NewsTypeDao();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext application=config.getServletContext();
	    this.refreshSystem(application);
	}
	
	private void refreshSystem(ServletContext application){
		 Connection con=null;
			try{
				con=dbUtil.getCon();
				
				String sql="SELECT * FROM t_news  ORDER BY publishDate DESC LIMIT 0,8;";
				List<News> newestNewsList=newsDao.newsList(con, sql);
				application.setAttribute("newestNewsList", newestNewsList);
				
				List<NewsType> newsTypeList=newsTypeDao.newsTypeList(con);
				application.setAttribute("newsTypeList", newsTypeList);
				
				sql="SELECT * FROM t_news  ORDER BY click DESC LIMIT 0,8;";
				List<News> hotNewsList=newsDao.newsList(con, sql);
				application.setAttribute("hotNewsList", hotNewsList);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession   session=(HttpSession)request.getSession();   
		ServletContext   application=(ServletContext)session.getServletContext();
		this.refreshSystem(application);
		try {
			JSONObject result=new JSONObject();
			result.put("success", true);
			ResponseUtil.write(result,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
