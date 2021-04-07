package univ.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import univ.dto.ProData;
import dbconnection.DBConn;

public class ProDAO {

	private DBConn dbconn = new DBConn();
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	private String tblName;

	public ProDAO(String tblName) {
		this.tblName = tblName;
	}

	public ArrayList<ProData> selectAll() throws Exception {
		String sql = "select * from " + tblName;
		return doSelectAll(sql);
	}

	public ArrayList<ProData> select(String condition, String text)
			throws Exception {
		String sql = "select * from " + tblName + " where " + condition
				+ " = ?";
		return doSelect(sql, text);
	}

	public void insert(ProData addPro) throws Exception {
		String sql = "insert into " + tblName
				+ " select ?, ?, ?, ?, ?, ?, ?, ?, ?, ? from dual";
		doInsert(sql, addPro);
	}

	public void modify(ProData modifyPro) throws Exception {
		String sql = "update "
				+ tblName
				+ " set name = ?, addr = ?, juminnum = ?, cellphone = ?, phone = ?, hireyear = ?, degree = ?, department = ?, labnum = ?"
				+ " where code = ?";
		doModify(sql, modifyPro);
	}

	public void remove(String value) throws Exception {
		String sql = "delete from " + tblName + " where code = ?";
		doRemove(sql, value);
	}

	private int doModify(String sql, ProData modifyPro) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, modifyPro.getProName());
		pstmt.setString(2, modifyPro.getProAddr());
		pstmt.setString(3, modifyPro.getProJuminNum());
		pstmt.setString(4, modifyPro.getProCellphone());
		pstmt.setString(5, modifyPro.getProPhone());
		pstmt.setString(6, modifyPro.getProHireYear());
		pstmt.setString(7, modifyPro.getProDegree());
		pstmt.setString(8, modifyPro.getProMajor());
		pstmt.setString(9, modifyPro.getProLab());
		pstmt.setString(10, modifyPro.getProCode());
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

	private int doInsert(String sql, ProData addPro) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, addPro.getProCode());
		pstmt.setString(2, addPro.getProName());
		pstmt.setString(3, addPro.getProAddr());
		pstmt.setString(4, addPro.getProJuminNum());
		pstmt.setString(5, addPro.getProCellphone());
		pstmt.setString(6, addPro.getProPhone());
		pstmt.setString(7, addPro.getProHireYear());
		pstmt.setString(8, addPro.getProDegree());
		pstmt.setString(9, addPro.getProMajor());
		pstmt.setString(10, addPro.getProLab());
		isSuccess = pstmt.executeUpdate();
		return isSuccess;
	}

	private ArrayList<ProData> doSelectAll(String sql) throws Exception {
		ArrayList<ProData> pro = new ArrayList<ProData>();
		pstmt = dbconn.conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		selectRow(pro, rs);
		return pro;
	}

	private ArrayList<ProData> doSelect(String sql, String text)
			throws Exception {
		ArrayList<ProData> pro = new ArrayList<ProData>();
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, text);
		rs = pstmt.executeQuery();
		selectRow(pro, rs);
		return pro;
	}

	private void selectRow(ArrayList<ProData> pro, ResultSet rs)
			throws Exception {
		while (rs.next()) {
			String proCode = rs.getString("code");
			String proName = rs.getString("name");
			String proJuminNum[] = rs.getString("juminnum").split("-");
			String proAddr = rs.getString("addr");
			String proCellphone = rs.getString("cellphone");
			String proPhone = rs.getString("phone");
			String proHireYear = rs.getString("hireyear");
			String proDegree = rs.getString("degree");
			String proMajor = rs.getString("department");
			String proLab = rs.getString("labnum");
			pro.add(new ProData(proCode, proName, proJuminNum[0], proJuminNum[1],
					proAddr, proCellphone, proPhone, proHireYear, proDegree,
					proMajor, proLab));
		}
	}
}
