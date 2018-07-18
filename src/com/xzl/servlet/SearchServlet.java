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

@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		String pageNo = req.getParameter("pageNo");
		req.setAttribute("pageNo", pageNo);
		String searchName = req.getParameter("searchName");

		if (pageNo == null) {
			pageNo = "" + 0;
		}
		UserDao dao = new UserDao();

		List<Student> list = dao.fuzzySearch(searchName, Integer.parseInt(pageNo));
		req.getSession().setAttribute("searchName", searchName);

		int count = dao.getCount();
		if (list.size() == 0) {
			PrintWriter pw = resp.getWriter();
			pw.write("<script language='javascript'>alert('没有匹配该字段的数据！');"
					+ "window.document.location.href='manage.jsp'</script>");
		} else {
			req.getSession().setAttribute("list", list);
			count = dao.getCount(searchName);
			req.getSession().setAttribute("count", count % 5 == 0 ? count / 5 : count / 5 + 1);
			req.getRequestDispatcher("manage.jsp").forward(req, resp);
		}
	}
}
