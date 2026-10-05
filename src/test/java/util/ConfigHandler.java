package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigHandler {
    private Logger log = LogManager.getLogger(ConfigHandler.class);
    Properties properties;
    public ConfigHandler(String filePath){
        properties = new Properties();
        try {
            log.debug("Trying to open file: {}",filePath);
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
        }catch (Exception e){
            log.error("Couldn't open config property file");
            e.printStackTrace();
        }
    }
    public String getValue(String key){
        log.debug("Getting value from key: {} from the property file",key);
        return properties.getProperty(key);
    }
}
