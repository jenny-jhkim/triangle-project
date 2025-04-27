package org.comit.project.triangle.service;

public class TriangleClassifier {
	TriangleValidator triangle;
	
	public TriangleClassifier() {
		this.triangle = new TriangleValidator();
	}
	
	public String classifyByAngles(int angleOne, int angleTwo, int angleThree) {
		
		if(!triangle.isValidWithAngles(angleOne, angleTwo, angleThree)) {
			return "Not a triangle";
		}

		if( angleOne == 90 || angleTwo == 90 || angleThree == 90 ) {
			return "Right";
		} else if(angleOne < 90 && angleTwo < 90 && angleThree < 90 ){
			return "Acute";
		} else {
			return "Obtuse";
		}
	}
	
	public String classifyBySides(int a, int b, int c) {
		
		if(!triangle.isValidWithSides(a, b, c)) {
			return "Not a triangle";
		}
		
		if( a==b && b==c ) {
			return "Equilateral";
		} else if ( a==b || b==c || a==c ) {
			return "Isosceles";
		} else {
			return "Scalene";
		}
	}
}
