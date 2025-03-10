package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public  class CommonUtilities {
	public static Properties loadProperiesFile() throws IOException {
		Properties pro = new Properties();
		try {
			FileReader fr = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\projectdata.properties");
			pro.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pro;
	}

}
