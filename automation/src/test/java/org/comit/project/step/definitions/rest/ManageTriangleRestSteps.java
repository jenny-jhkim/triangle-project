package org.comit.project.step.definitions.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.comit.project.contexts.TestContext;
import org.comit.project.provider.properties.TestProperties;
import org.comit.project.restObjects.ManageTriangleEndPoint;
import org.comit.project.restObjects.TriangleClassifyEndPoint;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class ManageTriangleRestSteps {
	
	@Autowired
	private ManageTriangleEndPoint triangleEndPoint;
	
	@Autowired
	private TestProperties properties;
	
	@Autowired
	private TestContext testContext;
	
	private Response response;
	
	@Given("the user creates a triangle with these input data")
	public void the_user_creates_a_triangle_with_these_input_data(DataTable data) throws JsonProcessingException {
		this.testContext.setTriangleData( this.convertDataTableToMap(data) );
	    this.triangleEndPoint.createSaveRequest(this.properties.getBackend(), data);
	}
	
	@When("the user sends a POST request to {string}")
	public void the_user_sends_a_post_request_to(String path) {
		this.response = this.triangleEndPoint.sendPostRequest(path);	
		//get ID from the response after saving
		this.testContext.setTriangleId( this.response.jsonPath().getInt("id") );
	}
	
	@Then("the response status should be {int}")
	public void the_response_status_should_be (Integer httpStatusCode) {
		
		assertThat(this.response.getStatusCode())
		.as("Expected http status code: %s, but was: %s", httpStatusCode, this.response.getStatusCode())
		.isEqualTo(httpStatusCode);
	}

	@Given("the request is prepared")
	public void the_request_is_prepared() {
	    this.triangleEndPoint.createRequest( properties.getBackend() );
	}

	@When("the user sends a GET request to {string}")
	public void the_user_sends_a_get_request_to(String path) {
		this.response = this.triangleEndPoint.sendGetRequest(path);
	}

	@SuppressWarnings("unchecked")
	@Then("the response should contain the previously created triangle")
	public void the_response_should_contain_the_previously_created_triangle() {
	    List<Map<String, Object>> triangles = this.response.jsonPath().getList("$");
	    
		//System.out.println(this.response.jsonPath().prettify());
	    
	    int expectedId = this.testContext.getTriangleId();
	    
	    Map<String, Object> match = triangles.stream()
	    									.filter( triangle -> expectedId == (int) triangle.get("id") )
	    									.findAny()
	    									.orElseThrow( ()-> new AssertionError("Triangle with Id '" + expectedId + "' not found in the response"));
	    
	    this.testContext.getTriangleData().forEach( (key, expectedValue) -> {
	    	if("Field".equals(key)) {
	    		return; //skip
	    	}
	    	
	    	Object actualValue = match.get(key);
	    	
	    	if("inputType".equals(key) || "triangleType".equals(key) ) {
		    	assertThat(actualValue)
	    		.as("Expected - %s : %s, but was : %s", key, expectedValue, actualValue)
	    		.isNotNull()
	    		.isEqualTo( expectedValue );
	    	} else {
		    	assertThat(actualValue)
	    		.as("Expected - %s : %s, but was : %s", key, expectedValue, actualValue)
    			.isInstanceOf(Integer.class)
    			.extracting(Object::toString)
	    		.isEqualTo( expectedValue );
	    	}
	    });
	}

	@Given("the user prepares updated triangle data by setting {string} to {int} and classifying triangleType")
	public void the_user_prepares_updated_triangle_data(String field, Integer value) throws JsonProcessingException {
		 Map<String, String> data = this.testContext.getTriangleData();
		 data.put(field, String.valueOf(value));
		 this.testContext.setTriangleData(data);
		 
		 //call classify request for "triangleType" updates
		 this.triangleEndPoint.createClassifyRequest(this.properties.getBackend(), data);
		 Response classifyResponse = this.triangleEndPoint.sendPostRequest("/api/triangle/classify");
		 System.out.println("getStatusCode:" + classifyResponse.getStatusCode());
		 System.out.println("type:" + classifyResponse.jsonPath().getString("typeOfTriangle"));
		 data.put("triangleType", classifyResponse.jsonPath().getString("typeOfTriangle"));
		 
		 this.testContext.setTriangleData(data);
	}

	@When("the user updates the triangle via a PUT request {string}")
	public void the_user_updates_the_triangle_via_a_put_request(String path) throws JsonProcessingException {
		int id = this.testContext.getTriangleId();
		Map<String, String> data = this.testContext.getTriangleData();

		this.response = this.triangleEndPoint.sendPutRequest(path, id, data);
	}
	
	@Then("JSON response should contain updated data")
	public void JSON_response_should_contain_updated_data(DataTable data) {
	    Map<String, String> expectedData = data.asMap(String.class, String.class);
	    	    
	    expectedData.forEach( (key, expectedValue) -> {
	    	
	    	if("inputType".equals(key) || "triangleType".equals(key) ) {
	    		String actualValue = response.jsonPath().getString(key);
		        assertThat(actualValue)
	        	.as("Expected %s: %s, but was: %s" , key, expectedValue, actualValue)
	        	.isEqualTo(expectedValue);
	    	} else {
	    		//int
	     		int actualValue = response.jsonPath().getInt(key);
		        assertThat( String.valueOf(actualValue) )
	        	.as("Expected %s: %s, but was: %s" , key, expectedValue, actualValue)
	        	.isEqualTo(expectedValue);
	    	}
	    	
	    });
	}
	
	

	private Map<String, String> convertDataTableToMap(DataTable data) {
		return new HashMap<>(data.asMap());
	}


	
}
