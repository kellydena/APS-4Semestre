package model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DaoUtilConnection {

	public static Properties readProperties(String fileName){
		Properties p = null;
		try {
	    FileReader reader=new FileReader(fileName);  
	    p = new Properties();  
	    p.load(reader); 
		} catch(IOException e) {
			e.printStackTrace();
		}
		return p;
	}

}
