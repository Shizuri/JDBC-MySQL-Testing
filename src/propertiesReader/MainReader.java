package propertiesReader;

import java.io.IOException;

public class MainReader {

	public static void main(String[] args) {
		PropertiesReader pr = new PropertiesReader();
		pr.read();

		try {
			pr.doSomeOperation();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
