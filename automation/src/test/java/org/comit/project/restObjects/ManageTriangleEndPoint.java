package org.comit.project.restObjects;

import java.util.Map;

import org.springframework.stereotype.Component;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

@Component
public class ManageTriangleEndPoint extends BaseEndPoint {
	public void createSaveRequest(String backEndUrl, DataTable data) throws JsonProcessingException {
		ObjectNode root = this.convertDataTabletoObjectNode(data);
		this.createRequest(backEndUrl, root);
	}
	
	public void createClassifyRequest(String backEndUrl, Map<String, String> data) throws JsonProcessingException {
		ObjectNode root = this.convertMapToObjectNode(data);
		
		this.createRequest(backEndUrl, root);
	}
	
	public Response sendPutRequest(String path, int id,  Map<String, String> data ) throws JsonProcessingException {
		ObjectNode root = this.convertMapToObjectNode(data);
		
		return this.sendPutRequest(path, id, root);
	}
}
