#Tests features in the navbar
Feature: Navbar is responsive
  Scenario: the menu button works
    Given the user is on about us page
    When the user clicks on menu button
    Then the user should be on menu page

  Scenario: the about us button works
    Given the user is on the menu
    When the user clicks on the about us button
    Then the user should be on the about us page
  Scenario: the contact us button works
    Given the user is on the menu
    When the user clicks on the contact us button
    Then the user should be on the contact us button
  Scenario: the login button works
    Given the user is not logged in
    When the user clicks the login button
    Then the user is on the login page
  Scenario: the cart button works
    Given the user is on the menu page
    When the user clicks on the cart button
    Then the user is on the cart page