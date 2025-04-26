@WebTest
Feature: Triangle Project - save a triangle data

  As a user
  I want to save a triangle data.
  So that I can save/get triangle data in the system
  
  Scenario: the user successfully saves the data
  Given the user is on the triangle main page "Types of Triangles"
    And the user fills in the form with the following data:
       |  Field          |  Value        |
       |  Input Type     |  Sides        |
       |  A              |  10           |
       |  B              |  10           |
       |  C              |  10           |
    When the user clicks the "CHECK" button
    Then the user should see the triangle type as "Equilateral" in the result
     And the "SAVE" button should be enabled
    When the user clicks the "SAVE" button
    Then the user should see a success message "Saved to database!"
      
  Scenario: the user successfully saves the second dataset
   Given the user is ready to input a new dataset
    When the user fills in the form with the following data:
       |  Field          |  Value        |
       |  Input Type     |  Angles       |
       |  A              |  60           |
       |  B              |  60           |
       |  C              |  60           |
     And the user clicks the "CHECK" button
    Then the user should see the triangle type as "Acute" in the result
     And the "SAVE" button should be enabled
    When the user clicks the "SAVE" button
    Then the user should see a success message "Saved to database!"

  Scenario: the user navigates to the record page to check previously saved data
   Given the user has saved data
    When the user clicks on the "Triangle Record" link in the navigation bar
    Then the user should be redirected to the "Triangle Records" list page
     And the newly saved triangle data should appear in the triangle list