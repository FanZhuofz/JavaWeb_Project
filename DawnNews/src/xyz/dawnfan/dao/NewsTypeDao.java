package xyz.dawnfan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import xyz.dawnfan.model.NewsType;

public class NewsTypeDao {
	
	public List<NewsType> newsTypeList(Connection con)throws Exception{
		List<NewsType> newsTypeList=new ArrayList<NewsType>();
		String sql="select * from t_newsType";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			NewsType newsType=new NewsType();
			newsType.setNewsTypeId(rs.getInt("newsTypeId"));
			newsType.setTypeName(rs.getString("typeName"));
			newsTypeList.add(newsType);
		}
		return newsTypeList;
	}
	
	public NewsType getNewsTypeById(Connection con,String newsTypeId)throws Exception {
		String sql="select * from t_newsType where newsTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, newsTypeId);
		ResultSet rs=pstmt.executeQuery();
		NewsType newsType=new NewsType();
		if(rs.next()){
			newsType.setNewsTypeId(rs.getInt("newsTypeId"));
			newsType.setTypeName(rs.getString("typeName"));
		}
		return newsType;
	}
	
	public int newsTypeAdd(Connection con,NewsType newsType)throws Exception{
		String sql="insert into t_newsType values(null,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, newsType.getTypeName());
		return pstmt.executeUpdate();
	}
	
	public int newsTypeUpdate(Connection con,NewsType newsType)throws Exception{
		String sql="update t_newsType set typeName=? where newsTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, newsType.getTypeName());
		pstmt.setInt(2, newsType.getNewsTypeId());
		return pstmt.executeUpdate();
	}
	
	public int newsTypeDelete(Connection con,String newsTypeId)throws Exception{
		String sql="delete from t_newsType where newsTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, newsTypeId);
		return pstmt.executeUpdate();
	}
	
	
}
