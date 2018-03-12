package com.bright;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegServlet extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        //提交方式为post
	        req.setCharacterEncoding("utf8");

	        //接收请求中的参数
	        String username = req.getParameter("username");
	        String pass = req.getParameter("pass");
	        String pass2 = req.getParameter("pass2");
	        
	        resp.setContentType("text/html;charset=utf-8");
	        PrintWriter out = resp.getWriter();

	        //输入校验
	        if(username!=null&&username.length()>0&&pass!=null&&pass.length()>0&&pass.equals(pass2)){

	            //要连接数据库，先从配置表中获取初始化参数传给JDBSUtil的构造函数
	            ServletContext ctx = this.getServletContext();
	            String url = ctx.getInitParameter("url");
	            String dbuser = ctx.getInitParameter("dbuser");
	            String dbpass = ctx.getInitParameter("dbpass");         
	            JDBCUtil util = new JDBCUtil(url, dbuser, dbpass);

	            //userdao--->util--->数据库
	            //然后将util作为参数传给操作类
	            UserDao dao = new UserDao();
	            dao.setUtil(util);

	            //将获取的的参数存到uer中，调用操作类中的方法保存
	            User user = new User(username, pass);
	            dao.saveUser(user);

	            out.println("<h1>恭喜你！注册成功</h1>");
	            out.println("<br><a href='index.jsp'>点此登录</a>");
	        }else{

	            out.println("<h1>参数有误，注册失败！</h1>");
	            out.println("<br><a href='reg.html'>重新注册</a>");

	        }

	        out.flush();
	        out.close();


	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        // TODO Auto-generated method stub
	        doGet(req, resp);
	    }       
	

}
