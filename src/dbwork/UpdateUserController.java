package dbwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class UpdateUserController {

	private String firstName, lastName;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String update() {
		
		Connection myConn = null;
		Statement myStmt = null;
//		ResultSet myRs = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/test2";
		String user = "user1";
		String password = "test2";
		
		try {
			myConn = DriverManager.getConnection(dbUrl, user, password);
			myStmt = myConn.createStatement();
//			myRs = myStmt.executeQuery("select * from user where first_name='Bruce'");
			
//			String sql = "insert into user (first_name, last_name, user_id) values(" + firstName + ", " + lastName + ", NULL)";
			String sqlFirstName = "update user set first_name='" + firstName +"' where user_id=" + id;
			String sqlLastName = "update user set last_name='" + lastName +"' where user_id=" + id;
			System.out.println(sqlFirstName);
			System.out.println(sqlLastName);
			
			myStmt.executeUpdate(sqlFirstName);
			myStmt.executeUpdate(sqlLastName);
			
			System.out.println("update complete");
		} catch (Exception e) {
			System.out.println("PROBLEM");
			System.out.println(e.getLocalizedMessage());
		} finally {
//		    if (myRs != null) {
//		        try {
//		        	myRs.close();
//		        } catch (SQLException e) { /* ignored */}
//		    }
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
