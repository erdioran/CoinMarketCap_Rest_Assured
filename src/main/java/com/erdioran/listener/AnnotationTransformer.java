package com.erdioran.listener;

import com.erdioran.utils.ConfigManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformer implements IAnnotationTransformer {

    private static final Logger LOGGER = LogManager.getLogger(AnnotationTransformer.class);


    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
        LocalDate expiryDate = LocalDate.parse("25022022", DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH));
        //annotation.setEnabled(LocalDate.now().isBefore(expiryDate));
        if (ConfigManager.getBooleanValue("enable.test.time.out", true)) {
            if (testMethod.getDeclaringClass().getSimpleName().contains("Clean") || testMethod.getDeclaringClass().getSimpleName()
                    .contains("DeployedScenariosTests")) {
                annotation.setTimeOut(9000 * 1000);
            } else {
                // If test still continues beyond 1 hr, it will be marked as timeout failure
                annotation.setTimeOut(3600000); // 3600000 ms = 60 min = 1 hr
            }
        } else {
            LOGGER.debug("Test time out disabled");
        }
    }


}


