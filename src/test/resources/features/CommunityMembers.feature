@sanity
Feature: Verifying Community Members

  Background:
    Given the user is on the login page
    When the user logs in with valid credentials
    Then the user should be redirected to the dashboard

  Scenario Outline: Verify users can filter and sort community members with different sorting options
    When the user navigates to the "<section>" section
    And the user selects "<connectionType>" as the connection type
    And the user clicks on the sort button
    And the user selects "<sortingType>" sorting
    Then the user should see the sorted community members list
    And the list should not be empty

    Examples:
      | section   |  connectionType   |  sortingType    |
      | Connect   |  Community        |  A-Z            |
      | Connect   |  Community        |  Newest members |