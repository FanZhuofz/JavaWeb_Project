package com.dawn.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dawn.dao.StudentDao;
import com.dawn.model.Student;
import com.dawn.util.DbUtil;
import com.dawn.util.ResponseUtil;
import com.dawn.util.StringUtil;

import net.sf.json.JSONObject;

public class StudentSaveServlet extends HttpServlet{
	DbUtil dbUtil=new DbUtil();
	StudentDao studentDao=new StudentDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		String sex=request.getParameter("sex");
		String dept=request.getParameter("dept");
		String tel=request.getParameter("tel");
		String email=request.getParameter("email");
		String id=request.getParameter("id");
		Student student=new Student(userName,password,name,age,sex,dept,tel,email);
		
		if(StringUtil.isNotEmpty(id)){
			student.setId(Integer.parseInt(id));
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(id)){
				saveNums=studentDao.studentModify(con, student);
			}else{
				saveNums=studentDao.studentAdd(con, student);
			}
			if(saveNums>0){
				result.put("success", "true");
			}else{
				result.put("success", "true");
				result.put("errorMsg", "保存失败");
			}
			ResponseUtil.write(response, result);
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

	
	
}
