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

	        //�ύ��ʽΪpost
	        req.setCharacterEncoding("utf8");

	        //���������еĲ���
	        String username = req.getParameter("username");
	        String pass = req.getParameter("pass");
	        String pass2 = req.getParameter("pass2");
	        
	        resp.setContentType("text/html;charset=utf-8");
	        PrintWriter out = resp.getWriter();

	        //����У��
	        if(username!=null&&username.length()>0&&pass!=null&&pass.length()>0&&pass.equals(pass2)){

	            //Ҫ�������ݿ⣬�ȴ����ñ��л�ȡ��ʼ����������JDBSUtil�Ĺ��캯��
	            ServletContext ctx = this.getServletContext();
	            String url = ctx.getInitParameter("url");
	            String dbuser = ctx.getInitParameter("dbuser");
	            String dbpass = ctx.getInitParameter("dbpass");         
	            JDBCUtil util = new JDBCUtil(url, dbuser, dbpass);

	            //userdao--->util--->���ݿ�
	            //Ȼ��util��Ϊ��������������
	            UserDao dao = new UserDao();
	            dao.setUtil(util);

	            //����ȡ�ĵĲ����浽uer�У����ò������еķ�������
	            User user = new User(username, pass);
	            dao.saveUser(user);

	            out.println("<h1>��ϲ�㣡ע��ɹ�</h1>");
	            out.println("<br><a href='index.jsp'>��˵�¼</a>");
	        }else{

	            out.println("<h1>��������ע��ʧ�ܣ�</h1>");
	            out.println("<br><a href='reg.html'>����ע��</a>");

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
