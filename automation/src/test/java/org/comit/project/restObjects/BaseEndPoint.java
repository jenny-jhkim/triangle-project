package org.comit.project.restObjects;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseEndPoint {
	
	@Autowired
	ObjectMapper objectMapper;
	
	RequestSpecification reqSpec;
	
	public void createRequest(String backEndUrl) {
		this.reqSpec = RestAssured.with()
				.baseUri(backEndUrl)
				.contentType(ContentType.JSON);
	}
		
	public void createRequest(String backEndUrl, ObjectNode node) throws JsonProcessingException 
	{
		this.createRequest(backEndUrl);
		this.reqSpec.body(this.objectMapper.writeValueAsString(node));
	}
	
	public Response sendPostRequest(String path) {
		return RestAssured.given(this.reqSpec).post(path);
	}

	public Response sendPutRequest(String path, int id, ObjectNode data) throws JsonProcessingException {
		String resolvedPath = path.replace("{id}", String.valueOf(id));
		String jsonBody = this.objectMapper.writeValueAsString(data);
		
		System.out.println("Put url: " + resolvedPath);
		return RestAssured.given(this.reqSpec)
				.body(jsonBody)
				.put(resolvedPath);
	}
	
	public Response sendGetRequest(String path) {
		return RestAssured.given(this.reqSpec).get(path);
	}
	
	public ObjectNode convertDataTabletoObjectNode(DataTable data) {
		ObjectNode root = this.objectMapper.createObjectNode();
		
		data.asMap().forEach( (key, value) -> {  
			root.put(key, value);
		});
		
		return root;
	}
	
	public ObjectNode convertMapToObjectNode(Map<String, String> data) {
		ObjectNode root = this.objectMapper.createObjectNode();
		
		data.forEach(root::put);
		return root;
	}
}
