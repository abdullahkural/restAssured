package com.cybertek.Day04;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    }


}
