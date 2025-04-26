@RestTest
Feature: Triangle System RESTful EndPoint
#Triangle Create/Get/Edit/Delete test

#Create triangle
  Scenario: User successfully creates a new isosceles triangle- API Call
   Given the user creates a triangle with these input data
        | Field          |  Value        |
    		| inputType      | sides         |
    		| a              | 7             |
    		| b              | 10            |
    		| c              | 10            | 
    		| triangleType   | Isosceles     | 
    When the user sends a POST request to "/api/triangle/save"
    Then the response status should be 201
    
#Get triangle record
 Scenario: The newly created triangle appears in the record list - API Call
   Given the request is prepared
    When the user sends a GET request to "/api/triangle/record"
    Then the response status should be 200
     And the response should contain the previously created triangle

#Edit triangle
 Scenario: Successfully updates triangle data by id - API Call
   Given the user prepares updated triangle data by setting "a" to 10 and classifying triangleType
    When the user updates the triangle via a PUT request "/api/triangle/update/{id}"
    Then the response status should be 200
     And JSON response should contain updated data
    		| inputType      | sides         |
    		| a              | 10            |
    		| b              | 10            |
    		| c              | 10            | 
    		| triangleType   | Equilateral   | 
