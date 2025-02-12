Feature: Login functionality
  @Sanity
  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter a valid username and password
    And I click the login button
    Then I should be redirected to the homepage
  @Smoke
  Scenario: Successful login with valid credentialss
    Given I am on the login pages
    When I enter a valid username and passwords
    And I click the login buttons
    Then I should be redirected to the homepages
