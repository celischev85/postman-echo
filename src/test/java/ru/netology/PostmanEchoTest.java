package ru.netology;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class PostmanEchoTest {

    @Test
    void shouldReturnSentData() {
        given()
                .baseUri("https://postman-echo.com")
                .body("some data")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("some data"));
    }


    @Test
    void shouldReturnDataInUTF8() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body("Привет мир")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("Привет мир"));
    }

    @Test
    void shouldReturnComplexUTF8Data() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body("Тестирование REST API 2024")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("Тестирование REST API 2024"));
    }
    @Disabled("Временно отключён: тест намеренно падает — используется для демонстрации поведения CI")
    @Test
    void shouldFailOnWrongData() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body("Привет мир")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("Wrong text"));
    }
}