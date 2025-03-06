Feature: Verifying Community Members
  Scenario: Verify users can filter and sort community members
    Given the user is on the login page
    When the user logs in with valid credentials
    Then the user should be redirected to the dashboard
    When the user navigates to the "Connect" section
    And the user selects "Community" as the connection type
    And the user clicks on the sort button
    And the user selects "A-Z" sorting
    Then the user should see the sorted community members list
    And the list should not be empty
