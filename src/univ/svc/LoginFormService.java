package univ.svc;

import java.sql.Connection;

import univ.dao.LoginDAO;
import univ.dto.LoginData;
import dbconnection.DBConn;

public class LoginFormService {
	
	public LoginData loginCheck(LoginData loginData) throws Exception {
		DBConn conn = new DBConn();
		Connection con = conn.conn;
		LoginDAO loginDAO = new LoginDAO(con);
		LoginData getLoginData = new LoginData();
		String auth = loginDAO.AuthCheck(loginData);
		if(auth.charAt(0) == 's'){
			getLoginData = loginDAO.loginCheck(loginData, "student");
		}else if(auth.charAt(0) == 'p'){
			getLoginData = loginDAO.loginCheck(loginData, "professor");
		}else if(auth.equals("admin")){
			getLoginData = loginData;
			getLoginData.setCode("admin");
			getLoginData.setName("admin");
		}
		return getLoginData;
	}
	
	
}
