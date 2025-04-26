package org.comit.project.contexts;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class TestContext {

	Map<String, String> triangleData;
	
	Integer triangleId;
	
	public Map<String, String> getTriangleData() {
		return triangleData;
	}
	
	public void setTriangleData(Map<String, String> data) {
		this.triangleData = data;
	}

	public Integer getTriangleId() {
		return triangleId;
	}

	public void setTriangleId(Integer id) {
		this.triangleId = id;
	}
	

	
}
