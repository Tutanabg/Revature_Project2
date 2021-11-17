#Tests features in the Login Component
Feature: Login is responsive
  Scenario: the login button works
    Given the user is on main menu page
    When the user clicks on the confirm order button
    Then the user is on order items page