package com.xiaoniucr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xiaoniucr.entity.Student;
import com.xiaoniucr.utils.JDBCUtils;

/**
 * 学生数据库操作
 *
 */
public class StudentDao {

	/**
	 * 查询学生列表
	 * 
	 * @param username
	 *            账号
	 * @param nickname
	 *            姓名
	 * @return
	 */
	public List<Student> queryList(String username, String nickname) {

		List<Student> list = new ArrayList<Student>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			List<Object> params = new ArrayList<>();
			StringBuffer sb = new StringBuffer("select * from t_student where 1=1 ");
			if (nickname != null && !"".equals(username)) {
				sb.append("and username like ? ");
				params.add("%" + username + "%");
			}
			if (nickname != null && !"".equals(nickname)) {
				sb.append("and nickname like ? ");
				params.add("%" + nickname + "%");
			}
			pstmt = con.prepareStatement(sb.toString());
			if (params != null && params.size() > 0) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("id"));
				student.setUsername(rs.getString("username"));
				student.setNickname(rs.getString("nickname"));
				student.setSex(rs.getInt("sex"));
				student.setBirthday(rs.getString("birthday"));
				student.setTelephone(rs.getString("telephone"));
				student.setEmail(rs.getString("email"));
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(con, pstmt, rs);
		}
		return list;

	}

	/**
	 * 保存学生信息
	 * 
	 * @param user
	 *            学生信息
	 * @return
	 */
	public boolean save(Student student) {

		Connection con = null;
		String sql = "insert into t_student(username,nickname,sex,birthday,telephone,email) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getUsername());
			pstmt.setString(2, student.getNickname());
			pstmt.setInt(3, student.getSex());
			pstmt.setString(4, student.getBirthday());
			pstmt.setString(5, student.getTelephone());
			pstmt.setString(6, student.getEmail());
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(con, pstmt, null);
		}
		return false;

	}

	/**
	 * 修改学生信息
	 * 
	 * @param user
	 *            学生信息
	 * @return
	 */
	public boolean update(Student student) {

		Connection con = null;
		String sql = "update t_student set username=?,nickname=?,sex=?,birthday=?,telephone=?,email=? where id=?";
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getUsername());
			pstmt.setString(2, student.getNickname());
			pstmt.setInt(3, student.getSex());
			pstmt.setString(4, student.getBirthday());
			pstmt.setString(5, student.getTelephone());
			pstmt.setString(6, student.getEmail());
			pstmt.setInt(7, student.getId());
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(con, pstmt, null);
		}
		return false;

	}

	/**
	 * 删除学生信息
	 * 
	 * @param id
	 *            主键ID
	 * @return
	 */
	public boolean delete(int id) {

		Connection con = null;
		String sql = "delete from t_student where id=?";
		PreparedStatement pstmt = null;
		try {
			con = JDBCUtils.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			int rows = pstmt.executeUpdate();
			if (rows > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(con, pstmt, null);
		}
		return false;

	}

	/**
	 * 根据ID查询学生
	 * 
	 * @param id
	 *            主键ID
	 * @return
	 */
	public Student getById(int id) {

		Student student = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "select * from t_student where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setObject(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setUsername(rs.getString("username"));
				student.setNickname(rs.getString("nickname"));
				student.setSex(rs.getInt("sex"));
				student.setBirthday(rs.getString("birthday"));
				student.setTelephone(rs.getString("telephone"));
				student.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(con, pstmt, rs);
		}
		return student;

	}
	
	/**
	 * 根据账号查询学生
	 * 
	 * @param username
	 * @return
	 */
	public Student getByUsername(String username) {

		Student student = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "select * from t_student where username = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setUsername(rs.getString("username"));
				student.setNickname(rs.getString("nickname"));
				student.setSex(rs.getInt("sex"));
				student.setBirthday(rs.getString("birthday"));
				student.setTelephone(rs.getString("telephone"));
				student.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(con, pstmt, rs);
		}
		return student;

	}


}
