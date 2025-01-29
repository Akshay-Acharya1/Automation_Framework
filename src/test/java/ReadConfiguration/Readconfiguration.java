package ReadConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Readconfiguration {

	Properties prop;

	public Readconfiguration() {

		File src = new File("./src/test/resources/Configuration/Config.properties");

		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream(src);
			try {
				prop.load(fis);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			System.out.println(e.getMessage());
		}
	}

	public String getApllicationURL() {

		String URL = prop.getProperty("appURL");

		return URL;
	}

	public String userEmail() {

		String Username = prop.getProperty("email");

		return Username;
	}

	public String Password() {

		String Passsowrd = prop.getProperty("password");

		return Passsowrd;
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

}