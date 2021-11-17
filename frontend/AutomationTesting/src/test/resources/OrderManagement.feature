Feature: Order page is functional

  Scenario: the manager can click on the order management page
    Given the user is a manager
    When the manager clicks on the order management page
    Then the manager should be on the order management page

  Scenario: the manager can see all the orders for today
    Given the user is a manager
    When the manager is on the order management page
    Then the manager should see the orders from today

  Scenario: the manager can advance an order
    Given the user is a manager on the order page
    When the manager clicks on an order to advance
    Then the order should have status as ready

  Scenario: the manager can look at orders on a specific day
    Given the user is a manager on the order page
    When the manager inputs the day to view orders from
    And the manager clicks the button to see orders
    Then the orders from that day should be displayed