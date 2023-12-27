package org.example.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GPUD1 {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test //get
    public void getAllproducts() {

        given().log().all()
                .when()
                .get("http://localhost:3030/products")
                .then().log().all()
                .statusCode(200);
    }
    @Test //get
    public void getproductbyId() {

        given().log().all()
                .when()
                .get("http://localhost:3030/products/43900")
                .then().log().all()
                .statusCode(200);
    }

    @Test //create
    public void createPruduct() {

        String jsonData = "{\n" +
                "  \"name\": \"computer\",\n" +
                "  \"type\": \"digital\",\n" +
                "  \"price\": 0,\n" +
                "  \"shipping\": 0,\n" +
                "  \"upc\": \"string\",\n" +
                "  \"description\": \"string\",\n" +
                "  \"manufacturer\": \"string\",\n" +
                "  \"model\": \"string\",\n" +
                "  \"url\": \"string\",\n" +
                "  \"image\": \"string\"\n" +
                "}";

        response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(jsonData)
                .post("http://localhost:3030/products");
        response.then().log().all().statusCode(201)
                .body("name",equalTo("computer"))
                .body("type",equalTo("digital"));

    }

    @Test
    public void updateProduct(){

        String jsondata="{\n" +
                "  \"name\": \"camera\"\n" +
                "}";
        given().log().all()
                .baseUri("http://localhost:3030/products/9999688")
                .contentType(ContentType.JSON)
                .body(jsondata)
                .when()
                .patch()
                .then().log().all()
                .body("name", equalTo("camera"))
                .statusCode(200);

    }
    @Test
    public  void deleteProduct(){
        given()
                .when()
                .delete("http://localhost:3030/products/9999688")
                .then()
                .statusCode(404);
    }
}
