package com.erdioran.base;

import java.nio.file.Paths;

public class Constant {
    public static final String PARENT_DIR = System.getProperty("user.dir");
    public static final String TARGET_DIR = Paths.get(PARENT_DIR, "target").toString();
    public static final String TEST_RESOURCES_DIR = Paths.get(PARENT_DIR, "src", "test", "resources").toString();
    public static final String SCENARIO_UPGRADE_DIR = Paths.get(TARGET_DIR, "upgrades-dir").toString();
    public static String xmlSuiteFileName;

}
