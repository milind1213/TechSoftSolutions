package com.techSoft.stepDefinitions.steps_apis;

import com.techSoft.CommonConstants;
import com.techSoft.commonPlatformUtils.CommonRestClient;
import com.techSoft.restObjects.endPoints.GroceryEndpoints;
import com.techSoft.restObjects.payloads.GroceryPayloads;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.testng.Assert;

import static com.techSoft.utils.FileUtil.getProperty;


public class GroceryApisSteps {

    private GroceryPayloads payloads ;
    private Response response;
    private CommonRestClient apiClient;
    String accessToken;
    String email = CommonConstants.generateRandomEmail(5),
            name = CommonConstants.generateRandomText(5);

    @Before
    public void setup() {
        payloads = new GroceryPayloads();
        apiClient = new CommonRestClient(getProperty(CommonConstants.TECHSOFT, CommonConstants.TECHSOFT_BASEURL));
    }

    @Given("the API base URL is set")
    public void setApiBaseUrl() {
        System.out.println("Base URL is set: "+ getProperty(CommonConstants.TECHSOFT, CommonConstants.TECHSOFT_BASEURL));
    }

    @When("I send a GET request to the {string} endpoint")
    public void sendGetRequest(String endpoint)
    {
        switch (endpoint)
        {
            case "status":
                response = apiClient.sendGetRequest(GroceryEndpoints.STATUS);
                break;
            case "products/{id}":
                response = apiClient.withPathParam("id", 4643)
                        .sendGetRequest(GroceryEndpoints.PRODUCTS_BY_ID);
                break;
            default:
                throw new IllegalArgumentException("Unknown endpoint: " + endpoint);
        }
        System.out.println("GET Response: " + response.asPrettyString());
    }

    @Then("the response status code should be {int}")
    public void validateStatusCode(int expectedStatusCode)
    {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Unexpected status code!");
    }

    @Then("the response should contain {string} as {string}")
    public void validateResponseField(String key, String expectedValue)
    {
        String actualValue = response.jsonPath().getString(key);
        Assert.assertEquals(actualValue, expectedValue,
                "Mismatch in response field: " + key);
    }

    @Given("I have a random user with name and email")
    public void createRandomUser()
    {
        String email = CommonConstants.generateRandomEmail(5);
        String name = CommonConstants.generateRandomText(5);
        payloads.registerClient(name, email);
        System.out.println("Generated User - Name: " + name + ", Email: " + email);
    }

    @When("I send a POST request to the {string} endpoint with user details")
    public void sendPostRequest(String endpoint)
    {
        if (endpoint.equals("api/clients"))
        {
            response = apiClient.sendPostRequest(GroceryEndpoints.API_CLIENTS, payloads.registerClient(name,email));
            System.out.println("POST Response: " + response.asPrettyString());
        } else {
            throw new IllegalArgumentException("Unknown endpoint: " + endpoint);
        }
    }

    @Then("I store the access token from the response")
    public void storeAccessToken()
    {
        accessToken = response.jsonPath().getString("accessToken");
        Assert.assertNotNull(accessToken, "Access token is not generated!");
        System.out.println("Stored Access Token: " + accessToken);
    }

    @Given("I have a product with ID {int}")
    public void productWithId(int productId)
    {
        System.out.println("Fetching Product with ID: " + productId);
    }

    @Then("the product ID in the response should be {int}")
    public void validateProductId(int expectedProductId)
    {
        int actualProductId = response.jsonPath().getInt("id");
        Assert.assertEquals(actualProductId, expectedProductId,
                "Product ID mismatch!");
    }

}

