package com.techSoft.commonPlatformUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.*;

public class CommonRestClient {
    private final String baseUrl;
    private final Map<String, Object> headers = new HashMap<>();
    private final Map<String, Object> cookies = new HashMap<>();
    private final Map<String, Object> queryParams = new HashMap<>();
    private final Map<String, Object> pathParams = new HashMap<>();


    private RequestSpecification buildRequestSpecification() {
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .headers(headers)
                .cookies(cookies)
                .pathParams(pathParams)
                .queryParams(queryParams);
    }

    // HTTP Methods
    public Response sendGetRequest(String endpoint) {
        return buildRequestSpecification().get(endpoint);
    }

    public Response sendPostRequest(String endpoint, Object body) {
        return buildRequestSpecification().contentType(ContentType.JSON).body(body).post(endpoint);
    }

    public Response sendPatchRequest(String endpoint, Object body) {
        return buildRequestSpecification().contentType(ContentType.JSON).body(body).patch(endpoint);
    }

    public Response sendDeleteRequest(String endpoint) {
        return buildRequestSpecification().delete(endpoint);
    }

    public Response sendPutRequest(String endpoint, Object body) {
        return buildRequestSpecification().contentType(ContentType.JSON).body(body).put(endpoint);
    }

    // Constructor to set the base URL
    public CommonRestClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public CommonRestClient withHeader(String key, Object value) {
        headers.put(key, value);
        return this;
    }

    public CommonRestClient withCookie(String key, Object value) {
        cookies.put(key, value);
        return this;
    }

    public CommonRestClient withQueryParam(String key, Object value) {
        queryParams.put(key, value);
        return this;
    }

    public CommonRestClient withPathParam(String key, Object value) {
        pathParams.put(key, value);
        return this;
    }
}
