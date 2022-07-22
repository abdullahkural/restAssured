package com.cybertek.Day05;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsInRelativeOrder;

public class SpartanHamcrestTest extends SpartanTestBase {

    @DisplayName("GET Spartan/search and chaining together")
    @Test
    public void test1(){

        //ALONG WITH THIS STATEMENT, I WANT TO SAVE NAMES INSIDE THE LIST OF STRING
        List <String> names  = given().accept(ContentType.JSON)
                            .and().queryParams("nameContains", "j", "gender", "Male")
                            .when()
                            .get(baseURI + "/api/spartans/search")
                            .then()
                            .statusCode(200)
                            .and()
                            .body("totalElement", is(8))
                            .extract().response().jsonPath().getList("content.name");

        //.extract().response().jsonPath().getList("content.name") --> saves names inside a list
        //

        System.out.println(names);
    }



}
