package com.cybertek.Day05;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName("GET Request to Employees IT_PROG endpoint and Chaining")
    @Test
    public void employeesTest(){

        //send a get request to employees endpoint with query parameter job_id IT_PROG
        //verify each job_id is  IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order, just make sure it has same emails)

        given()
                .accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
        .when()
                .get(baseURI + "/employees")
        .then()
                .statusCode(200)
                .contentType("application/json")
                .header("Date", notNullValue())
                .and().assertThat()
                .body("items.job_id", everyItem(is(equalTo("IT_PROG"))))
                .body("items.first_name", hasItems("Alexander", "Bruce"))
                .body("items.email", hasItems("AHUNOLD","BERNST"));






    }
}
