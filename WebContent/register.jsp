<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>登录页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/main.css">
<script src="js/jquery-3.2.1.min.js"></script>
<style type="text/css">
#code {
	width: 145px;
	float: right;
	font-family: "FreeStyle Script";
	height: 35px;
	line-height: 35px;
	font-size: 40px;
	background-color: #1ab395;
	color: #fff;
	text-align: center;
	display: inline-block;
	cursor: pointer;
	font-style: italic;
}
</style>
</head>
<script type="text/javascript">
	function f1() {
		alert("注册成功！3秒后跳转登录界面！");
	}
</script>
<body>
	<div class="student-login">
		<div class="student-title">学生信息管理系统</div>
		<div class="student-login-box">
			<div class="student-login-top">
				<span onclick="window.location.href='login.jsp'">登录</span> <span
					class="on" onclick="window.location.href='register.jsp'">注册</span>
			</div>
			<div class="student-toggle">
				<div class="student-comin student-r ">
					<form action="registerServlet" method="POST">
						<li><input type="text" name="username" placeholder="用户名"></li>
						<li><input type="password" name="password" placeholder="密码"></li>
						<li><input class="reg" type="submit" value="注册" onclick="f1"></li>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
