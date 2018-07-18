package com.xzl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzl.mysql.Student;
import com.xzl.mysql.User;
import com.xzl.mysql.UserDao;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			byte[] hash = MessageDigest.getInstance("SHA-256").digest(password.getBytes());
			password = new BigInteger(1, hash).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserDao dao = new UserDao();
		User user = null;
		user = dao.validateLoginInfo(username, password);

		if (user != null) {
			PrintWriter pw = resp.getWriter();
			// System.out.println(username);

			req.getSession(false).setAttribute("uname", username);
			List<Student> list = new ArrayList<>();

			list = dao.queryLimit(0, 5);
			int count = dao.getCount();
			req.getSession().setAttribute("list", list);
			req.getSession().setAttribute("count", count % 5 == 0 ? count / 5 : count / 5 + 1);

			// req.setAttribute("list", list);

//			req.getRequestDispatcher("manage.jsp").forward(req, resp);
			pw.write("<script language='javascript'>alert('登录成功！欢迎您！');"
					+ "window.document.location.href='manage.jsp'</script>");

//			req.setAttribute("uname", username);
//			req.getRequestDispatcher("manage.jsp").forward(req, resp);
//			pw.write("<script language='javascript'>window.document.location.href='manage.jsp'</script>");
		} else {
			PrintWriter pw = resp.getWriter();
			pw.write("<script language='javascript'>alert('登录失败！');"
					+ "window.document.location.href='login.jsp'</script>");
		}
	}
}
