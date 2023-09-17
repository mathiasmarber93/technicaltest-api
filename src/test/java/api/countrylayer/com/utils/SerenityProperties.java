package api.countrylayer.com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SerenityProperties {

    public static String getProperty(String propertyName) {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("serenity.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propertyName);
    }
}
