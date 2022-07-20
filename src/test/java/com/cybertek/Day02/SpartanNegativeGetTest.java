package com.cybertek.Day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    /*
    BeforeAll() is an annotation equals to @BeforeClass in testNG, we use with static method name
    */
    @BeforeAll
    public static void init(){

        baseURI = "http://54.91.11.180:8000";

    }

    /*TASK
    Given Accept type application/xml
    When user send GET request to api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
     */





    @DisplayName("Get request to /api/spartans/10")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.XML).when().get("/api/spartans/10");
        assertEquals(406, response.statusCode());
        assertEquals("application/xml;charset=UTF-8", response.contentType());
    }
}
