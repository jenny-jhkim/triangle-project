package org.comit.project.step.definitions.page;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.comit.project.contexts.TestContext;
import org.comit.project.pageObjects.TrianglePage;
import org.comit.project.provider.properties.TestProperties;
import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SaveTriangleHomeSteps {

	@Autowired 
	TrianglePage trianglePage;
	
	@Autowired
	TestProperties testProperties;
	
	@Autowired
	private TestContext testContext2;

	@Given("the user is on the triangle main page {string}")
	public void the_user_is_on_the_triangle_main_page(String pageTitle) {
		this.trianglePage.openURL(this.testProperties.getFrontend());
		this.verifyPageTitle(pageTitle);
	}
	
	@Given("the user fills in the form with the following data:")
	public void the_user_fills_in_the_form_with_the_following_data(DataTable data) {
		Map<String, String> tri = this.convertDataTableToMap(data);
		
		this.testContext2.setTriangleData( tri );
  
	  	this.trianglePage.setInputType(tri.get("Input Type"));
	  	String a = tri.get("A").toString();
	  	String b = tri.get("B").toString();
	  	String c = tri.get("C").toString();
	  	this.trianglePage.setInputValues(a, b, c);		  
	}
	
	@Then("the user should see the triangle type as {string} in the result")
	public void the_user_should_see_the_triangle_type_as_in_the_result(String triangleType) {
		this.verifyMessage(triangleType);
		
		//update triangle type to testContext
		Map<String, String> data = this.testContext2.getTriangleData();
		data.put("triangleType", triangleType);
		this.testContext2.setTriangleData(data);
	}
	
	@Then("the {string} button should be enabled")
	public void the_button_should_be_enabled(String buttonText) {
	    this.isButtonEnabled(buttonText);
	}
	
	@Then("the user should see a success message {string}")
	public void the_user_should_see_a_success_message(String message) {
		this.verifyMessage(message);
	}
	
	@Given("the user is ready to input a new dataset")
	public void the_user_is_ready_to_input_a_new_dataset(){
		boolean isClear = this.trianglePage.isFormClear();
		
		System.out.println("Input fields and result are cleared: " + isClear);
	}	

	@Given("the user has saved data")
	public void the_user_has_saved_data() {
		//already save done
	}
	
	@When("the user clicks on the {string} link in the navigation bar")
	public void the_user_clicks_on_the_link_in_the_navigation_bar(String linkText) {
		this.trianglePage.clickOnLink(linkText);
	}
	
	@Then("the user should be redirected to the {string} list page")
	public void the_user_should_be_redirected_to_the_list_page(String pageTitle) {
		this.verifyPageTitle(pageTitle);
	}
	
	@Then("the newly saved triangle data should appear in the triangle list")
	public void the_newly_saved_triangle_data_should_appear_in_the_triangle_list() {
		Map<String, String> triangleData = this.testContext2.getTriangleData();
		
	    String expectedInputType = triangleData.get("Input Type");
	    String expectedA = triangleData.get("A");
	    String expectedB = triangleData.get("B");
	    String expectedC = triangleData.get("C");
	    String expectedTriangle = triangleData.get("triangleType");
	    
	    List<Map<String, String>> rows = this.trianglePage.getTableRows().stream()
				.filter( tri -> expectedTriangle.equals(tri.get("Triangle Type")))
				.toList();
	    
	    //select last row
	    Optional<Map<String, String>> match = rows.isEmpty() ? Optional.empty()
	            											: Optional.of(rows.get(rows.size() - 1)); 
	    Map<String, String> triangle = match.orElse(new HashMap<>());
	    
		assertThat(triangle.get("Input Type"))
			.as("Expected Input Type : %s, but was : %s", expectedInputType, triangle.get("Input Type"))
			.isEqualTo(expectedInputType);

		assertThat(triangle.get("A"))
			.as("Expected A: %s, but was :%s", expectedA, triangle.get("A"))
			.isEqualTo(expectedA);		
		assertThat(triangle.get("B"))
			.as("Expected A: %s, but was :%s", expectedA, triangle.get("B"))
			.isEqualTo(expectedB);
		assertThat(triangle.get("C"))
			.as("Expected A: %s, but was :%s", expectedA, triangle.get("C"))
			.isEqualTo(expectedC);
		assertThat(triangle.get("Triangle Type"))
			.as("TriangleType not found in the list: %s", expectedTriangle)
			.isEqualTo(expectedTriangle);
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
	
	private void isButtonEnabled(String expectedButton) {
		assertThat(this.trianglePage.isButtonEnabled(expectedButton))
			.as("%s button is not enabled.", expectedButton)
			.isTrue();
	}
	private Map<String, String> convertDataTableToMap(DataTable data) {
		return new HashMap<>(data.asMap());
	}


}
