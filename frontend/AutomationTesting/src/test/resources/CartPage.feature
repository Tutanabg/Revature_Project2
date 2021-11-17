Feature: The cart page functions as intended

  Scenario: The User tries to submit order without logging in
    Given The User is on the Coffee Shop website
    And The User is not logged in
    And The User's cart is empty
    And The User is on the Cart Page
    Then The Submit Order button will be replaced by a message prompting the User to sign in

  Scenario: The User tries to submit order without adding any items to their cart
    Given The User is on the Coffee Shop website
    And The User's cart is empty
    And The User is logged in
    And The User is on the Cart Page
    Then The Submit Order button will replaced by a message prompting the User to add items to their cart

  Scenario:The User removes an item from the cart, changes the quantity of an item in the cart, selects Delivery and submits order
    Given The User is on the Coffee Shop website
    And The User is logged in
    And The User's cart is not empty
    And The User is on the Cart Page
    When The User clicks the Remove From Cart button
    Then That item is no longer in the cart
    When The User clicks the Increase Quantity button
    Then The Quantity of that item increases by one
    When The User clicks the Decrease Quantity button
    Then The Quantity of that item decreases by one
    When The User selects Delivery
    Then Delivery is selected
    When The User clicks the Submit Order button
    Then The User is redirected to the Landing Page
    And The User's cart has been emptied
