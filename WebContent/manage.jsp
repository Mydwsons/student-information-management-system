
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>学生信息页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/main.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	
</script>
<style>
#sd-r-bottom-top {
	border-bottom: 1px solid #ddd;
	width: 100%;
}

#sd-r-exit {
	display: inline-block;
	line-height: 50px;
	width: 92%;
	padding-left: 20px;
	font-weight: 600;
	border-bottom: 0px solid #ddd;
}

#addStudent {
	display: inline-block;
	border-bottom: 0px solid #ddd;
}

#sd-r-exit a {
	color: #676968;
	margin-left: 1100px;
}

#addstu {
	border-right: 1px solid #a7a2a2;
}
</style>
</head>

<body class="sd">
	<c:if test="${empty list }">
		<script type="text/javascript">
			location = 'getAllStudentServlet'
		</script>
	</c:if>
	<c:if test="${empty uname }">
		<script type="text/javascript">
			location = 'dontServlet'
		</script>
	</c:if>
	<div class="sd-l">
		<div class="sd-l-top">
			<img src="image/user.jpg" width="100" height="100"> <a
				href="manage.jsp">管理员-${uname }</a>
		</div>
		<div class="sd-l-list">
			<li class="sd-list-li on"><span class="sd-list-title"><i
					class="icon-sz"></i>系统管理<i class="icon-fx"></i></span>
				<ul>
					<li><a href="add.jsp"><i class="icon-add"></i>添加学员</a></li>
					<li><a href="manage.jsp"><i class="icon-list"></i>学员列表</a></li>
				</ul></li>
		</div>
	</div>
	<div class="sd-r">
		<a class="sd-r-box" href=""></a>
		<div class="sd-r-top"></div>
		<div class="sd-r-bottom">
			<div id="sd-r-bottom-top">
				<div class="sd-r-title" id="addStudent">添加学员</div>
				<div id="sd-r-exit">
					<a href="logoutServlet">注销</a>
				</div>
			</div>
			<div class="sd-box-opt">
				<div class="sd-opt-l fl">
					<a href="add.jsp"><span class="opt-add" id="addstu"></span></a>
				</div>
				<form action="searchServlet">
					<div class="sd-opt-r fr">
						<input type="text" placeholder="搜索" name="searchName"> <input
							type="submit" value="搜索">
					</div>
				</form>
			</div>
			<div class="sd-box-center">
				<table>
					<thead>
						<tr>
							<th><input type="checkbox"></th>
							<th>学生姓名</th>
							<th>学生性别</th>
							<th>学生年龄</th>
							<th>学生电话</th>
							<th>学生地址</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:forEach items="${list }" var="student">
							<tr>
								<td><input type="checkbox"></td>
								<td>${student.stdname }</td>
								<td>${student.gender }</td>
								<td>${student.age }</td>
								<td>${student.phonenumber }</td>
								<td>${student.address }</td>
								<td><a
									href="getAndDeleteTargetStudentInfoServlet?std_id=${student.id }"
									onclick="return confirm('确认删除吗？')">删除</a> <a
									href="getTargetStudentInfoServlet?std_id=${student.id }">修改</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="studio-table-page">
					<a href="pageLimitServlet?pageNo=${pageNo==1?1:pageNo-1 }">上一页</a>
					<c:forEach begin="1" end="${count }" step="1" varStatus="sta">
						<a href="pageLimitServlet?pageNo=${sta.count }">${sta.count }</a>
					</c:forEach>
					<a
						href="pageLimitServlet?pageNo=${pageNo+1>count?pageNo:pageNo+1 }">下一页</a>
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
