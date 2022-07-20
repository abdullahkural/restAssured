package com.cybertek.Day03;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestWithParameters {

    @BeforeAll
    public static void init(){

        //Save baseURI inside this variable so that we do not need to type each http method
        baseURI = "http://54.91.11.180:8000";
    }


    /*

     */
}
