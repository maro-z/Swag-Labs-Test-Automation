package util;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.json.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JSONFileManager {
    private Logger log = LogManager.getLogger(JSONFileManager.class);
    public LinkedHashMap<String,Object> data;
    public JSONFileManager(String filepath){
        try {
            log.debug("trying to get data from json file: {}",filepath);
            Type type = new TypeToken<LinkedHashMap<String,Object>>(){}.getType();
            data = new Gson().fromJson(new FileReader(filepath),type);
        }catch (Exception e){
           log.error("couldn't open json file because error: {}",e);
        }
    }
    public Object getValue(String key){
        log.debug("getting value for key: {}",key);
        return data.get(key);
    }
    public ArrayList<Object> getArray(String key){
        log.debug("getting the array in key: {}",key);
        Object value = null;
        if (data != null && data.containsKey(key)) {
            value = data.get(key);
        }
        return (ArrayList<Object>) value;
    }
}
