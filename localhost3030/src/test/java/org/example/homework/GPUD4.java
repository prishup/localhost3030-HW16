package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GPUD4 {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void getAllServices(){
        given().log().all()
                .when()
                .get("http://localhost:3030/services")
                .then().log().all()
                .statusCode(200);
    }
    @Test
    public void getServicebyid(){
        given().log().all()
                .when()
                .get("http://localhost:3030/services/8")
                .then().log().all()
                .statusCode(200);
    }
    @Test
    public void createNewService(){
        String jsonData = "{\n" +
                "  \"name\": \"motor\"\n" +
                "}";
        RestAssured.baseURI = "http://localhost:3030/services";
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
        validatableResponse.body("name", equalTo("motor"));
    }
    @Test
    public void patchservices(){
        String jsonData = "{\n" +
                "  \"name\": \"car\",\n" +
                "  \"type\": \"string\",\n" +
                "  \"address\": \"string\",\n" +
                "  \"address2\": \"string\",\n" +
                "  \"city\": \"string\",\n" +
                "  \"state\": \"string\",\n" +
                "  \"zip\": \"string\",\n" +
                "  \"lat\": 0,\n" +
                "  \"lng\": 0,\n" +
                "  \"hours\": \"string\",\n" +
                "  \"services\": {}\n" +
                "}";
        given()
                .baseUri("http://localhost:3030/stores/23")
                .contentType(ContentType.JSON)
                .body(jsonData)
                .when()
                .patch()
                .then()
                .statusCode(200)
                .body("name",equalTo("car"));
    }
    @Test
    public void deleteCatagories(){
        given()
                .baseUri("http://localhost:3030/stores/23")
                .contentType(ContentType.JSON)
                .when()
                .delete()
                .then()
                .assertThat()
                .statusCode(200);

    }
}
