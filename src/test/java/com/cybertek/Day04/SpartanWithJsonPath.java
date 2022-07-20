package com.cybertek.Day04;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithJsonPath extends SpartanTestBase {


    @DisplayName("GET one spartan with JsonPath")
    @Test
    public void test1(){

        //Print each name of IT programmers
        Response response = given().accept(ContentType.JSON).and().pathParam("id",30).when().get(baseURI + "/api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json", response.contentType());

        //print name with path method
        System.out.println("response.path(\"name\") = " + response.path("name"));

        //assigning response to jsonpath
        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

    }


}
