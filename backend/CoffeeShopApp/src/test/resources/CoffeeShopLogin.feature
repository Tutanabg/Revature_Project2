#Tests features in the Login Component
Feature: Login is responsive
  Scenario: the login button works
    Given the user is on about us page
    When the user clicks on the login button
    Then the user is on login page

  Scenario: the sign in button works for valid inputs
    Given the user is on the login page
    When the user enters a valid username and password
    And clicks on the sign in button
    Then the user sign in, then logout button and username shows up, the register now button disappear

  Scenario: the sign in button works for invalid inputs
    Given the user is on the login page
    When the user enters an invalid username or password
    Then the error message shows up and the invalid Texts are removed

  Scenario: the logout button works
    Given the user is logged in
    When the user clicks on the logout button
    Then the user logout, the login button shows up

  Scenario: the register now button works
    Given the user is on the login page
    When the user clicks on the register now button
    Then the create account window shows up

  Scenario: the close button works
    Given the user is on the register window
    When the user clicks on the close button
    Then the create account window disappear

  Scenario: the submit button works
    Given the user is on the register window
    When the user clicks on the submit button with all valid inputs
    Then the new account be created and the user sign in.