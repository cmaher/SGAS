package com.cmaher.game.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.badlogic.gdx.utils.Logger;

//TODO: implement all necessary properties in all necessary files for SGAS

/** 
 * @author Christian Maher
 * 
 */
public class GameProperties {
    public static final String SEP = ".";

    private Logger logger = new Logger("GameProperties");
    
    public GameProperties(String propertiesFile) {
        Properties properties = new Properties();
        // read properties file
        try {
            String propertyFile = propertiesFile;
            properties.load(new FileInputStream(new File(propertyFile)));
            System.getProperties().putAll(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getString(String propertyRoot, String property, String defaultValue) {  
        String propName = propertyRoot + SEP + property;
        String prop = System.getProperty(propName, defaultValue);
        
        if((prop == null || prop.isEmpty()) && (defaultValue == null || defaultValue.isEmpty())) {
            logger.log("Error: Property " + propName + " not found, and no default value provided.  Terminating.");
            System.exit(1);
        }  
        
        return prop;
    }
    
    public int getInt(String propertyRoot, String property, int defaultValue) {
        String value = getString(propertyRoot, property, Integer.toString(defaultValue));
        return Integer.parseInt(value);
    }
    
    public float getFloat(String propertyRoot, String property, float defaultValue) {
        String value = getString(propertyRoot, property, Float.toString(defaultValue));
        return Float.parseFloat(value);
    }
}
