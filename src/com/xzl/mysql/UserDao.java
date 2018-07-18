package com.xzl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet set = null;

	/**
	 * 用户注册
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 */
	public int register(String name, String pwd) {
		int row = 0;

		conn = DBUtil.getConnection();

		String sql = "insert into UserAccount(username,password) value(?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			// 给占位符赋值
			pstm.setString(1, name);
			pstm.setString(2, pwd);
			row = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstm, conn, set);
		}
		return row;
	}

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 * @return
	 */
	public boolean isUsernameExist(String username) {
		int count = 0;
		conn = DBUtil.getConnection();
		String sql = "SELECT COUNT(*) COUNT FROM UserAccount WHERE username= ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			set = pstm.executeQuery();
			while (set.next()) {
				count = set.getInt("COUNT");
			}
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstm, conn, set);
		}
		return false;
	}

	/**
	 * 验证登录信息是否正确，正确则返回一个User类型，错误则返回NULL
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User validateLoginInfo(String username, String password) {
		User user = null;
		if (this.isUsernameExist(username)) {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM UserAccount WHERE username=?";
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, username);
				ResultSet set = pstm.executeQuery();
				String pwd = "";
				while (set.next()) {
					pwd = set.getString("password");
				}
				if (pwd.equals(password)) {
					user = new User();
					user.setUsername(username);
					user.setPassword(password);
				} else {
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeResource(pstm, conn, set);
			}
			return user;
		} else {
			return user;
		}
	}

	/**
	 * 获取所有学生信息
	 * 
	 * @return
	 */
	public List<Student> getAllStudentInfo() {
		conn = DBUtil.getConnection();
		String sql = "select * from StudentInfo";
		List<Student> list = new ArrayList<>();
		try {
			pstm = conn.prepareStatement(sql);
			set = pstm.executeQuery();
			while (set.next()) {
				Student student = new Student();
				student.setId(set.getInt("id"));
				student.setStdname(set.getString("stdname"));
				student.setStdword(set.getString("stdword"));
				student.setGender(set.getString("gender"));
				student.setAge(set.getString("age"));
				student.setAddress(set.getString("address"));
				student.setPhonenumber(set.getString("phonenumber"));
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstm, conn, set);
		}
		return list;
	}

	/**
	 * 添加一个学生信息
	 * 
	 * @param stdname
	 * @param stdword
	 * @param gender
	 * @param age
	 * @param address
	 * @param phonenumber
	 */
	public int addStudentInfo(String stdname, String stdword, String gender, String age, String address,
			String phonenumber) {
		int row = 0;

		conn = DBUtil.getConnection();
		String sql = "INSERT INTO StudentInfo(stdname,stdword,gender,age,address,phonenumber) VALUE(?,?,?,?,?,?)";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, stdname);
			pstm.setString(2, stdword);
			pstm.setString(3, gender);
			pstm.setString(4, age);
			pstm.setString(5, address);
			pstm.setString(6, phonenumber);
			row = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstm, conn, set);
		}
		return row;
	}

	/**
	 * 通过id搜索学生信息，返回一个Student类型
	 * 
	 * @param id
	 * @return
	 */
	public Student getStudentInfoById(int id) {
		Student std = null;

		conn = DBUtil.getConnection();
		String sql = "SELECT * FROM StudentInfo WHERE id=?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			set = pstm.executeQuery();
			while (set.next()) {
				std = new Student();
				std.setStdname(set.getString("stdname"));
				std.setStdword(set.getString("stdword"));
				std.setGender(set.getString("gender"));
				std.setAge(set.getString("age"));
				std.setAddress(set.getString("address"));
				std.setPhonenumber(set.getString("phonenumber"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstm, conn, set);
		}
		return std;
	}

	public int updateStudentInfoByStdname(String sname, String stdname, String stdword, String gender, String age,
			String address, String phonenumber) {
		int row = 0;

		conn = DBUtil.getConnection();
		String sql = "UPDATE StudentInfo SET stdname = ?,stdword = ?,gender = ?,age = ?,address = ?,phonenumber = ? WHERE stdname = ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, stdname);
			pstm.setString(2, stdword);
			pstm.setString(3, gender);
			pstm.setString(4, age);
			pstm.setString(5, address);
			pstm.setString(6, phonenumber);
			pstm.setString(7, sname);
			row = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstm, conn, set);
		}
		return row;
	}

	/**
	 * 根据ID删除一条数据
	 * 
	 * @param id
	 * @return
	 */
	public int deleteInfoById(int id) {
		int row = 0;

		conn = DBUtil.getConnection();

		String sql = "delete from studentinfo WHERE id=? ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			row = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstm, conn, set);
		}

		return row;
	}

	public List<Student> fuzzySearch(String searchName, int offset) {
		List<Student> list = new ArrayList<>();
		conn = DBUtil.getConnection();
		String sql = "SELECT * FROM StudentInfo  WHERE stdname LIKE ? or gender LIKE ? or age LIKE ? or address LIKE ? or phonenumber LIKE ? LIMIT 5 OFFSET ?";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + searchName + "%");
			pstm.setString(2, "%" + searchName + "%");
			pstm.setString(3, "%" + searchName + "%");
			pstm.setString(4, "%" + searchName + "%");
			pstm.setString(5, "%" + searchName + "%");
			pstm.setInt(6, offset);
			set = pstm.executeQuery();
			while (set.next()) {
				Student std = new Student();
				std.setId(set.getInt("id"));
				std.setStdname(set.getString("stdname"));
				std.setStdword(set.getString("stdword"));
				std.setGender(set.getString("gender"));
				std.setAge(set.getString("age"));
				std.setAddress(set.getString("address"));
				std.setPhonenumber(set.getString("phonenumber"));
				list.add(std);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 每页查询的信息条数
	 * 
	 * @param index
	 * @param size
	 * @return
	 */
	public List<Student> queryLimit(int index, int size) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet set = null;
		List<Student> list = new ArrayList<Student>();

		String sql = "select * from studentinfo limit ?,?";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, index);
			pstm.setInt(2, size);
			set = pstm.executeQuery();

			while (set.next()) {
				Student student = new Student();
				student.setId(set.getInt("id"));
				student.setStdname(set.getString("stdname"));
				student.setStdword(set.getString("stdword"));
				student.setGender(set.getString("gender"));
				student.setAge(set.getString("age"));
				student.setAddress(set.getString("address"));
				student.setPhonenumber(set.getString("phonenumber"));
				list.add(student);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstm, conn, set);
		}

		return list;

	}

	/**
	 * 得到总信息量
	 * 
	 * @return
	 */
	public int getCount() {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet set = null;
		int row = 0;
		String sql = "select count(*) count from studentinfo";

		try {
			pstm = conn.prepareStatement(sql);

			set = pstm.executeQuery();

			while (set.next()) {

				row = set.getInt("count");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeResource(pstm, conn, set);
		}

		return row;
	}
	
	public int getCount(String searchName) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet set = null;
		int row = 0;
		String sql = "select count(*) count from studentinfo WHERE stdname LIKE ? or gender LIKE ? or age LIKE ? or address LIKE ? or phonenumber LIKE ?";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%"+searchName+"%");
			pstm.setString(2, "%"+searchName+"%");
			pstm.setString(3, "%"+searchName+"%");
			pstm.setString(4, "%"+searchName+"%");
			pstm.setString(5, "%"+searchName+"%");
			set = pstm.executeQuery();

			while (set.next()) {

				row = set.getInt("count");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeResource(pstm, conn, set);
		}

		return row;
	}

	public List<Student> queryLimit(int index, String searchName) {
		List<Student> list = new ArrayList<>();
		index = (index - 1) * 5;
		conn = DBUtil.getConnection();
		String sql = "SELECT * FROM StudentInfo  WHERE stdname LIKE ? or gender LIKE ? or age LIKE ? or address LIKE ? or phonenumber LIKE ? LIMIT 5 OFFSET ?";

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "%" + searchName + "%");
			pstm.setString(2, "%" + searchName + "%");
			pstm.setString(3, "%" + searchName + "%");
			pstm.setString(4, "%" + searchName + "%");
			pstm.setString(5, "%" + searchName + "%");
			pstm.setInt(6, index);
			set = pstm.executeQuery();
			while (set.next()) {
				Student student = new Student();
				student.setId(set.getInt("id"));
				student.setStdname(set.getString("stdname"));
				student.setStdword(set.getString("stdword"));
				student.setGender(set.getString("gender"));
				student.setAge(set.getString("age"));
				student.setAddress(set.getString("address"));
				student.setPhonenumber(set.getString("phonenumber"));
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int getSearchCount() {
		conn = DBUtil.getConnection();

		int row = 0;
		String sql = "select count(*) count from studentinfo ";

		try {
			pstm = conn.prepareStatement(sql);

			set = pstm.executeQuery();

			while (set.next()) {

				row = set.getInt("count");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeResource(pstm, conn, set);
		}

		return row;
	}

	// ----------------------------------------

	public void query(String username) {

		conn = DBUtil.getConnection();

		// 3: 获取sql预编译对象
		String sql = "Select * from xzl where username = ? ";
		User user = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			ResultSet set = pstm.executeQuery();
			System.out.println(sql);
			while (set.next()) {
				int id = set.getInt("id");
				String uname = set.getString("username");
				String pwd = set.getString("password");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<User> queryAll() {
		conn = DBUtil.getConnection();
		String sql = "select * from xzl";
		List<User> list = new ArrayList<>();
		try {
			pstm = conn.prepareStatement(sql);
			set = pstm.executeQuery();
			while (set.next()) {
				User user = new User();
				user.setId(set.getInt("id"));
				user.setUsername(set.getString("username"));
				user.setPassword(set.getString("password"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
