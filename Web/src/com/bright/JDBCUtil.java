package com.bright;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	   private String url;     //url��ַ
	    private String dbuser;  //���ݿ���û���
	    private String dbpass;  //���ݿ�ĵ�¼����

	    public String getUrl() {
	        return url;
	    }
	    public void setUrl(String url) {
	        this.url = url;
	    }
	    public String getDbuser() {
	        return dbuser;
	    }
	    public void setDbuser(String dbuser) {
	        this.dbuser = dbuser;
	    }
	    public String getDbpass() {
	        return dbpass;
	    }
	    public void setDbpass(String dbpass) {
	        this.dbpass = dbpass;
	    }

	    public JDBCUtil() {
	        super();
	    }

	    public JDBCUtil(String url, String dbuser, String dbpass) {
	        super();
	        this.url = url;
	        this.dbuser = dbuser;
	        this.dbpass = dbpass;
	    }

	    //��̬����飬��������
	    static{
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }


	    //�����ṩһ���������ݿ�ķ���
	    public Connection getConnection() throws Exception{
	        return DriverManager.getConnection(this.url, this.dbuser, this.dbpass);
	    }


}
