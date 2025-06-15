package propertyUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtility {

    public Properties properties;

    // Facem un constructor care sa incarce fisierul in functie de nume
    public PropertyUtility(String testName){
        loadFile(testName);
    }

    // O metoda care incarca un fisier properties in functie de numele fisierului
    public void loadFile(String testName) {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/inputData/" + testName + "Data.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Facem o metoda care sa scoata valoarea unei proprietati pe baza de cheie
    public String getPropertyValue(String key){
           return properties.getProperty(key);
    }

    // O metoda care scoate toate cheile si valorile intr-un hashmap
    public Map<String, Object> getAllProperties(){
        Map<String, Object> allProperties = new HashMap<>();
        for (String key: properties.stringPropertyNames()){
            String value = getPropertyValue(key);
            allProperties.put(key, value);
        }
        return allProperties;
    }

}
