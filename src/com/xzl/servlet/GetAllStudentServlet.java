package com.xzl.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzl.mysql.Student;
import com.xzl.mysql.UserDao;

@WebServlet("/getAllStudentServlet")
public class GetAllStudentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		UserDao dao = new UserDao();
		List<Student> list = new ArrayList<>();

		list = dao.queryLimit(0, 5);
		int count = dao.getCount();
		String searchName = "";

		req.getSession().setAttribute("searchName", searchName);
		req.getSession().setAttribute("list", list);
		req.getSession().setAttribute("count", count % 5 == 0 ? count / 5 : count / 5 + 1);

		// req.setAttribute("list", list);

		req.getRequestDispatcher("manage.jsp").forward(req, resp);
	}
}
