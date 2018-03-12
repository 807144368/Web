package com.bright;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        //获取请求参数
	        req.setCharacterEncoding("utf-8");
	        String username = req.getParameter("username");
	        String pass = req.getParameter("pass");

	        resp.setContentType("text/html;charset=utf-8");
	        PrintWriter out = resp.getWriter();

	        //如果用户名和密码不为空，并且长度大于0就执行if语句--->判断查询到的user--->判断密码是否一致
	        if(username!=null&&username.length()>0&&pass!=null&&pass.length()>0){

	            //要连接数据库，先从配置表中获取初始化参数传给JDBSUtil的构造函数
	            ServletContext ctx = this.getServletContext();
	            String url = ctx.getInitParameter("url");
	            String dbuser = ctx.getInitParameter("dbuser");
	            String dbpass = ctx.getInitParameter("dbpass");

	            JDBCUtil util = new JDBCUtil(url, dbuser, dbpass);      

	            //然后将util作为参数传给操作类
	            UserDao dao = new UserDao();
	            dao.setUtil(util);

	            //调用操作类的通过用户名查询用户
	            User user = dao.getUserByName(username);

	            if(user!=null){
	                if(user.getPass().equals(pass)){
	                    out.println("<h1>恭喜你！登录成功！！</h1>");
	                }else{
	                    out.println("<h1>登录失败！密码错误！</h1>");
	                    out.println("<br><a href='index.jsp'>点此登录</a>");
	                }
	            }else{
	                out.println("<h1>登录失败！用户名错误！</h1>");
	                out.println("<br><a href='index.jsp'>点此登录</a>");
	            }

	        }else{
	            out.println("<h1>登录失败！用户名/密码错误！</h1>");
	            out.println("<br><a href='index.jsp'>点此登录</a>");
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
