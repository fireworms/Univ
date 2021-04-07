package univ.svc;

import java.sql.Connection;

import univ.dao.RegistDAO;
import univ.dto.LoginData;
import dbconnection.DBConn;

public class RegistFormService {
	
	public int registUser(LoginData loginData){
		DBConn conn = new DBConn();
		Connection con = conn.conn;
		RegistDAO registDAO = new RegistDAO(con);
		java.util.Date date = new java.util.Date();
		java.sql.Date setDate = new java.sql.Date(date.getTime());
		loginData.setRegDate(setDate);
		int isSuccess = registDAO.registUser(loginData);
		return isSuccess;
	}
	
}
