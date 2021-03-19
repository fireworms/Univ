package DBest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

public class UseDB {

	private DBConn dbconn = new DBConn();
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	private String tblName = "major";

	public void select(DefaultTableModel dtm) throws Exception {
		dtm.setRowCount(0);
		String sql = "select * from " + tblName;
		rs = doSelect(sql);
		try {
			while (rs.next()) {
				String[] row = { rs.getString("code"),
						rs.getString("department"), rs.getString("major") };
				dtm.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void select(DefaultTableModel dtm, String condition, String text)
			throws Exception {
		dtm.setRowCount(0);
		condition = conditions(condition);
		String sql = "select * from " + tblName + " where " + condition
				+ " = ?";

		rs = doSelect(sql, text);
		try {
			while (rs.next()) {
				String[] row = { rs.getString("code"),
						rs.getString("department"), rs.getString("major") };
				dtm.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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
			System.out.println(condition);
			break;
		case "학과명":
			condition = "department";
			System.out.println(condition);
			break;
		case "전공명":
			condition = "major";
			System.out.println(condition);
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

	private ResultSet doSelect(String sql) throws Exception {
		pstmt = dbconn.conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		return rs;
	}

	private ResultSet doSelect(String sql, String text) throws Exception {
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, text);
		rs = pstmt.executeQuery();
		return rs;
	}
}
