package org.example.homework;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GPUD5 {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getAllVersion(){
        given().log().all()
                .when()
                .get("http://localhost:3030/version")
                .then().log().all()
                .statusCode(200);
    }
    @Test
    public void getallhealthcheck(){
        given().log().all()
                .when()
                .get("http://localhost:3030/healthcheck")
                .then().log().all()
                .statusCode(200);
    }

}
