Feature: Purchase Order from ecommerce 
Background: 
Given I have landed on Ecommerce Page
Scenario Outline: Positive Test for Submitting the Order
Given Logged in with username <username> and password <password>
When I add product <productname> to cart
And Checkout <productname> and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

Examples:
| productname | username | password |
| ZARA COAT 3 | pratik7@gmail.com | Pratik@123 |