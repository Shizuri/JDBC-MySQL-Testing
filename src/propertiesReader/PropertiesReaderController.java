package propertiesReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class PropertiesReaderController {

	private String firstName, lastName, profession;
	
	public PropertiesReaderController() {
		read();
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

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	private void read() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "me.properties";
			input = PropertiesReader.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			// load a properties file from class path
			prop.load(getClass().getClassLoader().getResourceAsStream("me.properties"));
			//prop.load(input); it would have been this if it was a STATIC method	

			this.firstName = prop.getProperty("firstName");
			this.lastName = prop.getProperty("lastName");
			this.profession = prop.getProperty("profession");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
