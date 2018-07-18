<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script type="text/javascript">
	</script>
</head>

<body>

	<div class="student-login">
		<div class="student-title">学生信息管理系统</div>
		<div class="student-login-box">
			<div class="student-login-top">
				<span class="on" onclick="window.location.href='login.jsp'">登录</span> 
				<span onclick="window.location.href='register.jsp'">注册</span>
			</div>
			<div class="student-toggle">
				<div class="student-comin student-l">
					<form action="loginServlet" method="POST">
						<li><input type="text" name="username" placeholder="用户名"></li>
						<li><input type="password" name="password" placeholder="密码"></li>
						<li><input class="login" type="submit" value="登 录"></li>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
