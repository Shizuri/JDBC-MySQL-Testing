package dbwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class DeleteUserController {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String delete() {
		
		Connection myConn = null;
		Statement myStmt = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/test2";
		String user = "user1";
		String password = "test2";
		
		try {
			myConn = DriverManager.getConnection(dbUrl, user, password);
			myStmt = myConn.createStatement();
			
			//above this is boilerplate text that is repeated every time I work with JDBC.
			String sql = "DELETE FROM user WHERE user_id='" + id + "'";
			myStmt.executeUpdate(sql);
			//below this is boilerplate text that is repeated every time I work with JDBC.
			
		} catch (Exception e) {
			System.out.println("PROBLEM");
			System.out.println(e.getLocalizedMessage());
		} finally {
		    if (myStmt != null) {
		        try {
		        	myStmt.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		    if (myConn != null) {
		        try {
		        	myConn.close();
		        } catch (SQLException e) { /* ignored */}
		    }
		}
		
		return "index";
	}
}
