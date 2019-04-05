package maari.mahmoud.sql.date;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DateB {
	private static final String URL;
	private static final String USER;
	private static final String PASS;
	static {
		Properties properties = new Properties();
		try(FileInputStream inputStream = new FileInputStream("db.properties")){
			properties.load(inputStream);
		}catch (Exception e) {
			e.printStackTrace();
		}
		URL = properties.getProperty("url");
		USER = properties.getProperty("user");
		PASS = properties.getProperty("pass");
	}
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}

}
