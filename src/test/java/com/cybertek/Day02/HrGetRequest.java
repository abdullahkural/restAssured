package com.cybertek.Day02;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequest {

    /*with @BeforeAll method in each test we do not have to prive like:
    Response response = RestAssured.given().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans/3");
    with assigned baseURI we will only provide the end points in the tests*/

    //BeforeAll() is an annotation equals to @BeforeClass in testNG, we use with static method name
    @BeforeAll
    public static void init(){
        baseURI = "http://52.207.61.129:1000/ords/hr";
    }

    @DisplayName("GET request to /regions")
    @Test
    public void test1(){
        Response response = RestAssured.get("/regions");

        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());

    }


    //Test2:
    /*
        Given accept type is application/json
        When user sends get request to /request/2
        Then response status code must be 200
        And content type equals to application/json
        And response body contains Americas
         */

    @DisplayName("GET request to /regions/2")
    @Test
    public void test2(){
        //Response response = RestAssured.get("/regions/2");
        //Response response = RestAssured.given().accept(ContentType).when().get("/regions/2");
        //We imported RestAssured so we can write like:
        Response response = get("/regions/2");


        assertEquals("application/json", response.contentType());
        assertEquals(200, response.statusCode());
        assertTrue(response.body().asString().contains("Americas"));

    }
}
