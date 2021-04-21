package univ.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import univ.dto.CourseData;
import univ.dto.EnrolmentData;
import dbconnection.DBConn;

public class EnrolmentDAO {

	private DBConn dbconn = new DBConn();
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	private String tblName;

	public EnrolmentDAO(String tblName) {
		this.tblName = tblName;
	}

	public ArrayList<CourseData> selectAll() throws Exception {
		String sql = "select"
				+ " A.code, A.subject, A.openyear, A.department, A.opengrade, A.semester, A.hours, B.name, A.score"
				+ " from " + tblName + " A inner join professor B"
				+ " on A.pcode = B.code";
		return doSelectAll(sql);
	}
	
	public ArrayList<CourseData> enrolSelectAll(String userId) throws Exception {
		String sql = "select"
				+ " A.code, A.subject, A.openyear, A.department, A.opengrade, A.semester, A.hours, B.name, A.score"
				+ " from " + tblName + " A inner join professor B"
				+ " on A.pcode = B.code"
				+ " inner join enrolment C"
				+ " on A.code = C.ccode where scode = ?";
		return doEnrolSelectAll(sql, userId);
	}

	public ArrayList<CourseData> select(String condition, String text)
			throws Exception {
		String sql = "select"
				+ " A.code, A.subject, A.openyear, A.department, A.opengrade, A.semester, A.hours, B.name, A.score"
				+ " from " + tblName + " A inner join professor B"
				+ " on A.pcode = B.code"
				+ " where " + condition
				+ " = ?";
		return doSelect(sql, text);
	}
	
	public int confirm(EnrolmentData enrolData) throws SQLException{
		delete(enrolData);
		int isSuccess = insert(enrolData);
		return isSuccess;
	}
	
	private void delete(EnrolmentData enrolData) throws SQLException{
		String sql = "delete from enrolment"
				+ " where scode = ?";
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, enrolData.getScode());
		pstmt.executeUpdate();
	}
	
	private int insert(EnrolmentData enrolData) throws SQLException{
		String name = enrolData.getScode();
		ArrayList<String> ccodes = enrolData.getCcodes();
		int isSuccess = 0;
		for(int i = 0; i < ccodes.size(); i++){
			String sql = "insert into enrolment select ?, ? ";
			pstmt = dbconn.conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, ccodes.get(i));
			isSuccess += pstmt.executeUpdate();
		}
		return isSuccess;
	}
	
	private ArrayList<CourseData> doSelectAll(String sql) throws Exception {
		ArrayList<CourseData> Course = new ArrayList<CourseData>();
		pstmt = dbconn.conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		selectRow(Course, rs);
		return Course;
	}
	
	private ArrayList<CourseData> doEnrolSelectAll(String sql, String userId) throws Exception {
		ArrayList<CourseData> Course = new ArrayList<CourseData>();
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		rs = pstmt.executeQuery();
		selectRow(Course, rs);
		return Course;
	}

	private ArrayList<CourseData> doSelect(String sql, String text)
			throws Exception {
		String[] rsData = { "code", "subject", "openyear", "department", "opengrade", "semester", "hours", "pcode", "score"};
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
			String[] rsData = { "A.code", "A.subject", "A.openyear", "A.department", "A.opengrade", "A.semester", "A.hours", "B.name", "A.score"};
			String[] courseData = new String[9]; 
			for(int i = 0; i < rsData.length; i++){
				courseData[i] = rs.getString(rsData[i]);
			}
			Course.add(new CourseData(courseData));
		}
	}
}