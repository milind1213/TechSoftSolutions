@sanity
Feature: Grocery API Tests

  Background:
    Given the API base URL is set

  Scenario: Verify application status
    When  I send a GET request to the "status" endpoint
    Then  the response status code should be 200
    And   the response should contain "status" as "UP"

  Scenario: Generate access token
    Given I have a random user with name and email
    When  I send a POST request to the "api/clients" endpoint with user details
    Then  the response status code should be 201
    And   I store the access token from the response

  Scenario: Fetch product by ID
    Given I have a product with ID 4643
    When  I send a GET request to the "products/{id}" endpoint
    Then  the response status code should be 200
    And   the product ID in the response should be 4643
