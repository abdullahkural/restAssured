package com.cybertek.Day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWithPath {

    @BeforeAll
    public static void init() {

        //Save baseURI inside this variable so that we do not need to type each http method
        baseURI = "http://54.91.11.180:8000";
    }

    /*
    Task01
    Given accept type is Json
    And path param id is 30
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content-type: application/json
    And response payload values match the following:
        id is 10
        name is "Melania"
        gender is "Female"
        phone is 8916944276
     */

    @DisplayName("GET one spartan with Path Method")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 30).when().get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        //verify each json key has specific value
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(30, id);
        assertEquals("Melania", name);
        assertEquals("Female", gender);
        assertEquals(8916944276L, phone);


    }
}
