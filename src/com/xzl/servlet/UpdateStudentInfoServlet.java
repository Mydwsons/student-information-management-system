package com.xzl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzl.mysql.Student;
import com.xzl.mysql.UserDao;

@WebServlet("/updateStudentInfoServlet")
public class UpdateStudentInfoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		UserDao dao = new UserDao();

		String stdname = req.getParameter("b1");
		String stdword = req.getParameter("b2");
		try {
			byte[] hash = MessageDigest.getInstance("SHA-256").digest(stdword.getBytes());
			stdword = new BigInteger(1, hash).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String gender = req.getParameter("b3");

		String age = req.getParameter("b4");
		String address = req.getParameter("b5");
		String phonenumber = req.getParameter("b6");
		PrintWriter pw = resp.getWriter();

		if (stdname.equals("") || stdword.equals("") || address.equals("") || age.equals("") || gender.equals("")
				|| phonenumber.equals("")) {
			pw.write("<script language='javascript'>alert('信息不能为空！');" + "window.history.go(-1)</script>");
		} else {
			int row = dao.updateStudentInfoByStdname(stdname, stdname, stdword, gender, age, address, phonenumber);
			if (row > 0) {
				List<Student> list = dao.queryLimit(0, 5);
				int count = dao.getCount();
				req.getSession().setAttribute("list", list);
				req.getSession().setAttribute("count", count % 5 == 0 ? count / 5 : count / 5 + 1);

				// req.setAttribute("list", list);

//				req.getRequestDispatcher("manage.jsp").forward(req, resp);
				pw.write("<script language='javascript'>alert('提交成功！');"
						+ "window.document.location.href='manage.jsp'</script>");
			} else {
				pw.write("<script language='javascript'>alert('提交失败！');"
						+ "window.document.location.href='update.jsp'</script>");
			}
		}
	}
}
