package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	public Connection conn;
	public DBConn(){
		String dbURL = "jdbc:mysql://localhost:3306/friend?serverTimezone=UTC";
		String id = "root";
		String password = "1111";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DB ������");
			conn = DriverManager.getConnection(dbURL, id, password);
			System.out.println("�����ͺ��̽� ���� ����");
		}catch(ClassNotFoundException e){
			System.out.println("JDBC ����̹��� ã�� �� ����");
		}catch(SQLException e){
			System.out.println("�����ͺ��̽� ���� ����");
		}
	}
}
