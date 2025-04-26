@RestTest
Feature: Triangle System Classify EndPoint
#Triangle Classification Test

#Invalid input case - negative or zero inputs
  Scenario: Invalid inputs with negative side lengths - API Call
    Given the user has these input data
    		| inputType    | sides      |
    		| a            | 20         |
    		| b            | -3         |
    		| c            | 10         | 
    When the user calls the "/api/triangle/classify" endpoint
    Then the user should get status code 400
     And JSON error message is
        | httpStatus   | BAD_REQUEST                                             |
        | reason       | Input Error                                             |
        | message      | All the length of the side must be greater than zero.   |

  Scenario: Invalid inputs with zero angle value - API Call
    Given the user has these input data
    		| inputType    | angles     |
    		| a            | 100        |
    		| b            | 0          |
    		| c            | 80         | 
    When the user calls the "/api/triangle/classify" endpoint
    Then the user should get status code 400
     And JSON error message is
        | httpStatus   | BAD_REQUEST                                |
        | reason       | Input Error                                |
        | message      | All angles must be greater than zero.      |

#Success case - by sides
  Scenario: Classification succeeds with valid equilateral triangle inputs - API Call
    Given the user has these input data
    		| inputType       | sides      |
    		| a               | 10         |
    		| b               | 10         |
    		| c               | 10         | 
    When the user calls the "/api/triangle/classify" endpoint
    Then the user should get status code 200
     And JSON data received is
        | typeOfTriangle  | Equilateral                             |                             
        | explanation     | All three sides have the same length.   |

  Scenario: Classification succeeds with valid isosceles triangle inputs - API Call
    Given the user has these input data
    		| inputType       | sides     |
    		| a               | 5         |
    		| b               | 8         |
    		| c               | 5         | 
    When the user calls the "/api/triangle/classify" endpoint
    Then the user should get status code 200
     And JSON data received is
        | typeOfTriangle  | Isosceles                               |                             
        | explanation     | Two sides are equal.                    |
        
  Scenario: Classification succeeds with valid scalene triangle inputs - API Call
    Given the user has these input data
    		| inputType       | sides     |
    		| a               | 5         |
    		| b               | 6         |
    		| c               | 7         | 
    When the user calls the "/api/triangle/classify" endpoint
    Then the user should get status code 200
     And JSON data received is
        | typeOfTriangle  | Scalene                                 |                             
        | explanation     | All sides are different.                |
        
#Success case - by angles
  Scenario: Classification succeeds with valid right triangle inputs - API Call
    Given the user has these input data
    		| inputType       | angles     |
    		| a               | 60         |
    		| b               | 90         |
    		| c               | 30         | 
    When the user calls the "/api/triangle/classify" endpoint
    Then the user should get status code 200
     And JSON data received is
        | typeOfTriangle  | Right                                   |                             
        | explanation     | One angle is exactly 90 degrees.        |

  Scenario: Classification succeeds with valid acute triangle inputs - API Call
    Given the user has these input data
    		| inputType       | angles    |
    		| a               | 50        |
    		| b               | 50        |
    		| c               | 80        | 
    When the user calls the "/api/triangle/classify" endpoint
    Then the user should get status code 200
     And JSON data received is
        | typeOfTriangle  | Acute                                   |                             
        | explanation     | All angles are less than 90 degrees.    |
        
  Scenario: Classification succeeds with valid obtuse triangle inputs - API Call
    Given the user has these input data
    		| inputType       | angles    |
    		| a               | 30        |
    		| b               | 50        |
    		| c               | 100       | 
    When the user calls the "/api/triangle/classify" endpoint
    Then the user should get status code 200
     And JSON data received is
        | typeOfTriangle  | Obtuse                                       |                             
        | explanation     | One of the angle is greater than 90 degrees. |
        
#Invalid triangle case - not a triangle
  Scenario: Classification fails due to triangle inequality rule violation (sides) - API Call
    Given the user has these input data
    		| inputType       | sides     |
    		| a               | 1         |
    		| b               | 2         |
    		| c               | 3         | 
    When the user calls the "/api/triangle/classify" endpoint
    Then the user should get status code 200
     And JSON data received is
        | typeOfTriangle  | Not a triangle                                |                             
        | explanation     | The given values cannot form a triangle.The sum of any two sides of a triangle must be greater than the third side. |
          
  Scenario: Classification fails due to invalid angle sum (angles) - API Call
    Given the user has these input data
    		| inputType       | angles    |
    		| a               | 30        |
    		| b               | 50        |
    		| c               | 30        | 
    When the user calls the "/api/triangle/classify" endpoint
    Then the user should get status code 200
     And JSON data received is
        | typeOfTriangle  | Not a triangle                                |                             
        | explanation     | The given values cannot form a triangle.The sum of all the angles of a triangle is equal to 180 degrees. |
