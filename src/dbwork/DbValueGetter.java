package dbwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class DbValueGetter {

	private String value;
	
	private String id, firstName, lastName;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String retrieve() {

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/test2";
		String user = "user1";
		String password = "test2";
		
		try {
			myConn = DriverManager.getConnection(dbUrl, user, password);
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from user where first_name='Bruce'");
			
			
			
			while(myRs.next()) {
				value = myRs.getString("first_name");
				System.out.println("First name: " + myRs.getString("first_name") + " Last name: " + myRs.getString("last_name") + " ID: " + myRs.getInt("user_id"));
			}
		} catch (Exception e) {
			System.out.println("PROBLEM");
			System.out.println(e.getLocalizedMessage());
		} finally {
		    if (myRs != null) {
		        try {
		        	myRs.close();
		        } catch (SQLException e) { /* ignored */}
		    }
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
		
		return "response-page";
	}
	
	public String findUser() {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/test2";
		String user = "user1";
		String password = "test2";
		
		try {
			myConn = DriverManager.getConnection(dbUrl, user, password);
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from user where user_id="+id);
			
			while(myRs.next()) {
				value = myRs.getString("first_name");
				firstName = myRs.getString("first_name");
				lastName = myRs.getString("last_name");
				System.out.println("First name: " + myRs.getString("first_name") + " Last name: " + myRs.getString("last_name") + " ID: " + myRs.getInt("user_id"));
			}
		} catch (Exception e) {
			System.out.println("PROBLEM");
			System.out.println(e.getLocalizedMessage());
		}
		
		return "find-user";
	}
}
