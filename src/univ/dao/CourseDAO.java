package univ.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import univ.dto.CourseData;
import dbconnection.DBConn;

public class CourseDAO {

	private DBConn dbconn = new DBConn();
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	private String tblName;

	public CourseDAO(String tblName) {
		this.tblName = tblName;
	}

	public ArrayList<CourseData> selectAll() throws Exception {
		String sql = "select * from " + tblName;
		return doSelectAll(sql);
	}

	public ArrayList<CourseData> select(String condition, String text)
			throws Exception {
		String sql = "select * from " + tblName + " where " + condition
				+ " = ?";
		return doSelect(sql, text);
	}

	public void insert(CourseData addCourse) throws Exception {
		String sql = "insert into " + tblName
				+ " select ?, ?, ?, ?, ?, ?, ?, ?, ? from dual";
		doInsert(sql, addCourse);
	}

	public void modify(CourseData modifyCourse) throws Exception {
		String sql = "update "
				+ tblName
				+ " set subject = ?, openyear = ?, department = ?, opengrade = ?, semester = ?, hours = ?, professor = ?, score = ?"
				+ " where code = ?";
		doModify(sql, modifyCourse);
	}

	public void remove(String value) throws Exception {
		String sql = "delete from " + tblName + " where code = ?";
		doRemove(sql, value);
	}

	private int doModify(String sql, CourseData modifyCourse) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		for(int i = 1; i < modifyCourse.length(); i++){
			pstmt.setString(i, modifyCourse.toArray()[i]);
		}
		pstmt.setString(modifyCourse.length(), modifyCourse.toArray()[0]);
		isSuccess = pstmt.executeUpdate();
		return isSuccess;
	}

	private int doRemove(String sql, String value) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, value);
		isSuccess = pstmt.executeUpdate();
		return isSuccess;
	}

	private int doInsert(String sql, CourseData addCourse) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		for(int i = 0; i < addCourse.length(); i++){
			pstmt.setString(i+1, addCourse.toArray()[i]);
		}
		isSuccess = pstmt.executeUpdate();
		return isSuccess;
	}

	private ArrayList<CourseData> doSelectAll(String sql) throws Exception {
		ArrayList<CourseData> Course = new ArrayList<CourseData>();
		pstmt = dbconn.conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		selectRow(Course, rs);
		return Course;
	}

	private ArrayList<CourseData> doSelect(String sql, String text)
			throws Exception {
		ArrayList<CourseData> Course = new ArrayList<CourseData>();
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, text);
		rs = pstmt.executeQuery();
		selectRow(Course, rs);
		return Course;
	}

	private void selectRow(ArrayList<CourseData> Course, ResultSet rs)
			throws Exception {
		while (rs.next()) {
			String[] rsData = { "code", "subject", "openyear", "department", "opengrade", "semester", "hours", "professor", "score"};
			String[] courseData = new String[9]; 
			for(int i = 0; i < rsData.length; i++){
				courseData[i] = rs.getString(rsData[i]);
			}
			Course.add(new CourseData(courseData));
		}
	}
}
