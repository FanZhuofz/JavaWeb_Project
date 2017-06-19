package xyz.dawnfan.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
//import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import xyz.dawnfan.util.DateUtil;
import xyz.dawnfan.util.PropertiesUtil;

public class ImageUploadServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			//List<FileItem> list = upload.parseRequest((RequestContext) request);
			List<FileItem> list = (List<FileItem>)upload.parseRequest(new ServletRequestContext(request));
			//List<FileItem> list = upload.parseRequest(request);
			for (FileItem fileItem : list) {
				String imageName=DateUtil.getCurrentDateStr();
				File file = new File(PropertiesUtil.getValue("imagePath") + imageName +"."+fileItem.getName().split("\\.")[1]);
				String newPath = PropertiesUtil.getValue("imageFile") + "/"+ imageName + "."+fileItem.getName().split("\\.")[1];
				fileItem.write(file);
				String callback = request.getParameter("CKEditorFuncNum");
				out.println("<script type=\"text/javascript\">");
				out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + newPath + "',''" + ")");
				out.println("</script>");
				out.flush();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			out.close();
		}
	}
	
}
