package xyz.dawnfan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import xyz.dawnfan.model.News;
import xyz.dawnfan.model.PageBean;
import xyz.dawnfan.util.DateUtil;
import xyz.dawnfan.util.DbUtil;
import xyz.dawnfan.util.PropertiesUtil;
import xyz.dawnfan.util.StringUtil;

public class NewsDao {

	public List<News> newsList(Connection con,String sql)throws Exception{
		List<News> newsList=new ArrayList<News>();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			News news=new News();
			news.setNewsId(rs.getInt("newsId"));
			news.setTitle(rs.getString("title"));
			news.setContent(rs.getString("content"));
			news.setPublishDate(DateUtil.formatString(rs.getString("publishDate"), "yyyy-MM-dd HH:mm:ss"));
			news.setAuthor(rs.getString("author"));
			news.setTypeId(rs.getInt("typeId"));
			news.setClick(rs.getInt("click"));
			news.setIsHead(rs.getInt("isHead"));
			news.setIsImage(rs.getInt("isImage"));
			news.setImageName(PropertiesUtil.getValue("userImage")+rs.getString("imageName"));
			newsList.add(news);
		}
		return newsList;
	}
	
	public List<News> newsList(Connection con,News s_news,PageBean pageBean,String s_bPublishDate,String s_aPublishDate)throws Exception{
		List<News> newsList=new ArrayList<News>();
		StringBuffer sb=new StringBuffer("select * from t_news t1,t_newsType t2 where t1.typeId=t2.newsTypeId ");
		if(StringUtil.isNotEmpty(s_news.getTitle())){
			sb.append(" and t1.title like '%"+s_news.getTitle()+"%'");
		}
		if(s_news.getTypeId()!=-1){
			sb.append(" and t1.typeId="+s_news.getTypeId());
		}
		if(StringUtil.isNotEmpty(s_bPublishDate)){
			sb.append(" and TO_DAYS(t1.publishDate)>=TO_DAYS('"+s_bPublishDate+"')");
		}
		if(StringUtil.isNotEmpty(s_aPublishDate)){
			sb.append(" and TO_DAYS(t1.publishDate)<=TO_DAYS('"+s_aPublishDate+"')");
		}
		sb.append(" ORDER BY t1.publishDate  DESC");
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			News news=new News();
			news.setNewsId(rs.getInt("newsId"));
			news.setTitle(rs.getString("title"));
			news.setContent(rs.getString("content"));
			news.setPublishDate(DateUtil.formatString(rs.getString("publishDate"), "yyyy-MM-dd HH:mm:ss"));
			news.setAuthor(rs.getString("author"));
			news.setTypeId(rs.getInt("typeId"));
			news.setTypeName(rs.getString("typeName"));
			news.setClick(rs.getInt("click"));
			news.setIsHead(rs.getInt("isHead"));
			news.setIsImage(rs.getInt("isImage"));
			news.setImageName(PropertiesUtil.getValue("userImage")+rs.getString("imageName"));
			newsList.add(news);
		}
		return newsList;
	}
	
	public int newsCount(Connection con,News s_news,String s_bPublishDate,String s_aPublishDate)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_news ");
		if(s_news.getTypeId()!=-1){
			sb.append(" and typeId="+s_news.getTypeId());
		}
		if(StringUtil.isNotEmpty(s_bPublishDate)){
			sb.append(" and TO_DAYS(publishDate)>=TO_DAYS('"+s_bPublishDate+"')");
		}
		if(StringUtil.isNotEmpty(s_aPublishDate)){
			sb.append(" and TO_DAYS(publishDate)<=TO_DAYS('"+s_aPublishDate+"')");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public News getNewsById(Connection con,String newsId)throws Exception{
		String sql="select * from t_news t1,t_newsType t2 where t1.typeId=t2.newsTypeId and t1.newsId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, newsId);
		ResultSet rs=pstmt.executeQuery();
		News news=new News();
		if(rs.next()){
			news.setNewsId(rs.getInt("newsId"));
			news.setTitle(rs.getString("title"));
			news.setContent(rs.getString("content"));
			news.setPublishDate(DateUtil.formatString(rs.getString("publishDate"), "yyyy-MM-dd HH:mm:ss"));
			news.setAuthor(rs.getString("author"));
			news.setTypeName(rs.getString("typeName"));
			news.setTypeId(rs.getInt("typeId"));
			news.setClick(rs.getInt("click"));
			news.setIsHead(rs.getInt("isHead"));
			news.setIsImage(rs.getInt("isImage"));
			news.setIsHot(rs.getInt("isHot"));
		}
		return news;
	}
	
	public List<News> getUpAndDownPageId(Connection con,String newsId)throws Exception{
		List<News> upAndDownPage=new ArrayList<News>();
		String sql="SELECT * FROM t_news WHERE newsId<? ORDER BY newsId DESC LIMIT 1";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, newsId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			upAndDownPage.add(new News(rs.getInt("newsId"),rs.getString("title")));
		}else{
			upAndDownPage.add(new News(-1,""));
		}
		
		sql="SELECT * FROM t_news WHERE newsId>? ORDER BY newsId ASC LIMIT 1 ";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, newsId);
		rs=pstmt.executeQuery();
		if(rs.next()){
			upAndDownPage.add(new News(rs.getInt("newsId"),rs.getString("title")));
		}else{
			upAndDownPage.add(new News(-1,""));
		}
		return upAndDownPage;
	}
	
	public int newsAdd(Connection con,News news)throws Exception{
		String sql="insert into t_news values(null,?,?,now(),?,?,0,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, news.getTitle());
		pstmt.setString(2, news.getContent());
		pstmt.setString(3, news.getAuthor());
		pstmt.setInt(4, news.getTypeId());
		pstmt.setInt(5, news.getIsHead());
		pstmt.setInt(6, news.getIsImage());
		pstmt.setString(7, news.getImageName());
		pstmt.setInt(8, news.getIsHot());
		return pstmt.executeUpdate();
	}
	
	public int newsUpdate(Connection con,News news)throws Exception{
		String sql="update t_news set title=?,content=?,author=?,typeId=?,isHead=?,isImage=?,imageName=?,isHot=? where newsId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, news.getTitle());
		pstmt.setString(2, news.getContent());
		pstmt.setString(3, news.getAuthor());
		pstmt.setInt(4, news.getTypeId());
		pstmt.setInt(5, news.getIsHead());
		pstmt.setInt(6, news.getIsImage());
		pstmt.setString(7, news.getImageName());
		pstmt.setInt(8, news.getIsHot());
		pstmt.setInt(9, news.getNewsId());
		return pstmt.executeUpdate();
	}
	
	public int newsClick(Connection con,String newsId)throws Exception{
		String sql="update t_news set click=click+1 where newsId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, newsId);
		return pstmt.executeUpdate();
	}
	
	public int newsDelete(Connection con,String newsId)throws Exception{
		String sql="delete from t_news where newsId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, newsId);
		return pstmt.executeUpdate();
	}
	
	public boolean existNewsWithNewsTypeId(Connection con,String typeId)throws Exception{
		String sql="select * from t_news where typeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, typeId);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		try {
			System.out.println(new NewsDao().getUpAndDownPageId(new DbUtil().getCon(), "1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
