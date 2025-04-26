package org.comit.project.step.definitions.page;

import static org.assertj.core.api.Assertions.assertThat;

import org.comit.project.pageObjects.TrianglePage;
import org.comit.project.provider.properties.TestProperties;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TriangleHomeSteps {
	
	@Autowired 
	TrianglePage trianglePage;
	
	@Autowired
	TestProperties testProperties;
	
	@Given("the user is on the triangle system")
	public void the_user_is_on_the_triangle_system() {
		this.trianglePage.openURL(this.testProperties.getFrontend());
	}
	
	@Given("the user is on the triangle page {string}")
	public void the_user_is_on_the_triangle_page(String pageTitle) {
		this.trianglePage.verifyPageTitle(pageTitle);
	}
	
	@Given("the input type is set to {string}")
	public void the_input_type_is_set_to(String expectedInputType) {
		String selectedType = this.trianglePage.getSelectedInputType();
		
		assertThat(selectedType)
			.as("Expected input type: %s, but was: %s", expectedInputType, selectedType)
			.isEqualTo(expectedInputType);
	}

	@When("the user selects {string} from the input type dropdown")
	public void the_user_selects_from_the_input_type_dropdown(String inputType) {
		this.trianglePage.setInputType(inputType);
	}

	@When("the user enters inputA {int} and inputB {int} and inputC {int}")
	public void the_user_enters_input_a_and_input_b_and_input_c(int a, int b, int c) {
	    this.trianglePage.setInputValues(a, b, c);
	}
	
	@When("the user clicks the {string} button")
	public void the_user_clicks_the_button(String buttonText) {
	    this.trianglePage.clickOnButton(buttonText);
	}

	@Then("the user should see the result {string}")
	public void the_user_should_see_the_result(String result) {
		assertThat(this.trianglePage.verifyMessageWithWait(result))
			.as("Expected result not found: %s", result)
			.isTrue();
	}

	@Then("check more details {string}")
	public void check_more_details(String explanation) {
	    assertThat(this.trianglePage.verifyMessage(explanation))
	    	.as("Expected triangle explanation: %s", explanation)
	    	.isTrue();
	}
	
	@Then("the user should see the error alert {string}")
	public void the_user_should_see_the_error_alert(String errorMessage) {
		assertThat(this.trianglePage.verifyErrorMessage(errorMessage))
		.as("Expected error message not found: %s", errorMessage)
		.isTrue();
	}
	

}
