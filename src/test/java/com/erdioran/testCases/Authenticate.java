package com.erdioran.testCases;

import com.aventstack.extentreports.ExtentTest;
import com.erdioran.utils.ExtentTestManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.erdioran.projectUtils.Environments.*;
import static com.erdioran.projectUtils.PreMethods.*;

public class Authenticate {

    @BeforeMethod()
    public void beforeAuth(ITestContext context) {
        ExtentTest test = ExtentTestManager.getNode();
        test.assignCategory("Auth Tests");

    }

    // This method is not valid for CoinMarketCap services.It checks if the token returns the value.
    @Test(description = "Successful Login", priority = 1)
    public void successLogin() {

        String postData = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";

        Response response =
                RestAssured.given().
                        header("Content-Type", "application/json").
                        header("Authorization", "Bearer " + getToken()).
                        when().
                        post(authenticate ).
                        then().
                        log().ifValidationFails().
                        statusCode(200).
                        body(accessToken, Matchers.notNullValue()).
                        extract().response();

        String reponseBody = response.getBody().asString();
        System.out.println("reponseBody: " + reponseBody);


        ExtentTestManager.getNode().pass("Login 200 verified");
    }

}
