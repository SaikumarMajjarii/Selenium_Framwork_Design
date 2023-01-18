#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Adding Items to Cart


Background: 
		 Given I need to Land on Website
		 
  @SubmitOrderTests
  Scenario Outline: Add Specific  Items to Cart
  
  		 When I need to Enter <UserName> and <Password>	 
   		 And I Add <ProductName> to Cart 
   		 And Checkout <ProductName> and Submit the Order
  		 Then "THANKYOU FOR THE ORDER."  Success Msg should be Validated

    Examples: 
    
      |  UserName           |   Password          | ProductName     |
      |  siddu@gmail.com    |   Siddukumar@123    | ADIDAS ORIGINAL |
     
      
      
      
      
