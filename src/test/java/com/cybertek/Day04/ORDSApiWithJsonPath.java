package com.cybertek.Day04;

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

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to Countries")
    @Test
    public void test1() {

        Response response = get("/countries");

        //get the 2nd country name with Jsonpath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.get("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        //items.country_id
        List<String> countryIDs = jsonPath.getList("items.country_id");
        for (String countryID : countryIDs) {
            System.out.println("countryID = " + countryID);
        }

        //get all country names where their region id is equal to 2
        List<String> countryNameWithRegionID2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("countryNameWithRegionID2 = " + countryNameWithRegionID2);

    }

    @DisplayName("GET request to /employees with query param")
    @Test
    public void tes2(){
        //we added limit query to get 107 employees
        Response response = given().queryParam("limit", 107).when().get("/employees");

        //get me all email of employees who is working as IT_PROG
        JsonPath jsonPath = response.jsonPath();
        List <String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println("employeeITProgs = " + employeeITProgs);

        //get me 1st name of employees who is making more than 10000
        List <String> employeesNamesMakingMoreThan10000 = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("employeesNamesMakingMoreThan10000 = " + employeesNamesMakingMoreThan10000);

        //get the max salary first_name
        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);
        String kingsName = response.path("items.max {it.salary}.first_name");
        System.out.println("kingsName = " + kingsName);
    }

}
