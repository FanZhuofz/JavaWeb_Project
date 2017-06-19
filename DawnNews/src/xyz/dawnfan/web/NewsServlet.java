package xyz.dawnfan.web;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import xyz.dawnfan.dao.CommentDao;
import xyz.dawnfan.dao.NewsDao;
import xyz.dawnfan.dao.NewsTypeDao;
import xyz.dawnfan.model.Comment;
import xyz.dawnfan.model.News;
import xyz.dawnfan.model.NewsType;
import xyz.dawnfan.model.PageBean;
import xyz.dawnfan.util.DateUtil;
import xyz.dawnfan.util.DbUtil;
import xyz.dawnfan.util.NavUtil;
import xyz.dawnfan.util.PageUtil;
import xyz.dawnfan.util.PropertiesUtil;
import xyz.dawnfan.util.ResponseUtil;
import xyz.dawnfan.util.StringUtil;

public class NewsServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	NewsDao newsDao=new NewsDao();
	NewsTypeDao newsTypeDao=new NewsTypeDao();
	CommentDao commentDao=new CommentDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		if("list".equals(action)){
			this.newsList(request,response);
		}else if("show".equals(action)){
			this.newsShow(request,response);
		}else if("preSave".equals(action)){
			this.newsPreSave(request,response);
		}else if("save".equals(action)){
			this.newsSave(request,response);
		}else if("backList".equals(action)){
			this.newsBackList(request,response);
		}else if("delete".equals(action)){
			this.newsDelete(request,response);
		}
	}
	
	
	private void newsList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String typeId=request.getParameter("typeId");
		String page=request.getParameter("page");
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		Connection con=null;
		News s_news=new News();
		if(StringUtil.isNotEmpty(typeId)){
			s_news.setTypeId(Integer.parseInt(typeId));			
		}
		try{
			con=dbUtil.getCon();
			int total=newsDao.newsCount(con, s_news,null,null);
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			List<News> newestNewsListWithType=newsDao.newsList(con,s_news, pageBean,null,null);
			request.setAttribute("newestNewsListWithType", newestNewsListWithType);
			request.setAttribute("navCode", NavUtil.genNewsListNavigation(newsTypeDao.getNewsTypeById(con, typeId).getTypeName(), typeId));
			request.setAttribute("pageCode", PageUtil.genUpAndDownPagation(total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")),typeId));
			request.setAttribute("mainPage", "news/newsList.jsp");
			request.getRequestDispatcher("foreground/newsTemp.jsp").forward(request, response);
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
	
	private void newsBackList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String page=request.getParameter("page");
		String s_title=request.getParameter("s_title");
		String s_bPublishDate=request.getParameter("s_bPublishDate");
		String s_aPublishDate=request.getParameter("s_aPublishDate");
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		Connection con=null;
		News s_news=new News();
		if(StringUtil.isNotEmpty(s_title)){
			s_news.setTitle(s_title);
		}
		try{
			con=dbUtil.getCon();
			int total=newsDao.newsCount(con, s_news,s_bPublishDate,s_aPublishDate);
			String pageCode=PageUtil.genPagation(request.getContextPath()+"/news?action=backList",total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("backPageSize")));
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("backPageSize")));
			List<News> backNewsList=newsDao.newsList(con,s_news, pageBean,s_bPublishDate,s_aPublishDate);
			request.setAttribute("backNewsList", backNewsList);
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("navCode", NavUtil.genNewsManageNavigation("新闻管理", "新闻维护"));
			request.setAttribute("s_title", s_title);
			request.setAttribute("s_bPublishDate", s_bPublishDate);
			request.setAttribute("s_aPublishDate", s_aPublishDate);
			request.setAttribute("mainPage", "news/newsList.jsp");
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
	
	
	
	
	private String getUpDownPageCode(List<News> upAndDownPage){
		News upNews=upAndDownPage.get(0);
		News downNews=upAndDownPage.get(1);
		StringBuffer pageCode=new StringBuffer();
		if(upNews.getNewsId()==-1){
			pageCode.append("<p>上一篇：没有了</p>");
		}else{
			pageCode.append("<p>上一篇：<a href='news?action=show&newsId="+upNews.getNewsId()+"'>"+upNews.getTitle()+"</a></p>");
		}
		if(downNews.getNewsId()==-1){
			pageCode.append("<p>下一篇：没有了</p>");
		}else{
			pageCode.append("<p>上一篇：<a href='news?action=show&newsId="+downNews.getNewsId()+"'>"+downNews.getTitle()+"</a></p>");
		}
		return pageCode.toString();
	}
	
	private void newsShow(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String newsId=request.getParameter("newsId");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			newsDao.newsClick(con, newsId);
			News news=newsDao.getNewsById(con, newsId);
			Comment s_comment=new Comment();
			s_comment.setNewsId(Integer.parseInt(newsId));
			List<Comment> commentList=commentDao.commentList(con, s_comment,null,null,null);
			request.setAttribute("commentList", commentList);
			request.setAttribute("navCode",NavUtil.genNewsNavigation(news.getTypeName(), news.getTypeId()+"", news.getTitle()));
			request.setAttribute("pageCode", this.getUpDownPageCode(newsDao.getUpAndDownPageId(con, newsId)));
			request.setAttribute("news", news);
			request.setAttribute("mainPage", "news/newsShow.jsp");
			request.getRequestDispatcher("foreground/newsTemp.jsp").forward(request, response);
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
	
	private void newsPreSave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		Connection con=null;
		String newsId=request.getParameter("newsId");
		try{
			con=dbUtil.getCon();
			if(StringUtil.isNotEmpty(newsId)){
				News news=newsDao.getNewsById(con, newsId);
				request.setAttribute("news", news);
			}
			List<NewsType> newsTypeList=newsTypeDao.newsTypeList(con);
			request.setAttribute("newsTypeList", newsTypeList);
			request.setAttribute("mainPage", "/background/news/newsSave.jsp");
			if(StringUtil.isNotEmpty(newsId)){
				request.setAttribute("navCode", NavUtil.genNewsManageNavigation("新闻管理", "新闻修改"));
			}else{
				request.setAttribute("navCode", NavUtil.genNewsManageNavigation("新闻管理", "新闻添加"));				
			}
			request.getRequestDispatcher("/background/mainTemp.jsp").forward(request, response);
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
	

	
	private void newsSave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		List<FileItem> items=null;
		try {
			  items=(List<FileItem>)upload.parseRequest(new ServletRequestContext(request));
		//	items=upload.parseRequest((RequestContext) request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<FileItem> itr=items.iterator();
		News news=new News();
		while(itr.hasNext()){
			FileItem item=(FileItem)itr.next();
			if(item.isFormField()){
				String fieldName=item.getFieldName();
				if("newsId".equals(fieldName)){
					if(StringUtil.isNotEmpty(item.getString("utf-8"))){
						news.setNewsId(Integer.parseInt(item.getString("utf-8")));						
					}
				}
				if("title".equals(fieldName)){
					news.setTitle(item.getString("utf-8"));
				}
				if("author".equals(fieldName)){
					news.setAuthor(item.getString("utf-8"));
				}
				if("content".equals(fieldName)){
					news.setContent(item.getString("utf-8"));
				}
				if("typeId".equals(fieldName)){
					news.setTypeId(Integer.parseInt(item.getString("utf-8")));
				}
				if("isHead".equals(fieldName)){
					news.setIsHead(Integer.parseInt(item.getString("utf-8")));
				}
				if("isImage".equals(fieldName)){
					news.setIsImage(Integer.parseInt(item.getString("utf-8")));
				}
				if("isHot".equals(fieldName)){
					news.setIsHot(Integer.parseInt(item.getString("utf-8")));
				}
				if("imageName".equals(fieldName)){
					if(StringUtil.isNotEmpty(item.getString("utf-8"))){
						news.setImageName(item.getString("utf-8"));
					}
				}
			}else if(!"".equals(item.getName())){
				try{
					String imageName=DateUtil.getCurrentDateStr();
					news.setImageName(imageName+"."+item.getName().split("\\.")[1]);
					String filePath=PropertiesUtil.getValue("imagePath")+imageName+"."+item.getName().split("\\.")[1];
					item.write(new File(filePath));
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			if(news.getNewsId()!=0){
				newsDao.newsUpdate(con, news);
			}else{
				newsDao.newsAdd(con, news);				
			}
			request.getRequestDispatcher("/news?action=backList").forward(request, response);
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
	
	private void newsDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String newsId=request.getParameter("newsId");
		Connection con=null;
		boolean delFlag;
		try {
			con=dbUtil.getCon();
			int delNums=newsDao.newsDelete(con, newsId);
			if(delNums==1){
				delFlag=true;
			}else{
				delFlag=false;
			}
			ResponseUtil.write(delFlag,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
}
