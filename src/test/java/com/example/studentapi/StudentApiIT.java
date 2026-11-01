package com.example.studentapi;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentApiIT {

    @Value("${local.server.port}")
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void createThenFetchStudent() {
        given().contentType("application/json")
                .body("{\"id\":1,\"name\":\"Asha\",\"cgpa\":8.5}")
                .when().post("/api/students")
                .then().statusCode(201);

        given()
                .when().get("/api/students/1")
                .then().statusCode(200)
                .body("name", equalTo("Asha"));
    }

    @Test
    void getUnknownStudent_returns404() {
        given()
                .when().get("/api/students/999")
                .then().statusCode(404);
    }

    @Test
    void createInvalidStudent_returns400() {
        given().contentType("application/json")
                .body("{\"id\":5,\"name\":\"\",\"cgpa\":8.0}")
                .when().post("/api/students")
                .then().statusCode(400);
    }
}