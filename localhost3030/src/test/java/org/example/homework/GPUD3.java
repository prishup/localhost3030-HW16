package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GPUD3 {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getAllCatagories(){
given().log().all()
        .when()
        .get("http://localhost:3030/categories")
        .then().log().all()
        .statusCode(200);
    }
    @Test
    public void getcatagorybyid(){
        given().log().all()
                .when()
                .get("http://localhost:3030/categories/abcat0101000")
                .then().log().all()
                .statusCode(200);
    }
    @Test
    public void createNewCatagories(){
        String jsonData = "{\n" +
                "  \"name\": \"string\",\n" +
                "  \"id\": \"abcsuz104\"\n" +
                "}";
        RestAssured.baseURI = "http://localhost:3030/categories";
        //create request specification
        requestSpecification = RestAssured.given();
        // // Setting content type to specify format in which request payload will be sent.
        requestSpecification.contentType(ContentType.JSON);
        //// Adding body as string
        requestSpecification.body(jsonData);
        //// Calling POST method
        response = requestSpecification.post();
        // // Let's print response body.
        System.out.println(response.prettyPrint());
        validatableResponse = response.then();
        // Check status code
        validatableResponse.statusCode(201);
        // Check response body - name attribute
        validatableResponse.body("name", equalTo("string"));
    }
    @Test
    public void patchcatagories(){
        String jsonData = "{\n" +
                "  \"name\": \"kin\",\n" +
                "  \"id\": \"string\"\n" +
                "}";
        given()
                .baseUri("http://localhost:3030/categories/abcsuz104")
                .contentType(ContentType.JSON)
                .body(jsonData)
                .when()
                .patch()
                .then()
                .statusCode(200)
                .body("name",equalTo("kin"));
    }
    @Test
    public void deleteCatagories(){
        given()
                .baseUri("http://localhost:3030/categories/abcsuz104")
                .contentType(ContentType.JSON)
                .when()
                .delete()
                .then()
                .statusCode(200);
    }
}
