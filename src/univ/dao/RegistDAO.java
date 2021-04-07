package univ.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import univ.dto.LoginData;

public class RegistDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public RegistDAO(Connection con) {
		this.con = con;
	}

	public int registUser(LoginData loginData) {
		int isSuccess = 0;
		try {
			String sql = "insert into logindata select ?, ?, ?, ? from dual";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginData.getId());
			pstmt.setString(2, loginData.getPassword());
			pstmt.setString(3, loginData.getCode());
			pstmt.setDate(4, loginData.getRegDate());
			isSuccess = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
}
