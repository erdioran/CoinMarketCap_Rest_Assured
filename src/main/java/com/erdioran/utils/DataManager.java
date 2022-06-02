package com.erdioran.utils;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.FileUtils;

public class DataManager {

    private static Object document;

    private DataManager() {
    }

    public static String getData(String key) {
        if (document == null) {
            String jsonString;
            try {
                jsonString = FileUtils.readFileToString(new File("src/test/resources/data.json"), StandardCharsets.UTF_8);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            document = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);

        }
        return JsonPath.read(document, key);
    }

    public static synchronized String getRandomString() {
        return getRandomString("Auto_");
    }

    public static synchronized String getRandomString(String prefix) {
        return prefix.concat(new SimpleDateFormat("ddMMMHHmmss", Locale.ENGLISH).format(new Date()));
    }


}