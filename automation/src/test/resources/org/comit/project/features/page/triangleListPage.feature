@WebTest
Feature: Triangle Project - record page

  As a user
  I want to get a triangle data record.
  So that I can get/edit/delete triangle data in the system

  Scenario: Display of "No data found" message when no records are available
	 Given the user is on the main page titled "Types of Triangles"
	  When the user clicks on the "Triangle Record" link in the navigation bar
	  Then the user should be redirected to the "Triangle Records" list page
     And the user should see a information message "No data found."
	    
	    
