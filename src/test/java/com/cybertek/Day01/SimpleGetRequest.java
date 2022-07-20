package com.cybertek.Day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://54.91.11.180:8000/api/spartans";

    @Test
    public void test1(){

        //send a get request and save response inside the "Response object"
        Response response = RestAssured.get(url);

        //print response status code
        //statusCode()
        System.out.println(response.statusCode());  //200

        //print response body
        response.prettyPrint();

    }
}
