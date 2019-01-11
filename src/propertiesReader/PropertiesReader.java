package propertiesReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	public void read() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "db.properties";
			input = PropertiesReader.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(getClass().getClassLoader().getResourceAsStream("db.properties"));

			// get the property value and print it out
			System.out.println(prop.getProperty("url"));
			System.out.println(prop.getProperty("user"));
			System.out.println(prop.getProperty("password"));

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
	
	public void doSomeOperation() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("db.properties");
        Properties properties = new Properties();
        System.out.println("InputStream is: " + inputStream);
        properties.load(inputStream);
        String propValue = properties.getProperty("url");
        System.out.println("Property value is: " + propValue);
    }
}
