package com.techSoft.testRunners;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class CodeCheck {
    public static void main(String[] args) {
        // Start the WireMock server on port 8080
        WireMockServer wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        // Create a stub for the mock API response
        wireMockServer.stubFor(get(urlEqualTo("/api/user/101"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"id\": 101, \"name\": \"Milind\" }")));

        // Test the mock API using RestAssured
        RestAssured.given().log().all()
                .baseUri("http://localhost:8080")
                .when()
                .get("/api/user/101")
                .then().log().all()
                .statusCode(201)
                .body("name", equalTo("Milind"));

        // Stop the WireMock server after test
        wireMockServer.stop();
    }

    public void testFunction(){
        WireMockServer wiremock = new WireMockServer(8000);
        wiremock.start();
        wiremock.stubFor(get(urlEqualTo("/user/2123")).willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"id\": 2123, \"name\": \"John Doe\" }"
        )));

        // Test the mock API using RestAssured
        RestAssured.given().log().all()
                .baseUri("http://localhost:8080")
                .when()
                .get("/api/user/101")
                .then().log().all()
                .statusCode(200)
                .body("name", equalTo("Milind"));

        // Stop the WireMock server after test
        wiremock.stop();
    }
}
