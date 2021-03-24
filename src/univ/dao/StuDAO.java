package univ.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import univ.vo.StuData;
import dbconnection.DBConn;

public class StuDAO {

	private DBConn dbconn = new DBConn();
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	private String tblName;

	public StuDAO(String tblName) {
		this.tblName = tblName;
	}

	public ArrayList<StuData> selectAll() throws Exception {
		String sql = "select * from " + tblName;
		return doSelectAll(sql);
	}

	public ArrayList<StuData> select(String condition, String text)
			throws Exception {
		String sql = "select * from " + tblName + " where " + condition
				+ " = ?";
		return doSelect(sql, text);
	}

	public void insert(StuData addStu) throws Exception {
		String sql = "insert into " + tblName
				+ " select ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? from dual";
		doInsert(sql, addStu);
	}

	public void modify(StuData modifyStu) throws Exception {
		String sql = "update "
				+ tblName
				+ " set name = ?, addr = ?, juminnum = ?, cellphone = ?, phone = ?, entyear = ?, highschool = ?, gradyear = ?, depcode = ?, procode = ?"
				+ " where code = ?";
		doModify(sql, modifyStu);
	}

	public void remove(String value) throws Exception {
		String sql = "delete from " + tblName + " where code = ?";
		doRemove(sql, value);
	}

	private int doModify(String sql, StuData modifyStu) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, modifyStu.getStuName());
		pstmt.setString(2, modifyStu.getStuAddr());
		pstmt.setString(3, modifyStu.getStuJuminNum());
		pstmt.setString(4, modifyStu.getStuCellphone());
		pstmt.setString(5, modifyStu.getStuPhone());
		pstmt.setString(6, modifyStu.getStuEntYear());
		pstmt.setString(7, modifyStu.getStuHighSchool());
		pstmt.setString(8, modifyStu.getStuGrad());
		pstmt.setString(9, modifyStu.getStuDepCode());
		pstmt.setString(10, modifyStu.getStuProCode());
		pstmt.setString(11, modifyStu.getStuCode());
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

	private int doInsert(String sql, StuData addStu) throws Exception {
		int isSuccess = 0;
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, addStu.getStuCode());
		pstmt.setString(2, addStu.getStuName());
		pstmt.setString(3, addStu.getStuAddr());
		pstmt.setString(4, addStu.getStuJuminNum());
		pstmt.setString(5, addStu.getStuCellphone());
		pstmt.setString(6, addStu.getStuPhone());
		pstmt.setString(7, addStu.getStuEntYear());
		pstmt.setString(8, addStu.getStuHighSchool());
		pstmt.setString(9, addStu.getStuGrad());
		pstmt.setString(10, addStu.getStuDepCode());
		pstmt.setString(11, addStu.getStuProCode());
		isSuccess = pstmt.executeUpdate();
		return isSuccess;
	}

	private ArrayList<StuData> doSelectAll(String sql) throws Exception {
		ArrayList<StuData> Stu = new ArrayList<StuData>();
		pstmt = dbconn.conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		selectRow(Stu, rs);
		return Stu;
	}

	private ArrayList<StuData> doSelect(String sql, String text)
			throws Exception {
		ArrayList<StuData> Stu = new ArrayList<StuData>();
		pstmt = dbconn.conn.prepareStatement(sql);
		pstmt.setString(1, text);
		rs = pstmt.executeQuery();
		selectRow(Stu, rs);
		return Stu;
	}

	private void selectRow(ArrayList<StuData> Stu, ResultSet rs)
			throws Exception {
		while (rs.next()) {
			String StuCode = rs.getString("code");
			String StuName = rs.getString("name");
			String StuJuminNum[] = rs.getString("juminnum").split("-");
			String StuAddr = rs.getString("addr");
			String StuCellphone = rs.getString("cellphone");
			String StuPhone = rs.getString("phone");
			String StuEntYear = rs.getString("entyear");
			String StuHighSchool = rs.getString("highschool");
			String StuGrad = rs.getString("gradyear");
			String StuDepCode = rs.getString("depcode");
			String StuProCode = rs.getString("procode");
			Stu.add(new StuData(StuCode, StuName, StuJuminNum[0],
					StuJuminNum[1], StuAddr, StuCellphone, StuPhone,
					StuEntYear, StuHighSchool, StuGrad, StuDepCode, StuProCode));
		}
	}
}
