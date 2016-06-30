Feature: As a shopper, when I need to get a gift, then I can purchase

  Scenario: I'm in trouble with my wife, I need to buy an expensive gift

    Given  I am viewing rails items
      And  Items are $15.00 or over
     When  I choose "Ruby on Rails Tote"
      And  I add it to the cart
     Then  "Ruby on Rails Tote" is in my shopping cart
      And  Total amount in the cart is $15.99

