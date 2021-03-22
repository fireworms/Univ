package dao.professor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DBest.DBConn;

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

	public void insert(String proCode, String proName, String proAddr,
			String proJuminNum_1, String proJuminNum_2, String proCellphone,
			String proPhone, String proHireYear, String proDegree,
			String proMajor, String proLab) throws Exception {
		String sql = "insert into " + tblName
				+ " select ?, ?, ?, ?, ?, ?, ?, ?, ?, ? from dual";
		ProData tempPro = new ProData(proCode, proName, proJuminNum_1,
				proJuminNum_2, proAddr, proCellphone, proPhone, proHireYear,
				proDegree, proMajor, proLab);
		doInsert(sql, tempPro);
	}

	public void modify(String proName, String proAddr, String proJuminNum_1,
			String proJuminNum_2, String proCellphone, String proPhone,
			String proHireYear, String proDegree, String proMajor,
			String proLab, String oldCode) throws Exception {
		String sql = "update "
				+ tblName
				+ " set name = ?, addr = ?, juminnum = ?, cellphone = ?, phone = ?, hireyear = ?, degree = ?, department = ?, labnum = ?"
				+ " where code = ?";
		ProData tempPro = new ProData(oldCode, proName, proJuminNum_1,
				proJuminNum_2, proAddr, proCellphone, proPhone, proHireYear,
				proDegree, proMajor, proLab);
		doModify(sql, tempPro);
	}

	public void remove(String value) throws Exception {
		String sql = "delete from " + tblName + " where code = ?";
		doRemove(sql, value);
	}

	private void doModify(String sql, ProData tempPro) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, tempPro.getProName());
		pstmt.setString(2, tempPro.getProAddr());
		pstmt.setString(3, tempPro.getProJuminNum());
		pstmt.setString(4, tempPro.getProCellphone());
		pstmt.setString(5, tempPro.getProPhone());
		pstmt.setString(6, tempPro.getProHireYear());
		pstmt.setString(7, tempPro.getProDegree());
		pstmt.setString(8, tempPro.getProMajor());
		pstmt.setString(9, tempPro.getProLab());
		pstmt.setString(10, tempPro.getProCode());
		isSuccess = pstmt.executeUpdate();
	}

	private void doRemove(String sql, String value) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, value);
		isSuccess = pstmt.executeUpdate();
	}

	private void doInsert(String sql, ProData tempPro) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, tempPro.getProCode());
		pstmt.setString(2, tempPro.getProName());
		pstmt.setString(3, tempPro.getProAddr());
		pstmt.setString(4, tempPro.getProJuminNum());
		pstmt.setString(5, tempPro.getProCellphone());
		pstmt.setString(6, tempPro.getProPhone());
		pstmt.setString(7, tempPro.getProHireYear());
		pstmt.setString(8, tempPro.getProDegree());
		pstmt.setString(9, tempPro.getProMajor());
		pstmt.setString(10, tempPro.getProLab());
		isSuccess = pstmt.executeUpdate();
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
