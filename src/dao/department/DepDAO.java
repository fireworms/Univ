package dao.department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DBConn;

public class DepDAO {

	private DBConn dbconn = new DBConn();
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	private String tblName;
	
	public DepDAO(String tblName){
		this.tblName = tblName;
	}

	public ArrayList<DepData> selectAll() throws Exception {
		String sql = "select * from " + tblName;
		return doSelectAll(sql);
	}

	public ArrayList<DepData> select(String condition, String text)
			throws Exception {
		condition = conditions(condition);
		String sql = "select * from " + tblName + " where " + condition + " = ?";
		return doSelect(sql, text);
	}

	public void insert(String condition, String department, String major)
			throws Exception {
		String sql = "insert into " + tblName + " select ?, ?, ? from dual";
		doInsert(sql, condition, department, major);
	}

	public void modify(String department, String major, String oldCode)
			throws Exception {
		String sql = "update " + tblName + " set department = ?, major = ?"
				+ " where code = ?";
		doModify(sql, department, major, oldCode);
	}

	public void remove(String value) throws Exception {
		String sql = "delete from " + tblName + " where code = ?";
		doRemove(sql, value);
	}

	private String conditions(String condition) {
		switch (condition) {
		case "학과코드":
			condition = "code";
			break;
		case "학과명":
			condition = "department";
			break;
		case "전공명":
			condition = "major";
			break;
		}
		return condition;
	}

	private void doModify(String sql, String department, String major,
			String oldCode) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, department);
		pstmt.setString(2, major);
		pstmt.setString(3, oldCode);
		isSuccess = pstmt.executeUpdate();
	}

	private void doRemove(String sql, String value) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, value);
		isSuccess = pstmt.executeUpdate();
	}

	private void doInsert(String sql, String condition, String department,
			String major) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, condition);
		pstmt.setString(2, department);
		pstmt.setString(3, major);
		isSuccess = pstmt.executeUpdate();
	}

	private ArrayList<DepData> doSelectAll(String sql) throws Exception {
		ArrayList<DepData> dep = new ArrayList<DepData>();
		pstmt = dbconn.conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		try {
			while (rs.next()) {
				String code = rs.getString("code"); 
				String department = rs.getString("department");
				String major = rs.getString("major");
				dep.add(new DepData(code, department, major));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dep;
	}

	private ArrayList<DepData> doSelect(String sql, String text) throws Exception {
		ArrayList<DepData> dep = new ArrayList<DepData>();
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, text);
		rs = pstmt.executeQuery();
		try {
			while (rs.next()) {
				String code = rs.getString("code"); 
				String department = rs.getString("department");
				String major = rs.getString("major");
				dep.add(new DepData(code, department, major));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dep;
	}
}
