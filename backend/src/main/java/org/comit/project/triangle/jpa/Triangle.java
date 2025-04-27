package org.comit.project.triangle.jpa;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRIANGLE")
public class Triangle implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
    @Column(name="input_type")
	String inputType;
	
	int a;
	int b;
	int c;
	
    @Column(name="triangle_type")
	String triangleType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public String getTriangleType() {
		return triangleType;
	}

	public void setTriangleType(String triangleType) {
		this.triangleType = triangleType;
	}
	
	public Triangle() {}

	public Triangle(int id, String inputType, int a, int b, int c, String triangleType) {
		this.id = id;
		this.inputType = inputType;
		this.a = a;
		this.b = b;
		this.c = c;
		this.triangleType = triangleType;
	}

	@Override
	public String toString() {
		return String.format("Triangle [id=%s, inputType=%s, a=%s, b=%s, c=%s, triangleType=%s]", id, inputType, a, b,
				c, triangleType);
	}
	
}
