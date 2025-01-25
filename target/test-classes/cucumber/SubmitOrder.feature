
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

Background:
Given I landed on the Ecommerce page

  @Regression
  Scenario Outline: Postivite Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout  <productName> and submit the order
    Then "THANKYOU FOR THE ORDER."message is dispalyed on the confirmationPage

    Examples: 
      | name                 |  password | productName |
      | vamsiyaka@gmail.com |  Vamsi@0806 | IPHONE 13 PRO |
      
