package dbwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import models.User;

@ManagedBean
public class ListUsersController {

	public List<User> getUsersList() {
		
		List<User> result = new ArrayList<User>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/test2";
		String user = "user1";
		String password = "test2";
		
		try {
			myConn = DriverManager.getConnection(dbUrl, user, password);
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from user");
			
			while(myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				int id = myRs.getInt("user_id");
				
				User u = new User(firstName, lastName, id);
				result.add(u);
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
		
		return result;
	}
}
