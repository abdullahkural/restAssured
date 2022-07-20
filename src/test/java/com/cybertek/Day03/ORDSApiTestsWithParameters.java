package com.cybertek.Day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;

public class ORDSApiTestsWithParameters {

    @BeforeAll
    public static void init(){
        //save baseURL inside this variable so that we do not need to type each http method
        baseURI = "http://52.207.61.129:1000/ords/hr";
    }


    //== TASK01 ======================================================================================================================================================================================


    /*
    Task01
    Given accept type is Json
    And parameters: q = {"region_id":2}
    When user sends GET request to /countries
    Then response status code should be 200
    And response content-type: application/json
    And Payload should contain "United States of America"
     */

    @DisplayName("GET request /countries with Query Param")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).
                and().queryParam("q", "{\"region_id\":2}").
                log().all().
                when().get(baseURI + "/countries");
        assertEquals(200, response.statusCode());
        //assertEquals("application/json", response.contentType());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("United States of America"));
    }



    //== TASK02 ======================================================================================================================================================================================


    /*
    Task02
    Send a GET request to employees and get only employees who works as a IT_PROG
     */

    @DisplayName("GET request /countries with Query Param")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).
                and().queryParam("q", "{\"job_id\":\"IT_PROG\"}").log().all()
                .when().get(baseURI + "/employees");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));

    }
}
