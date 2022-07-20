package com.cybertek.Day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {

    String baseUrl = "http://54.91.11.180:8000";

    /*Given Accept type application/json
    When user send GET request to api/spartans end point
    Then status code must be 200
    And response Content Type must be application/json
    And response body should include spartan result*/

    //RestAssured is my class

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                    .get(baseUrl + "/api/spartans");

        //statusCode()
        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode()); //200

        //contentType()
        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType()); //response.contentType() = application/json

        //print whole result body
        response.prettyPrint();

        //How to do API testing then? (With Juint5 Assert = Assertions)

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(), "application/json");

        //verify status code is 200
        Assertions.assertEquals(response.statusCode(),200);


    }

    /*Given accept type application/json
    When user sends a GET request to api/spartans/3
    Then status code should be 200
    And response Content Type should be application/json
    And response body should contain Fidole*/

    @DisplayName("GET one spartan / api/spartans/3 and verify")
    @Test
    public void test2(){

        Response response = RestAssured.given().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans/3");

        Assertions.assertEquals(response.statusCode(), 200);
        Assertions.assertEquals(response.contentType(), "application/json");
        //there is a method as asString(), this method return the response as string and so that we can assert if it contains "Fidole"
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }

    /*
    Given no headers provided
    When Users sends GET request to /api/hello
    Then response status code should be 200
    And Content type header should be "text/plain;charset=UTF-8"
    And header should contain date
    And Content-Length should be 17
    And body should be "Hello from Sparta"
     */

    @Test
    public void test3(){
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("text/plain;charset=UTF-8", response.getContentType());

        //we verify that the Header has a "Date"
        //we use response.headers().hasHeaderWithName() method to get any header name
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //How to get an header from response using header key?
        //We use response.header(String headerName) method to get any header value
        //verify Content-Length is 17
        Assertions.assertEquals("17", response.header("Content-Length"));

        Assertions.assertTrue(response.getBody().asString().contains("Hello from Sparta"));
        //Assertions.assertEquals("Hello from Sparta", response.body().asString());

    }



}
