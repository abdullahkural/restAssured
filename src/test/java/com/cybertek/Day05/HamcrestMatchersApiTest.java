package com.cybertek.Day05;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;



import static org.hamcrest.MatcherAssert.assertThat;

public class HamcrestMatchersApiTest extends SpartanTestBase {

    /*
    Task01
    Given accept type is Json
    And path param id is 15
    When user sends GET request to /api/spartans/{id}
    Then response status code should be 200
    And response content-type: application/json
    And json data has following:
        id is 15
        name is "Meta"
        gender is "Female"
        phone is 1938695106
     */

    @DisplayName("One Spartan with Hamcrest and Chaining")
    @Test
    public void test1(){

        given().accept(ContentType.JSON).

                and().pathParam("id", 15).

                when().get(baseURI + "/api/spartans/{id}").

                then().statusCode(200).

                and().contentType("application/json").

                and().body("id", equalTo(15),
                        "name", is("Meta"),
                        "gender", is("Female"),
                        "phone", is(1938695106));

    }


    @DisplayName("CBTraining Teacher request with chaining with and matchers")
    @Test
    public void test2(){

    }































}
