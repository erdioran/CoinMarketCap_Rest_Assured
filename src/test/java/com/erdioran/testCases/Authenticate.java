package com.erdioran.testCases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.erdioran.projectUtils.Environments.*;
import static com.erdioran.projectUtils.PreMethods.*;

public class Authenticate {

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

    }

}
