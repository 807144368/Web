package com.bright;

import java.sql.*;

//ע���ʱ�򱣴��û���ͨ���û���id��
public class UserDao {

  //��Ϊ�����������Ƕ����ݿ���в���������Ҫ�������ݿ�
  private JDBCUtil util;

  public JDBCUtil getUtil() {
      return util;
  }

  public void setUtil(JDBCUtil util) {
      this.util = util;
  }

  //
  public void saveUser(User user){

      Connection conn = null;
      PreparedStatement stat = null;

      try {
          String sql = "insert into users(username,pass) values (?,?)";
          conn = util.getConnection();        //��������
          stat = conn.prepareStatement(sql);  //����Ԥ�������

          //�洢���ݣ��м����ʺţ��ʹ漸����
          stat.setString(1, user.getUsername());
          stat.setString(2, user.getPass());

          stat.executeUpdate();//����

      } catch (Exception e) {
          e.printStackTrace();
      }finally{

          try {
              if(conn!=null&!conn.isClosed()){
                  conn.close();
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }


  }

  //��¼ʱ��ͨ���û����ҵ����������Ϊ��ѯ������һ���������з���һ��user
  public User getUserByName(String uname){
      User user = null;

      Connection conn = null;
      PreparedStatement stat = null;
      ResultSet res = null;

      try {
          String sql = "select * from users where username = ?";
          conn = util.getConnection();
          stat = conn.prepareStatement(sql);

          stat.setString(1, uname);

          //�Ѳ�ѯ�������ݷŵ��������
          res = stat.executeQuery();//��ѯ

          //�����ѯ�����ͽ�������е����ݱ��浽�û���
          if(res.next()){
              user = new User();
              user.setId(res.getInt("id"));
              user.setUsername(res.getString("username"));
              user.setPass(res.getString("pass"));
          }

      } catch (Exception e) {
          e.printStackTrace();
      }finally{

          try {
              if(conn!=null&!conn.isClosed()){
                  conn.close();
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }

      return user;
  }   
}