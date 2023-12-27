package org.example.homework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GPUD2 {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
 @Test
    public void getAllStores(){
     given().log().all()
             .when()
             .get("http://localhost:3030/stores")
             .then().log().all()
             .statusCode(200);
 }
 @Test //getbyid
 public void getById(){
     given().log().all()
             .when()
             .get("http://localhost:3030/stores/10")
             .then().log().all()
             .statusCode(200);
}
@Test
    public void createStore(){
     String jsondata = "{\n" +
             "  \"name\": \"Chocolate\",\n" +
             "  \"type\": \"ferrero\",\n" +
             "  \"address\": \"watford\",\n" +
             "  \"address2\": \"string\",\n" +
             "  \"city\": \"string\",\n" +
             "  \"state\": \"string\",\n" +
             "  \"zip\": \"string\",\n" +
             "  \"lat\": 0,\n" +
             "  \"lng\": 0,\n" +
             "  \"hours\": \"string\",\n" +
             "  \"services\": {}\n" +
             "}";
   validatableResponse = given().log().all()
           .contentType(ContentType.JSON)
           .body(jsondata)
           .post("http://localhost:3030/stores")
           .then().log().all()
           .statusCode(201);

 }
 @Test
    public void patchstore(){
     String jsondata = "{\"type\": \"dairymilk\"\n" +
             "}";
     given().log().all()
             .baseUri("http://localhost:3030/stores/8927")
             .contentType(ContentType.JSON)
             .body(jsondata)
             .when()
             .patch()
             .then().log().all()
             .body("type",equalTo("dairymilk"))
             .statusCode(200);
 }
 @Test
    public void deletestore(){
     given()
             .when()
             .delete("http://localhost:3030/stores/8927")
             .then()
             .statusCode(200);
 }
}
