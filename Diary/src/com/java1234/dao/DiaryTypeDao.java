package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java1234.model.DiaryType;

public class DiaryTypeDao {

	public List<DiaryType> diaryTypeCountList(Connection con)throws Exception{
		List<DiaryType> diaryTypeCountList=new ArrayList<DiaryType>();
		String sql="SELECT diaryTypeId,typeName,COUNT(diaryId) as diaryCount FROM t_diary RIGHT JOIN t_diaryType ON t_diary.typeId=t_diaryType.diaryTypeId GROUP BY typeName;";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			DiaryType diaryType=new DiaryType();
			diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
			diaryType.setTypeName(rs.getString("typeName"));
			diaryType.setDiaryCount(rs.getInt("diaryCount"));
			diaryTypeCountList.add(diaryType);
		}
		return diaryTypeCountList;
	}
	
	public List<DiaryType> diaryTypeList(Connection con)throws Exception{
		List<DiaryType> diaryTypeList=new ArrayList<DiaryType>();
		String sql="select * from t_diaryType";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			DiaryType diaryType=new DiaryType();
			diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
			diaryType.setTypeName(rs.getString("typeName"));
			diaryTypeList.add(diaryType);
		}
		return diaryTypeList;
	}
	
	public int diaryTypeAdd(Connection con,DiaryType diaryType)throws Exception{
		String sql="insert into t_diaryType values(null,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, diaryType.getTypeName());
		return pstmt.executeUpdate();
	}
	
	public int diaryTypeUpdate(Connection con,DiaryType diaryType)throws Exception{
		String sql="update t_diaryType set typeName=? where diaryTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, diaryType.getTypeName());
		pstmt.setInt(2, diaryType.getDiaryTypeId());
		return pstmt.executeUpdate();
	}
	
	public DiaryType diaryTypeShow(Connection con,String diaryTypeId)throws Exception{
		String sql="SELECT * from t_diaryType where diaryTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, diaryTypeId);
		ResultSet rs=pstmt.executeQuery();
		DiaryType diaryType=new DiaryType();
		if(rs.next()){
			diaryType.setDiaryTypeId(rs.getInt("diaryTypeId"));
			diaryType.setTypeName(rs.getString("typeName"));
		}
		return diaryType;
	}
	
	public int diaryTypeDelete(Connection con,String diaryTypeId)throws Exception{
		String sql="delete from t_diaryType where diaryTypeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, diaryTypeId);
		return pstmt.executeUpdate();
	}
}
