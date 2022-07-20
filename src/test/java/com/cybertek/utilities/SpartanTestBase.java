package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {

        //Save baseURI inside this variable so that we do not need to type each http method
        baseURI = "http://54.91.11.180:8000";
    }
}
