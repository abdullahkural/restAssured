package com.cybertek.Day04;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.*;

public class OrdsApiTestWithPath extends HRTestBase {

    @DisplayName("ORDS API Test with path ")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).
                queryParam("q", "{\"region_id\":2}").
                when().get(baseURI + "/countries");

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print has more
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //PRINT FIRST COUNTRY_ID
        String firstCountryID = response.path("items[0].country_id");
        System.out.println("firstCountryID = " + firstCountryID);

        //print 2nd country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //print third href
        String thirdHref = response.path("items[2].links[0].href");
        System.out.println("thirdHref = " + thirdHref);

        //get me all the country names
        List<String> countryNames =  response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all regions IDs are equal to 2
        List <Integer> regionIDS = response.path("items.region_id");

        for (Integer regionID : regionIDS) {
            assertEquals(2, regionID);
        }

    }


    //ORDSApiTestsWithParameters class test2
    @DisplayName("GET request /countries with Query Params")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).
                and().queryParam("q", "{\"job_id\":\"IT_PROG\"}").log().all()
                .when().get(baseURI + "/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG only
        List <String> allJobIDs = response.path("items.job_id");
        for (String jobID : allJobIDs) {
            assertEquals("IT_PROG", jobID);
        }

    }


}
