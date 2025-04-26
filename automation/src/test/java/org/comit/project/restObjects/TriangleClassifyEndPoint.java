package org.comit.project.restObjects;

import org.springframework.stereotype.Component;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@Component
public class TriangleClassifyEndPoint extends BaseEndPoint{
	
	public void createClassifyRequest(String backEndUrl, DataTable data) throws JsonProcessingException {
		ObjectNode root = this.convertDataTabletoObjectNode(data);
		
		this.createRequest(backEndUrl, root);
	}
	

}
