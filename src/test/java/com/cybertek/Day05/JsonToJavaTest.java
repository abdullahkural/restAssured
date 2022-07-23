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
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsInRelativeOrder;

public class JsonToJavaTest extends SpartanTestBase {

    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParam("id", 15)
                .when().get(baseURI + "/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json and convert it to the map

        Map <String, Object> jsonMap = response.as(Map.class);

        System.out.println("jsonMap.toString() = " + jsonMap.toString());

        String actualName = (String) jsonMap.get("name");
        assertThat(actualName, is(equalTo("Meta")));


    }

    @DisplayName("Get all Spartans to Java")
    @Test
    public void getAllSpartans(){

    }





}
