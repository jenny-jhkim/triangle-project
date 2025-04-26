package org.comit.project.step.definitions.page;

import static org.assertj.core.api.Assertions.assertThat;

import org.comit.project.pageObjects.TrianglePage;
import org.comit.project.provider.properties.TestProperties;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TriangleListPageSteps {

	@Autowired 
	TrianglePage trianglePage;
	
	@Autowired
	TestProperties testProperties;
	
	@Given("the user is on the main page titled {string}")
	public void the_user_is_on_the_main_page_titled(String pageTitle) {
		this.trianglePage.openURL(this.testProperties.getFrontend());
		this.verifyPageTitle(pageTitle);
	}
	
	@Then("the user should see a information message {string}")
	public void the_user_should_see_a_information_message(String infoMessage) {
		this.verifyMessage(infoMessage);
	}

	private void verifyMessage(String expectedMessage) {
		assertThat(this.trianglePage.verifyMessageWithWait(expectedMessage))
			.as("Expected message not found: %s", expectedMessage)
			.isTrue();
	}
	
	private void verifyPageTitle(String expectedTitle) {
		assertThat(this.trianglePage.verifyMessage(expectedTitle))
			.as("Expected title not found: %s", expectedTitle)
			.isTrue();
	}



}
