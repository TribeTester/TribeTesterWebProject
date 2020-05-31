package utils;

import base.BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	
	static String projectPath=new File("").getAbsolutePath();

    /**
     * Gets the config properties
     * @param sKey
     * @return
     */
    public static String getConfigProperties(String sKey) {
        Properties prop;
        String sValue = null;
        try {
        	
        	
            InputStream input = new FileInputStream(projectPath+BaseTest.CONFIG_FILE_PATH);
            prop = new Properties();
            // load a properties file
            prop.load(input);
            sValue = prop.getProperty(sKey);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sValue;
    }
    
}
