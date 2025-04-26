package org.comit.project.runners;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

@Suite
@IncludeEngines("cucumber")
//@SelectClasspathResource("org/comit/users/features")
//@SelectClasspathResource("org/comit/users/features/page/loginPractice.feature")

//Selenium Web Test
@SelectClasspathResource("org/comit/project/features/page/triangleHome.feature")
@SelectClasspathResource("org/comit/project/features/page/triangleListPage.feature")
@SelectClasspathResource("org/comit/project/features/page/saveTriangleHome.feature")

//Rest Assured Test
@SelectClasspathResource("org/comit/project/features/rest/triangleClassifyRest.feature")
@SelectClasspathResource("org/comit/project/features/rest/manageTriangleRest.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.comit.project")
//@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@failCase")
public class RunCucumberTest {


}

