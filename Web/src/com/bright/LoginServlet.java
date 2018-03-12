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
	        //��ȡ�������
	        req.setCharacterEncoding("utf-8");
	        String username = req.getParameter("username");
	        String pass = req.getParameter("pass");

	        resp.setContentType("text/html;charset=utf-8");
	        PrintWriter out = resp.getWriter();

	        //����û��������벻Ϊ�գ����ҳ��ȴ���0��ִ��if���--->�жϲ�ѯ����user--->�ж������Ƿ�һ��
	        if(username!=null&&username.length()>0&&pass!=null&&pass.length()>0){

	            //Ҫ�������ݿ⣬�ȴ����ñ��л�ȡ��ʼ����������JDBSUtil�Ĺ��캯��
	            ServletContext ctx = this.getServletContext();
	            String url = ctx.getInitParameter("url");
	            String dbuser = ctx.getInitParameter("dbuser");
	            String dbpass = ctx.getInitParameter("dbpass");

	            JDBCUtil util = new JDBCUtil(url, dbuser, dbpass);      

	            //Ȼ��util��Ϊ��������������
	            UserDao dao = new UserDao();
	            dao.setUtil(util);

	            //���ò������ͨ���û�����ѯ�û�
	            User user = dao.getUserByName(username);

	            if(user!=null){
	                if(user.getPass().equals(pass)){
	                    out.println("<h1>��ϲ�㣡��¼�ɹ�����</h1>");
	                }else{
	                    out.println("<h1>��¼ʧ�ܣ��������</h1>");
	                    out.println("<br><a href='index.jsp'>��˵�¼</a>");
	                }
	            }else{
	                out.println("<h1>��¼ʧ�ܣ��û�������</h1>");
	                out.println("<br><a href='index.jsp'>��˵�¼</a>");
	            }

	        }else{
	            out.println("<h1>��¼ʧ�ܣ��û���/�������</h1>");
	            out.println("<br><a href='index.jsp'>��˵�¼</a>");
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
