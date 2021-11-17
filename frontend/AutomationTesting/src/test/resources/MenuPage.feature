Feature: Adding items to cart works

  Scenario: The User adds an item to their cart
    Given The User is on the MenuPage
    When The User clicks the show-extra-items button for some menu item
    And The User changes the quantity of any extra item
    And The User clicks the add-item-to-cart button for the menu item
    And The User clicks the cart button
    Then The selected menu item with the chosen extra items should be visible in the cart
