
package com.blog.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

public class FileUploadServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(FileUploadServlet.class);

	private static final long serialVersionUID = 2214176298135939640L;

	public static final long MAX_SIZE = 1024 * 1024 * 1024 * 10;

	private String fileUploadPath;// 文件存放路径
	private String realFileName;// 存入数据库文件名
	
	private long fileSize;

	@Override
	public void init(ServletConfig config) throws ServletException {
		logger.info("初始化上传配置信息");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/plain");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter outNet = null;
		fileUploadPath = request.getSession().getServletContext().getRealPath("/")+"uploads/";
		try {
			// 向客户端发送响应正文
			outNet = response.getWriter();
			// 创建一个基于硬盘的FileItem工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置向硬盘写数据时所用的缓冲区的大小，此处为4M
			factory.setSizeThreshold(40 * 1024);
			// 设置临时目录
			File tempFile = new File(fileUploadPath);
			if (!tempFile.exists())
				tempFile.mkdir();
			factory.setRepository(tempFile);
			
			// 创建一个文件上传处理器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置允许上传的文件的最大尺寸，此处为4M
			upload.setSizeMax(MAX_SIZE);

			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				
				if (!item.isFormField()) 
					processUploadedFile(item, outNet); // 处理上传文件
			}
			outNet.flush();
			
			outNet.println("{msg:\"" + realFileName+ "\",fileSize:\"" + fileSize+ "\"}");
			
		} catch (Exception e) {
			logger.error("上传文件出现异常：" + e.getMessage());
		} finally{
			if(null != outNet)
				outNet.close();
		}
	}

	/**
	 * 处理上传文件
	 * 
	 * @param item
	 * @param outNet
	 * @throws Exception
	 */
	private void processUploadedFile(FileItem item, PrintWriter outNet)
			throws Exception {
		String filename = item.getName();
		int index = filename.lastIndexOf("//");
		filename = filename.substring(index + 1, filename.length());
		fileSize = item.getSize();
		
		realFileName = reName(filename);
		
		if (realFileName.equals("") && filename.equals("") && fileSize == 0)
			return;
		File uploadedFile = new File(fileUploadPath + "/" + realFileName);
		item.write(uploadedFile);
//		logger.info("uploads" + fileUploadPath  +"/" + realFileName);
	}
	
	private String reName(String name) {
		// 获得文件后缀
		if (name.indexOf(".") != -1) {
			// 如果是图片则修改名字，否则不修改名字
			String newName = DateUtils.getCurTime()+RandomUtils.getRandomStr(2);
			String suffix = name.substring(name.lastIndexOf("."),name.length());
			name = newName + suffix;
		}
		return name;
	}
	
}
