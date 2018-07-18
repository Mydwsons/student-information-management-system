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

@WebServlet("/pageLimitServlet")
public class PageLimitServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNo = req.getParameter("pageNo");
		req.setAttribute("pageNo", pageNo);
		UserDao dao = new UserDao();

		String searchName = (String) req.getSession().getAttribute("searchName");

		int count = dao.getCount();
		List<Student> list = new ArrayList<>();

		list = dao.queryLimit(Integer.parseInt(pageNo), searchName);

		req.getSession().setAttribute("list", list);
		req.getSession().setAttribute("count", count % 5 == 0 ? count / 5 : count / 5 + 1);
//		req.getSession().setAttribute("count", list.size() % 5 == 0 ? list.size() / 5 : list.size() / 5 + 1);
		req.getRequestDispatcher("manage.jsp").forward(req, resp);

	}
}
