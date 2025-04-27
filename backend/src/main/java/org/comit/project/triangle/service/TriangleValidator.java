package org.comit.project.triangle.service;

public class TriangleValidator {
	boolean isValidWithAngles(int angleOne, int angleTwo, int angleThree) {
		
		if (angleOne <= 0 || angleTwo <= 0 || angleThree <= 0 ) {
			throw new IllegalArgumentException("All angles must be greater than zero.");
		}
		
		//The sum of all the angles of a triangle is equal to 180 degrees.
		return (angleOne + angleTwo + angleThree) == 180;
	}
	
	boolean isValidWithSides(int a, int b, int c) {
		
		if (a <= 0 || b <= 0 || c <= 0 ) {
			throw new IllegalArgumentException("All the length of the side must be greater than zero.");
		}
	
		return ( a + b > c && 
				a + c > b &&
				b + c > a );
	}
}
