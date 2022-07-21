package com.cybertek.Day04;

import org.junit.jupiter.api.BeforeAll;
import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.*;


public class CybertekTrainingWithJsonPath {

    @BeforeAll
    public static void init(){

        baseURI = "http://api.cybertektraining.com";

    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){

        /*send a request to student id 32881 as a path parameter and accept header application/json
        verify status code 200 /content type==application/json /Content-Encoding = gzip
        verify Date header exists
        assert that:
            firstName Vera
            batch 14
            section 12
            emailAddress aaa@gmail.com
            companyName Cybertek
            state IL
            zipCode 60606
            using Jsonpath
             */
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 32881).when().get(baseURI + "/student/{id}");

        assertEquals(200 ,response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("gzip", response.getHeader("Content-Encoding"));
        assertTrue(response.getHeader("Date").contains("Thu"));

        JsonPath jsonPath = response.jsonPath();
        assertEquals("Vera" ,jsonPath.getString("students[0].firstName"));
        assertEquals(14, jsonPath.getInt("students[0].batch"));
        assertEquals(12, jsonPath.getInt("students[0].section"));
        assertEquals("aaa@gmail.com", jsonPath.getString("students[0].contact.emailAddress"));
        assertEquals("Cybertek", jsonPath.getString("students[0].company.companyName"));
        assertEquals("IL", jsonPath.getString("students[0].company.address.state"));
        assertEquals(60606, jsonPath.getInt("students[0].company.address.zipCode"));

    }
}
