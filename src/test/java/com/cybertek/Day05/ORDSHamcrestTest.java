package com.cybertek.Day05;

import com.cybertek.utilities.HRTestBase;
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

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName("GET Request to Employees IT_PROG endpoint and Chaining")
    @Test
    public void employeesTest(){

        //send a get request to employees endpoint with query parameter job_id IT_PROG
        //verify each job_id is  IT_PROG
        //verify first names are "Alexander", "Bruce" (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order, just make sure it has same emails)

        List <String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

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
                .body("items.first_name", containsInAnyOrder("Bruce", "Alexander", "David", "Valli", "Diana"))
                .body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"))

                .body("items.email", hasItems("AHUNOLD","BERNST"))
                .body("items.email", containsInAnyOrder("BERNST", "AHUNOLD", "VPATABAL", "DAUSTIN", "DLORENTZ"))
                .body("items.email", containsInRelativeOrder("AHUNOLD", "BERNST", "DAUSTIN", "VPATABAL", "DLORENTZ"))
                .body("items.first_name", equalTo(names));

                //containsInAnyOrder isimlerine bakarken o isimlerin sirasi ile ilgilenmiyor
                //containsInRelativeOrder "Alexander", "Bruce", "David", "Valli", "Diana" isimlerinin isim listesi icinde bu sirada olup olmadigina bakiyor

    }


    @Test
    public void employeesTest2(){
        //we want to chain and also get response object

        //extract() is a method that allows us to get response object after we use then() method
        Response response = given()
                .accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get(baseURI + "/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG"))).extract().response();

        //.extract().response() --> It gives you to return response or jsonPath or what you want to return
        // .extract() ile assertion yaparken ayni zamanda response alabiliriz

        response.prettyPrint();

        //with extract().response().statusCode() at the end we can get the status code and assign to a integer value
        int statusCode = given()
                .accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get(baseURI + "/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG"))).extract().response().statusCode();

        //assertThat we have only 5 first names
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();

        assertThat(jsonPath.getList("items.first_name"), hasSize(5));

        //assert firstnames order
        assertThat(jsonPath.getList("items.first_name"), containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"));

    }
}
