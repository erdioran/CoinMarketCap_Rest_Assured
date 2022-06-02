package com.erdioran.projectUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static com.erdioran.projectUtils.Environments.*;

public class PreMethods {



    // This method is not valid for CoinMarketCap services.It is used in services that send request with user information and return token value.
    public static String getToken() {

        String postData = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";

        Response response =
                RestAssured.given().
                        header("Content-Type", "application/json").
                        baseUri(url).
                        body(postData).
                        when().
                        post(authenticate).
                        then().
                        log().ifValidationFails().
                        statusCode(200).
                        extract().response();

        String accessToken = response.path("accessToken").toString();

        System.out.println("accessToken: " + accessToken);

        return accessToken;
    }
}
