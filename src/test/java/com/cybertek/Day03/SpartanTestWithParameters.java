package com.cybertek.Day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestWithParameters {

    @BeforeAll
    public static void init(){

        //Save baseURI inside this variable so that we do not need to type each http method
        baseURI = "http://54.91.11.180:8000";
    }


//== TASK01 ======================================================================================================================================================================================


    /*
    Task01
    Given accept type is Json
    And ID parameter value is 5
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content-type: application/json
    And "Blythe" should be in response payload
     */

    @DisplayName("GET request to/api/spartans/{id} with ID 5")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 5).when().get(baseURI + "/api/spartans/{id}");
        //and().pathParams("id",5) is giving the path parameter

        assertEquals(200, response.getStatusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Blythe"));
    }


//== TASK02 ======================================================================================================================================================================================


    /*
    Task02
    Given accept type is Json
    And ID parameter value is 500
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 404
    And response content-type: application/json
    And "Not Found" should be in response payload
     */

    @DisplayName("GET request to /api/spartans/{id} Negative Test")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON).pathParams("id",500).when().get(baseURI + "/api/spartans/{id}");

        assertEquals(404, response.getStatusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Not Found"));
    }



//== TASK03 and TASK04 ======================================================================================================================================================================================


    /*
    Task03
    Given accept type is Json
    And query parameter values are:
        gender|Female
        nameConsists|e
    When user sends GET request to /api/spartans/search
    Then response status code should be 200
    And response content-type: application/json
    And "Female" should be in response payload
    And "Janette" should be in response payload
     */

    @Test
    public void test3(){
        Response response = given().log().all().
                                                accept(ContentType.JSON).
                                                                        and().queryParam("gender", "Female").
                                                                                                                        and().queryParam("nameContains", "e").
                                                                                                                                                                         get(baseURI+ "/api/spartans/search");
    //  {{spartanUrl}}/api/spartans/search?nameContains=e&Gender=Female  ---> in the Postman
    //  http://54.91.11.180:8000/api/spartans/search?gender=Female&nameContains=e

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Female") && response.body().asString().contains("e"));
    }

    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test4(){
        //create a map and add query parameters
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        Response response = given().
                                    log().all()
                                               .accept(ContentType.JSON).
                                                                         and().queryParams(queryMap).
                                                                                                     when().get("/api/spartans/search");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Female"));
    }


}
