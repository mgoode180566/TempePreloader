/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sml.tempepreloader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author michaelgoode
 */
public class Config {
    
    private static final Logger log = Logger.getLogger( Config.class.getName() );
    
    String inputFolder;
    String outputFolder;
    String calloutLookups;
    String doubleFile;
    
    public void saveConfig() {
        
        Properties prop = new Properties();
	OutputStream output = null;
 
	try {
 
		output = new FileOutputStream("config.properties");
 
		// set the properties value
		prop.setProperty("InputFolder", "C:\\Tempe\\Input");
		prop.setProperty("OutputFolder", "C:\\Tempe\\Output");
                prop.setProperty("CalloutFile", "C:\\Tempe\\Support\\Tempe_Lookups.xslx");
                prop.setProperty("Double", "C:\\Tempe\\Support\\TEMPE_FAM_DOBLE.xls");
 
		// save properties to project root folder
		prop.store(output, null);
 
	} catch (IOException io) {
		log.debug(io.getMessage());
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				log.debug(e.getMessage());
			}
		}
 
	}
        
    }
    
    public void getConfig() {
        
        Properties prop = new Properties();
	InputStream input = null;
 
	try {
 
		input = new FileInputStream("config.properties");
 
		// load a properties file
		prop.load(input);
 
		// get the property value 
		inputFolder = prop.getProperty("InputFolder");
		outputFolder = prop.getProperty("OutputFolder");
                calloutLookups = prop.getProperty("CalloutFile");
                doubleFile = prop.getProperty("Double");

 
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
