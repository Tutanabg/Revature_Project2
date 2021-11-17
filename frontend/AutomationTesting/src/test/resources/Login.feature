#Tests features in the Login Component
Feature: Login is responsive
  Scenario: the login button works
    Given the user is on about us page
    When the user clicks on the login button
    Then the user is on login page

  Scenario: the sign in button works for valid inputs
    Given the user is on the login page
    When the user enters a valid username and password
    And clicks on the login button
    Then the user sign in, then logout button shows up

  Scenario: the sign in button works for invalid inputs
    Given the user is on the login page
    When the user enters an invalid username or password
    And clicks on the login button
    Then the error message shows up and the invalid Texts are removed

  Scenario: the Forget your Password? works
    Given the user is on the login page
    When the user clicks on the Forget Your Password?
    Then the update account window shows up, the submit button is disable

  Scenario: the Forget your Password? works for valid inputs
    Given the user is on the login page
    When the user clicks on the Forget Your Password?
    And the user enters an valid information
    Then the submit button is enable

  Scenario: the logout button works
    Given the user is on the login page
    When the user enters a valid username and password
    And clicks on the login button
    And the user clicks on the logout button
    Then the user logout, the user is on landing page

  Scenario: the register now button works
    Given the user is on the login page
    When the user clicks on the register now button
    Then the create account window shows up, the register button is disable

  Scenario: the close button works
    Given the user is on the register window
    When the user clicks on the close button
    Then the create account window disappear

  Scenario: the submit button works
    Given the user is on the register window
    When the user enters all valid inputs
    Then the Register button is enabled