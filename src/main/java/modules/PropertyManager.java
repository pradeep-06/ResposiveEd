package modules;

import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
	
	Properties prop;
	InputStream input;
	
	public PropertyManager(String filename) {
		 prop = new Properties();
//		 String filepath=System.getProperty("user.dir")+"\\src\\main\\resources\\"+filename;
		 try {
			input= getClass().getClassLoader().getResourceAsStream(filename);
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String key) {

		return prop.getProperty(key);
	}


}
