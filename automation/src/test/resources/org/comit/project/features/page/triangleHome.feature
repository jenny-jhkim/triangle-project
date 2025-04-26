@WebTest
Feature: Triangle Project - main page, classify triangles

  Background:
  	Given the user is on the triangle system
  	And the user is on the triangle page "Types of Triangles"
 
  Scenario: Successful classification of an equilateral triangle with valid side inputs
    Given the input type is set to "Sides"
    When the user enters inputA 60 and inputB 60 and inputC 60
    And the user clicks the "CHECK" button
    Then the user should see the result "Equilateral"
    And check more details "All three sides have the same length."

  Scenario: Successful classification of an isosceles triangle with valid side inputs
    Given the input type is set to "Sides"
    When the user enters inputA 2 and inputB 2 and inputC 3
    And the user clicks the "CHECK" button
    Then the user should see the result "Isosceles"
    And check more details "Two sides are equal."

  Scenario: Successful classification of a scalene triangle with valid side inputs
    Given the input type is set to "Sides"
    When the user enters inputA 5 and inputB 6 and inputC 7
    And the user clicks the "CHECK" button
    Then the user should see the result "Scalene"
    And check more details "All sides are different."

  Scenario: Unsuccessful classification with invalid side inputs
    Given the input type is set to "Sides"
    When the user enters inputA 4 and inputB 8 and inputC 4
    And the user clicks the "CHECK" button
    Then the user should see the result "Not a triangle"
    And check more details "The given values cannot form a triangle."
    
  Scenario: Successful classification of a right triangle with valid angle inputs
    When the user selects "Angles" from the input type dropdown
    And the user enters inputA 60 and inputB 90 and inputC 30
    And the user clicks the "CHECK" button
    Then the user should see the result "Right"
    And check more details "One angle is exactly 90 degrees."
    
  Scenario: Successful classification of an acute triangle with valid angle inputs
    When the user selects "Angles" from the input type dropdown
    And the user enters inputA 50 and inputB 50 and inputC 80
    And the user clicks the "CHECK" button
    Then the user should see the result "Acute"
    And check more details "All angles are less than 90 degrees."
    
  Scenario: Successful classification of an obtuse triangle with valid angle inputs
    When the user selects "Angles" from the input type dropdown
    And the user enters inputA 100 and inputB 50 and inputC 30
    And the user clicks the "CHECK" button
    Then the user should see the result "Obtuse"
    And check more details "One of the angle is greater than 90 degrees."
    
  Scenario: Unsuccessful classification with invalid angle inputs
    When the user selects "Angles" from the input type dropdown
    When the user enters inputA 40 and inputB 90 and inputC 40
    And the user clicks the "CHECK" button
    Then the user should see the result "Not a triangle"
    And check more details "The given values cannot form a triangle."
   
  @failCase 
  Scenario: Input error when side input contains a negative value
    When the user selects "Sides" from the input type dropdown
    When the user enters inputA 4 and inputB -1 and inputC 2
    And the user clicks the "CHECK" button
    Then the user should see the error alert "Input Error : All the length of the side must be greater than zero."
    
  @failCase 
  Scenario: Input error when angle input contains a negative value
    When the user selects "Angles" from the input type dropdown
    When the user enters inputA 180 and inputB -80 and inputC 80
    And the user clicks the "CHECK" button
    Then the user should see the error alert "Input Error : All angles must be greater than zero."