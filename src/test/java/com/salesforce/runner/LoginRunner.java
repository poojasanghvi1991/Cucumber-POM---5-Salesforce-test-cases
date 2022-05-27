package com.salesforce.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(plugin = { "pretty", "html:target/cucumber-reports/cucumber.html","json:target/cucumber-reports/cucumber.json" }, 
							features = {"src/test/resources/features/SalesforceLogin.feature", "src/test/resources/features/userMenu.feature"}, 
							glue = { "com.salesforce.steps" },
							monochrome = true,
							dryRun = false,
							//strict = true;		//throws PendingException: TODO: implement me - if any steps are undefined in stepdef
							tags = "@noPasswordLogin or @validateUserMenuDropdown or @validLogin or @rememberMeChecked or @forgotPassword or @invalidCredentials")

public class LoginRunner extends AbstractTestNGCucumberTests {

}
 