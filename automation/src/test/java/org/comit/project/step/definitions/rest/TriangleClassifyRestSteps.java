package org.comit.project.step.definitions.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.comit.project.provider.properties.TestProperties;
import org.comit.project.restObjects.TriangleClassifyEndPoint;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TriangleClassifyRestSteps {
	
	@Autowired
	private TriangleClassifyEndPoint triangleEndPoint;
	
	@Autowired
	private TestProperties properties;
	
	private Response response;
	
	private String triangleType;
	
	@Given("the user has these input data")
	public void the_user_has_these_input_data(DataTable data) throws JsonProcessingException {

	    this.triangleEndPoint.createClassifyRequest(this.properties.getBackend(), data);
	}

	@When("the user calls the {string} endpoint")
	public void the_user_calls_the_endpoint(String path) {
		this.response = this.triangleEndPoint.sendPostRequest(path);
	}

	@Then("the user should get status code {int}")
	public void the_user_should_get_status_code(Integer httpStatusCode) {
		assertThat(this.response.getStatusCode())
		.as("Expected http status code: %d, but was: %d", httpStatusCode, this.response.getStatusCode())
		.isEqualTo(httpStatusCode);
	}
	
	@Then("JSON error message is")
	public void json_error_message_is(DataTable data) {
		assertThat(this.response.jsonPath().getString("httpStatus"))
			.isEqualTo(data.asMap().get("httpStatus"));
		assertThat(this.response.jsonPath().getString("reason"))
			.isEqualTo(data.asMap().get("reason"));
		assertThat(this.response.jsonPath().getString("message"))
			.isEqualTo(data.asMap().get("message"));
	}

	@Then("JSON data received is")
	public void json_data_received_is(DataTable result) {
		assertThat(this.response.jsonPath().getString("typeOfTriangle")).isEqualTo(result.asMap().get("typeOfTriangle"));
		assertThat(this.response.jsonPath().getString("explanation")).isEqualTo(result.asMap().get("explanation"));
		
		this.triangleType = this.response.jsonPath().getString("typeOfTriangle");
	}

	public String getTriangleType() {
		return this.triangleType;
	}


}
