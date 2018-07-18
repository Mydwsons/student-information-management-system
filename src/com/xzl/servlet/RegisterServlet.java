package com.xzl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xzl.mysql.UserDao;

/**
 * 注册
 * 
 * @author Miicocozzzz
 *
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
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
		if (username.equals("") || password.equals("")) {
			PrintWriter pw = resp.getWriter();
			pw.write("<script language='javascript'>alert('用户名和密码不能为空');"
					+ "window.document.location.href='register.jsp'</script>");
		} else {
			if (dao.isUsernameExist(username)) {
				PrintWriter pw = resp.getWriter();
				pw.write("<script language='javascript'>alert('用户名已存在');"
						+ "window.document.location.href='register.jsp'</script>");
			} else {
				dao.register(username, password);
				PrintWriter pw = resp.getWriter();
				pw.write("<script language='javascript'>alert('注册成功！');"
						+ "window.document.location.href='login.jsp'</script>");
			}
		}
	}
}
