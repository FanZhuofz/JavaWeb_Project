package xyz.dawnfan.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import xyz.dawnfan.dao.CommentDao;
import xyz.dawnfan.model.Comment;
import xyz.dawnfan.model.PageBean;
import xyz.dawnfan.util.DbUtil;
import xyz.dawnfan.util.NavUtil;
import xyz.dawnfan.util.PageUtil;
import xyz.dawnfan.util.PropertiesUtil;
import xyz.dawnfan.util.ResponseUtil;
import xyz.dawnfan.util.StringUtil;

public class CommentServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	CommentDao commentDao=new CommentDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if("save".equals(action)){
			commentSave(request,response);
		}else if("backList".equals(action)){
			commentBackList(request,response);
		}else if("delete".equals(action)){
			commentDelete(request,response);
		}
	}

	private void commentSave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String newsId=request.getParameter("newsId");
		String content=request.getParameter("content");
		String userIP=request.getRemoteAddr();
		Comment comment=new Comment(Integer.parseInt(newsId),content,userIP);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			commentDao.commentAdd(con, comment);
			request.getRequestDispatcher("news?action=show&newsId="+newsId).forward(request, response);
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

	private void commentBackList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String page=request.getParameter("page");
		String s_bCommentDate=request.getParameter("s_bCommentDate");
		String s_aPublishDate=request.getParameter("s_aPublishDate");
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int total=commentDao.commentCount(con, new Comment(),s_bCommentDate,s_aPublishDate);
			String pageCode=PageUtil.genPagation(request.getContextPath()+"/comment?action=backList",total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("backPageSize")));
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			List<Comment> commentBackList=commentDao.commentList(con, new Comment(),pageBean,s_bCommentDate,s_aPublishDate);
			request.setAttribute("navCode", NavUtil.genNewsManageNavigation("新闻评论管理", "新闻评论维护"));
			request.setAttribute("commentBackList", commentBackList);
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("s_bCommentDate", s_bCommentDate);
			request.setAttribute("s_aPublishDate", s_aPublishDate);
			request.setAttribute("mainPage", "comment/commentList.jsp");
			request.getRequestDispatcher("background/mainTemp.jsp").forward(request, response);
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
	
	private void commentDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String commentIds=request.getParameter("commentIds");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int delNums=commentDao.commentDelete(con, commentIds);
			if(delNums>0){
				result.put("success", true);
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "删除失败");
			}
			ResponseUtil.write(result,response);
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
	
	public static void main(String[] args) {
		String str="";
		if(str=="abc"){
			System.out.println("no");
		}else{
			System.out.println("yes");
		}
	}
}
