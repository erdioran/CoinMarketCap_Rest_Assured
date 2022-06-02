package com.erdioran.base;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static com.erdioran.projectUtils.Environments.*;
import static com.erdioran.projectUtils.PreMethods.*;

public class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    public static RequestSpecification requestSpec;
    public static RequestSpecification requestSpecPublicApi;

    @BeforeMethod(alwaysRun = true)
    public void startReport(Method method, ITestResult result, ITestContext context) {
        ThreadContext.put("testName", method.getName());

    }

    @AfterMethod(alwaysRun = true)
    public void finishTest(ITestResult result, ITestContext context) {

    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {

    }

    @BeforeTest(alwaysRun = true)
    public void createApiHeaders() {

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Authorization", getToken());
        builder.addHeader("Content-Type", "application/json");
        builder.setBaseUri(url);

        requestSpec = builder.build();
    }

    // This method is not valid for CoinMarketCap services.Gets token value with getToken method and uses it in headar

    /*
    @BeforeTest(alwaysRun = true)
    public void createApiHeaders() {

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Authorization", getToken());
        builder.addHeader("Content-Type", "application/json");
        builder.setBaseUri(url);

        requestSpec = builder.build();
    }
     */



}
