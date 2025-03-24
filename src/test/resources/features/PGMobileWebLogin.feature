Feature: Verifying MobileWeb Login Functionality

  Scenario Outline: Verify user login with multiple credentials
    Given I am on the login page
    When  I select the "<loginOption>" login option
    And   I enter email "<email>" and password "<password>"
    And   I click the login button
    Then  I should see the "<expectedResult>"

    Examples:
      | loginOption           | email              | password     | expectedResult      |
      | Continue with Email   | mags@gmail.com     | Mgs@1213     | Dashboard page      |
      | Continue with Email   | user2@example.com  | pass456      | Google sign-in page |

