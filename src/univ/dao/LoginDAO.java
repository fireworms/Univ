package univ.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import univ.dto.LoginData;

public class LoginDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public LoginDAO(Connection con) {
		this.con = con;
	}

	public String AuthCheck(LoginData loginData) throws Exception {
		String auth = null; 
		String sql = "select id, password, code"
				+ " from logindata"
				+ " where id = ? and password = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, loginData.getId());
		pstmt.setString(2, loginData.getPassword());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			auth = rs.getString("code");
		}
		return auth;
	}
	
	public LoginData loginCheck(LoginData loginData, String auth) throws Exception {
		String sql = "select logindata.id, logindata.password, logindata.code, " + auth + ".name"
				+ " from logindata"
				+ " join " + auth + ""
				+ " on logindata.code = " + auth + ".code"
				+ " where logindata.id = ? and logindata.password = ?";
		doQuery(sql, loginData);
		return loginData;
	}

	private void doQuery(String sql, LoginData loginData) throws Exception {
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, loginData.getId());
		pstmt.setString(2, loginData.getPassword());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			loginData.setId(rs.getString(1));
			loginData.setPassword(rs.getString(2));
			loginData.setCode(rs.getString(3));
			loginData.setName(rs.getString(4));
		}
	}
}
