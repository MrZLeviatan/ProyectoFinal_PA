package co.edu.uniquindio.utils;

import co.edu.uniquindio.utils.service.IConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Config implements IConfig {

    private Properties prop = new Properties();

    @Override
    public String SMTP_HOST() {
        try {
            prop.load(new FileInputStream("src/main/resources/info.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty("SMTP_HOST");
    }

    @Override
    public String SMTP_PORT() {
        try {
            prop.load(new FileInputStream("src/main/resources/info.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty("SMTP_PORT");
    }

    @Override
    public String SMTP_USER() {
        try {
            prop.load(new FileInputStream("src/main/resources/info.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty("SMTP_USER");
    }

    @Override
    public String SMTP_PASSWORD() {
        try {
            prop.load(new FileInputStream("src/main/resources/info.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty("SMTP_PASSWORD");
    }
}
