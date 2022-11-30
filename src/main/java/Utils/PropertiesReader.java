package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static final Properties property = new Properties();

    public static final String path = "conf.properties";

    PropertiesReader() {
    }

    ;

    public static Properties initProperties() {
        try {
            FileInputStream fis = new FileInputStream(path);
            property.load(fis);
            return property;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
