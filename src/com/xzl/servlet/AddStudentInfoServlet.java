package com.xzl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xzl.mysql.Student;
import com.xzl.mysql.UserDao;

@WebServlet("/addStudentInfoServlet")
public class AddStudentInfoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		UserDao dao = new UserDao();
		int row = 0;

		String stdname = req.getParameter("b1");

		String stdword = req.getParameter("b2");

		String gender = req.getParameter("b3");
		String age = req.getParameter("b4");
		String address = req.getParameter("b5");
		String phonenumber = req.getParameter("b6");

		PrintWriter pw = resp.getWriter();
		// row = dao.addStudentInfo(stdname, stdword, gender, age, address,
		// phonenumber);
		if (stdname.equals("") || stdword.equals("")) {
			pw.write("<script language='javascript'>alert('学生姓名和密码不能为空！');window.history.go(-1)</script>");
		} else if (Pattern.matches("^([a-zA-Z\\\\u4e00-\\\\u9fa5\\\\·]{1,10})$", stdname)) {
			pw.write("<script language='javascript'>alert('请填写正确的姓名！');window.history.go(-1)</script>");
		} else if (Pattern.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", stdword)) {
			pw.write("<script language='javascript'>alert('密码必须由6-16位数字和字母组合！');window.history.go(-1)</script>");
		} else if (Integer.parseInt(age) <= 0 || Integer.parseInt(age) >= 120 || age.equals("")) {
			pw.write("<script language='javascript'>alert('请输入正确的年龄！');\r\n" + "window.history.go(-1);</script>");
		} else {
			row = dao.addStudentInfo(stdname, stdword, gender, age, address, phonenumber);
			List<Student> list = dao.queryLimit(0, 5);
			int count = dao.getCount();
			req.getSession().setAttribute("list", list);
			req.getSession().setAttribute("count", count % 5 == 0 ? count / 5 : count / 5 + 1);
			// req.setAttribute("list", list);
			pw.write("<script language='javascript'>alert('提交成功！');"
					+ "window.document.location.href='manage.jsp'</script>");

		}

	}

}
