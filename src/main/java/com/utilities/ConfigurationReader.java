package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {


    static Properties properties;

    static {

        String path = "configuration.properties";
        try {

            FileInputStream fis = new FileInputStream(path);
            properties = new Properties();
            properties.load(fis);


        } catch (IOException e) {
            System.out.println("properties file was not read!");

        }

    }

    public static String getProperty(String key) {
        var data = properties.getProperty(key);
        return data;

    }


}



