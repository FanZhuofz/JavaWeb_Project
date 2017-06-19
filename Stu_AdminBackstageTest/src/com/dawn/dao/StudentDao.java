package com.dawn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dawn.model.PageBean;
import com.dawn.model.Student;
import com.dawn.util.StringUtil;

public class StudentDao {

	public ResultSet studentList(Connection con,PageBean pageBean,Student student)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_user");
		if(StringUtil.isNotEmpty(student.getUserName())){
			sb.append(" and userName like '%"+student.getUserName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public int studentCount(Connection con,Student student)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_user");
		if(StringUtil.isNotEmpty(student.getUserName())){
			sb.append(" and userName like '%"+student.getUserName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	/**
	 * delete from tableName where field in (1,3,5)
	 * @param con
	 * @param delIds
	 * @return
	 * @throws Exception
	 */
	//删除
	public int studentDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_user where id in("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	//添加
	public int studentAdd(Connection con,Student student)throws Exception{
		String sql="insert into t_user values(null,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getUserName());
		pstmt.setString(2, student.getPassword());
		pstmt.setString(3, student.getName());
		pstmt.setInt(4, student.getAge());
		pstmt.setString(5, student.getSex());
		pstmt.setString(6, student.getDept());
		pstmt.setString(7, student.getTel());
		pstmt.setString(8, student.getEmail());
		return pstmt.executeUpdate();
	}
	//修改
	public int studentModify(Connection con,Student student)throws Exception{
		String sql="update t_user set userName=?,password=?,name=?,age=?,sex=?,dept=?,tel=?,email=?  where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getUserName());
		pstmt.setString(2, student.getPassword());
		pstmt.setString(3, student.getName());
		pstmt.setInt(4, student.getAge());
		pstmt.setString(5, student.getSex());
		pstmt.setString(6, student.getDept());
		pstmt.setString(7, student.getTel());
		pstmt.setString(8, student.getEmail());
		pstmt.setInt(9, student.getId());
		return pstmt.executeUpdate();
	}
	
}
