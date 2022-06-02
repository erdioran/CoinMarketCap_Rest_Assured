package com.erdioran.listener;


import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.evam.utils.ExtentManager;
import com.evam.utils.ExtentTestManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import java.util.stream.Collectors;

public class TestListener implements ITestListener {

    private static final Logger LOGGER = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Passed test method : [{}] in class [{}]", result.getMethod().getMethodName(), result.getTestClass().getName());
        ExtentTestManager.getNode().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("Failed test method : [{}] in class [{}]", result.getMethod().getMethodName(),
                result.getTestClass().getName());
        LOGGER.error(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        List<String> dependingTestList = result.getSkipCausedBy().stream().map(ITestNGMethod::getMethodName)
                .collect(Collectors.toList());
        LOGGER.info("Skipped test method : [{}] in class [{}]. Depending on test method {}", result.getMethod().getMethodName(),
                result.getTestClass().getName(), dependingTestList);
        if (result.wasRetried()) {
            LOGGER.debug("Test method skipped and being retried. Removing the node");
            ExtentManager.getExtentReports().removeTest(ExtentTestManager.getNode());
        } else {
            String testMethodNameOrDescription =
                    StringUtils.isNotBlank(result.getMethod().getDescription()) ? result.getMethod().getDescription()
                            : result.getMethod().getMethodName();
            ExtentTest node = ExtentTestManager.getTest().createNode(testMethodNameOrDescription);
            node.skip(String.format("Skipped. Depending test %s failed.", dependingTestList));
           // node.skip(String.format("Exception : %s", ExceptionUtils.getFullStackTrace(result.getThrowable())));
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("Executing test : [{}] from class [{}]", context.getCurrentXmlTest().getName(),
                context.getCurrentXmlTest().getXmlClasses().get(0).getName());
        ExtentTestManager.startTest(context.getName(), "");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getExtentReports().flush();
    }
}
