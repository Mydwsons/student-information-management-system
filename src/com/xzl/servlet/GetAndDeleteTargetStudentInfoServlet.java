package com.xzl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzl.mysql.Student;
import com.xzl.mysql.UserDao;

@WebServlet("/getAndDeleteTargetStudentInfoServlet")
public class GetAndDeleteTargetStudentInfoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// 获取id
		int id = Integer.parseInt(req.getParameter("std_id"));
		// 根据id删除这一条数据
		UserDao dao = new UserDao();

		int row = dao.deleteInfoById(id);
		PrintWriter pw = resp.getWriter();
		if (row > 0) {
			List<Student> list = dao.queryLimit(0, 5);
			int count = dao.getCount();
			req.getSession().setAttribute("list", list);
			req.getSession().setAttribute("count", count % 5 == 0 ? count / 5 : count / 5 + 1);
			pw.write("<script language='javascript'>alert('删除成功！');" + "location.replace(document.referrer);</script>");
		} else {
			pw.write("<script language='javascript'>alert('删除失败！');"
					+ "window.document.location.href='manage.jsp'</script>");
		}

	}
}
