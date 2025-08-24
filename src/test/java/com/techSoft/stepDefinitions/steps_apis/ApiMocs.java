package com.techSoft.stepDefinitions.steps_apis;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiMocs {

    WireMockServer wireMockServer;

    @BeforeClass
    public void setupMockServer() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        // GET
        wireMockServer.stubFor(get(urlEqualTo("/api/user/101"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"id\": 101, \"name\": \"Milind\" }")));

        // POST
        wireMockServer.stubFor(post(urlEqualTo("/api/user"))
                .withRequestBody(equalToJson("{ \"name\": \"Milind\" }"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"message\": \"User created\" }")));

        // DELETE
        wireMockServer.stubFor(delete(urlEqualTo("/api/user/101"))
                .willReturn(aResponse()
                        .withStatus(204))); // No Content
    }

    @Test
    public void testGET() {
        Response res = given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/api/user/101")
                .then().statusCode(200).extract().response();
        System.out.println(res.asPrettyString());
    }


    @Test
    public void testPOST() {
        given()
                .baseUri("http://localhost:8080")
                .header("Content-Type", "application/json")
                .body("{ \"name\": \"Milind\" }")
                .when()
                .post("/api/user")
                .then()
                .statusCode(201)
                .body("message", equalTo("User created"));
    }


    @Test
    public void testDELETE() {
        given()
                .baseUri("http://localhost:8080")
                .when()
                .delete("/api/user/101")
                .then()
                .statusCode(204); // No Content
    }

    @AfterClass
    public void tearDown() {
        wireMockServer.stop();
    }
}