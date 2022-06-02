package com.erdioran.testCases;

import com.aventstack.extentreports.ExtentTest;
import com.erdioran.base.BaseTest;
import com.erdioran.utils.ExtentTestManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.erdioran.projectUtils.Environments.*;
import static com.erdioran.projectUtils.ResponseMessage.*;

public class Cryptocurrency extends BaseTest {

    public static final Logger LOGGER = LogManager.getLogger(Cryptocurrency.class);


    //If it is necessary to create data with the post method before the test, it is created as follows.
    //The desired value of the created data is returned from the method. (For example: id, name)

    public static String createAirdropData(String name, String code) {
        String postData = "";

        return postData;
    }

    public static String createAirdrop(String name, String code) {

        String postData = createAirdropData(name,code);

        Response response =
                RestAssured.given().
                        spec(requestSpec).
                        body(postData).
                        when().
                        post(cryptocurrency+airdrop).
                        then().
                        body("data.name", Matchers.equalTo(name)).
                        body("data.code", Matchers.equalTo(code)).
                        log().ifValidationFails().
                        statusCode(200).
                        extract().response();

        String id = response.path("data.id").toString();
        System.out.println(airdrop + ": " + id);

        return id;
    }

    public static void deleteAirdrop(String airdopId) {

        RestAssured.given().
                spec(requestSpec).
                when().
                delete(cryptocurrency + "/" + airdopId).
                then().
                log().ifValidationFails().
                statusCode(200).
                extract().response();

    }



    //******************************************************************************************************************
    //******************************************************************************************************************
    //******************************************************************************************************************

    @BeforeMethod()
    public void beforeCryptocurrency(ITestContext context) {
        ExtentTest test = ExtentTestManager.getNode();
        test.assignCategory("Cryptocurrency Tests");

    }

    @Test(description = "List All Airdrops", priority = 1)
    public void getAirdropsAll_200() {


        //If airdrop could be created, it would be created before the test with this method.
        //createAirdrop("Airdrop1", "A1");


        Response response =
                RestAssured.given().
                        spec(requestSpec).
                        when().
                        get(cryptocurrency+airdrops).
                        then().
                        log().ifValidationFails().
                        assertThat().
                        statusCode(200).
                        body("data", Matchers.hasSize(Matchers.greaterThan(0))).
                        body("status.credit_count", Matchers.equalTo(1)).
                        extract().response();

        String reponseBody = response.getBody().asString();
        System.out.println("reponseBody: " + reponseBody);

        //The created data would be deleted here after the test.
        /*
        try {
            deleteAirdrop(airdropId);
        } catch (Exception e) {
            e.printStackTrace();
        }

         */


        ExtentTestManager.getNode().pass("List airdrops 200 pass");
    }

    @Test(description = "List All Airdrops 400", priority = 2)
    public void getAirdropsAll_400() {


        //To get the 400 result, an error is created according to the service.

        Response response =
                RestAssured.given().
                        spec(requestSpec).
                        when().
                        get(cryptocurrency+airdrops).
                        then().
                        log().ifValidationFails().
                        assertThat().
                        statusCode(400).
                        body("error_message",  Matchers.equalTo(error_message_airdrops)).
                        extract().response();

        String reponseBody = response.getBody().asString();
        System.out.println("reponseBody: " + reponseBody);

        ExtentTestManager.getNode().pass("Airdrops 400 pass");
    }

    @Test(description = "List Specific Airdrop 200", priority = 3)
    public void getAirdrop_200() {

        //The airdrop is created as in test 1, or called from a fixed airdrop environment class.


        Response response =
                RestAssured.given().
                        spec(requestSpec).
                        when().
                        get(cryptocurrency+airdrop+"/"+airdropId).
                        then().
                        log().ifValidationFails().
                        assertThat().
                        statusCode(200).
                        body("data.id",  Matchers.equalTo(error_message_airdrops)).
                        body("data.status",  Matchers.equalTo(upcoming)).
                        extract().response();

        String reponseBody = response.getBody().asString();
        System.out.println("reponseBody: " + reponseBody);

        ExtentTestManager.getNode().pass("List specific airdrop 200 pass");


    }
}
