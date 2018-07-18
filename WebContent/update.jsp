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

<title>修改学生信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/main.css">

</head>

<body class="sd">
	<div class="sd-l">
		<div class="sd-l-top">
			<img src="image/user.jpg" width="100" height="100"> <a href="manage.jsp">管理员-${uname }</a>
		</div>
		<div class="sd-l-list">
			<li class="sd-list-li on"><span class="sd-list-title"><i
					class="icon-sz"></i>系统管理<i class="icon-fx"></i></span>
				<ul>
					<li class="on"><a href="add.jsp"><i class="icon-add"></i>添加学员</a>
					</li>
					<li><a href="manage.jsp"><i class="icon-list"></i>学员列表</a></li>
				</ul></li>
		</div>
	</div>
	<div class="sd-r">
		<div class="sd-r-top"></div>
		<div class="sd-r-bottom">
			<div class="sd-r-title">修改学员</div>
			<div class="sd-r-box">
				<div class="sd-center-add">
					<form action="updateStudentInfoServlet" method="POST">
						<li><label>学生姓名：</label> <input type="text" placeholder=""
							name="b1" value="${student.stdname }" required="required"></li>
						<li><label>学生密码：</label> <input type="text" placeholder=""
							name="b2" value="${student.stdword }" required="required"></li>
						<li><label>学生性别：</label> <input type="text" placeholder=""
							name="b3" value="${student.gender }" required="required"></li>
						<li><label>学生年龄：</label> <input type="text" placeholder=""
							name="b4" value="${student.age }" required="required"
							pattern="^(?:[1-9]?\d|100)$"></li>
						<li><label>学生地址：</label> <input type="text" placeholder=""
							name="b5" value="${student.address }" required="required"></li>
						<li><label>学生电话：</label> <input type="text" placeholder=""
							name="b6" value="${student.phonenumber }" required="required"
							pattern="^1[358]\d{9}$"></li> <input class="submit"
							type="submit" value="提交">
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.2.1.min.js"></script>
	<script>
		$(".icon-fx").on('click', function() {
			$(".sd-list-li ul").toggle();
			$(this).toggleClass("on");
		})
	</script>
</body>
</html>
