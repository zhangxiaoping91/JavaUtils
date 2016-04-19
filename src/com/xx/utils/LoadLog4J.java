package com.xx.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class LoadLog4J {
	static
	{
		String configFile = "src/log4j.xml";     
        PropertyConfigurator.configure(configFile);     
        Properties perties = new Properties();    
        FileInputStream fis;
		try {
			fis = new FileInputStream(configFile);
			perties.load(fis);
		}  catch (IOException e) {
			e.printStackTrace();
		}    
	}
}
