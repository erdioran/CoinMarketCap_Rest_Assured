package com.erdioran.utils;

import com.erdioran.base.Constant;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    private static final Logger LOGGER = LogManager.getLogger(Helper.class);

    private Helper() {
    }

    public static List<String> getAllFiles(String fileExtension, String path) {
        List<String> files = new ArrayList<>();
        String[] fileNames = new File(path).list();
        for (String fileName : fileNames) {
            if (fileName.contains(fileExtension)) {
                files.add(fileName);
            }
        }
        return files;
    }

    public static List<String> getAllFiles(String fileExtension, String path, String fileNameToSearch) {
        List<String> files = new ArrayList<>();
        String[] fileNames = new File(path).list();
        for (String fileName : fileNames) {
            if (fileName.contains(fileExtension) && fileName.contains(fileNameToSearch)) {
                files.add(fileName);
            }
        }
        return files;
    }

    public static void deleteAllFiles(String fileExtension, String path, String fileNameToSearch) {
        String[] fileNames = new File(path).list();
        for (String fileName : fileNames) {
            if (fileName.contains(fileExtension) && fileName.contains(fileNameToSearch)) {
                boolean deleteQuietly = FileUtils.deleteQuietly(Paths.get(path, fileName).toFile());
                if (deleteQuietly) {
                    LOGGER.info("Successfully deleted {}", fileName);
                }
            }
        }
    }

    public static void deleteAllFiles(String fileExtension, String path) {
        String[] fileNames = new File(path).list();
        for (String fileName : fileNames) {
            if (fileName.contains(fileExtension)) {
                boolean deleteQuietly = FileUtils.deleteQuietly(Paths.get(path, fileName).toFile());
                if (deleteQuietly) {
                    LOGGER.info("Successfully deleted {}", fileName);
                }
            }
        }
    }

    public static void sleepInSeconds(int sleepInSeconds) {
        try {
            LOGGER.debug("wait for seconds : " + sleepInSeconds);
            Thread.sleep(sleepInSeconds * 1000L);
        } catch (Exception e) {
            //
        }
    }

    public static void sleepMs(int sleepInMiliSeconds) {
        try {
            LOGGER.debug("wait for seconds : " + sleepInMiliSeconds);
            Thread.sleep(sleepInMiliSeconds);
        } catch (Exception e) {
            //
        }
    }

    public static void log(String message) {
        if (ExtentTestManager.getNode() != null) {
            ExtentTestManager.getNode().info(message);
        }
        LOGGER.info(message);
    }

    public static void deleteExportedFiles() {
        Helper.deleteAllFiles(".json", Constant.TARGET_DIR);
        Helper.deleteAllFiles(".zip", Constant.TARGET_DIR);
        Helper.deleteAllFiles(".txt", Constant.TARGET_DIR);
        FileUtils.deleteQuietly(Paths.get(Constant.SCENARIO_UPGRADE_DIR).toFile());
    }


}
