<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title >网页</title>
</head>
<body>
    <h1>学生管理系统</h1>

    <form action="login" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"></td>
        </tr>

        <tr>
            <td>密码：</td>
            <td><input type="password" name="pass"></td>
        </tr>

        <tr>
            <td><input type="submit" value="登录"> </td>
            <td><a href="reg.html">没有账号？点我注册</a></td>
            <td><input type="checkbox" name="cookie" >一周免密登录</td>
        </tr>
    </table>
    </form>
  </body>
</html>
