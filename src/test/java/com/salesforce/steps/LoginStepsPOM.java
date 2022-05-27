package com.salesforce.steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.salesforce.pages.home.HomePage;
import com.salesforce.pages.login.CheckEmailPage;
import com.salesforce.pages.login.ForgotPasswordPage;
import com.salesforce.pages.login.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

//extends TestNGRunCucumberTest ---------->if you calling this class from testng.xml

public class LoginStepsPOM {
	
	WebDriver driver;
	LoginPage login;
	HomePage home;
	ForgotPasswordPage frgtPwd;
	CheckEmailPage chkEmail;
	SoftAssert soft;
	
	@Before(order=0)										//we can have multiple @Before and @After methods by mentioning order sequence using "order" parameter
	public void setUp1() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@Before(order=1)
	public void setUp2() {
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	

	@Given("user opens Salesforce Application with url {string}")
	public void user_opens_salesforce_application_with_url(String url) {
		
		driver.get(url);
	}
	
	@When("user is on {string}")
	public void user_is_on(String page) {
		
		if(page.equalsIgnoreCase("loginpage"))
	    	login = new LoginPage(driver);
	    else if(page.equalsIgnoreCase("homepage"))
	    	home = new HomePage(driver);
	    else if(page.equals("ForgotPasswordPage"))
	    	frgtPwd = new ForgotPasswordPage(driver);
	    else if(page.equals("CheckEmailPage"))
	    	chkEmail = new CheckEmailPage(driver);
	}

	@When("user enters username {string} in Username textbox")
	public void user_enters_username_in_username_textbox(String username) throws InterruptedException {
		
		Thread.sleep(3000);
		login.setUsername(username);
	}

	@When("user clears password textbox and leaves it empty")
	public void user_clears_password_textbox_and_leaves_it_empty() {
	    
		login.clearPasswordField();
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
	    
		login.clickLoginButton();
	}
	
	@Then("verify user gets the error message {string}")
	public void verify_user_gets_the_error_message(String expectedError) {
	    
		String actualError = login.getNoPwdError();
		Assert.assertTrue(actualError.equalsIgnoreCase(expectedError), "Expected error message is NOT displayed");
	}

	@When("user enters password {string} in Password textbox")
	public void user_enters_password_in_password_textbox(String password) {
		
		login.setPassword(password);
	}

	@Then("verify title of the page is {string}")
	public void verify_title_of_the_page_is(String expectedTitle) throws InterruptedException {
	    
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		String actualTitle = home.getTitleOfTheHomePage();
		Assert.assertTrue(actualTitle.equalsIgnoreCase(expectedTitle), "Expected webpage has not loaded");
	}

	@Then("name of user on Home Page dashboard is {string}")
	public void name_of_user_on_home_page_dashboard_is(String expectedUserName) {

		soft = new SoftAssert();
		String dashboardName = home.getHomePageDashboardUserName();
		System.out.println("Username appearing on the SFDC Home dashboard: " + dashboardName);
		soft.assertEquals(dashboardName, expectedUserName, "User's fullname on the dashboard does NOT match with the expected name");
		soft.assertAll();
	}
	
	@When("user check the Remember Me checkbox")
	public void user_check_the_remember_me_checkbox() {
	   
		login.checkRemeberMe();
	}

	@When("user clicks on user menu dropdown")
	public void user_clicks_on_user_menu_dropdown() {

		home.clickUserMenuDropdown();
	}

	@When("user clicks on Logout link")
	public void user_clicks_on_logout_link() {

		home.clickLogoutLink();
	}

	@Then("verify displayed username is {string}")
	public void verify_displayed_username_is(String expectedUsername) throws InterruptedException {

		Thread.sleep(2000);
		String displayedUsername = login.getDisplayedUsername();
		Assert.assertEquals(displayedUsername, expectedUsername, "Displayed username is not correct");
	}

	@Then("verify Remember Me checkbox is checked")
	public void verify_remember_me_checkbox_is_checked() {

		boolean flag = login.isRememberMeChecked();
		soft = new SoftAssert();
		soft.assertTrue(flag, "Remember Me checkbox is not checked");
		soft.assertAll();
	}
	
	@When("user clicks on Forgot Password link")
	public void user_clicks_on_forgot_password_link() {
	    
		login.clickForgotPwdLink();
	}

	@Then("verify forgot password page header text is {string}")
	public void verify_forgot_password_page_header_text_is(String expectedHeader) throws InterruptedException {

		Thread.sleep(3000);
		String actualHeader = frgtPwd.getForgotPwdHeaderText();
		Assert.assertEquals(actualHeader, expectedHeader, "Header text is different from the expected one");
	}
	
	@When("user enters email {string} in Username textbox")
	public void user_enters_email_in_username_textbox(String username) {
		
		frgtPwd.enterEmail(username);
	}

	@When("user clicks on Continue button")
	public void user_clicks_on_continue_button() {

		frgtPwd.clickContinueButton();
	}

	@Then("verify check email page header text is {string}")
	public void verify_check_email_page_header_text_is(String expectedHeader) {
	   
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		String actualHeader = chkEmail.getCheckEmailHeader();
		Assert.assertEquals(actualHeader, expectedHeader, "Header text is different from the expected one");
	}
	
	@Then("verify user gets invalid login error {string}")
	public void verify_user_gets_invalid_login_error(String expectedError) {

		String actualError = login.getLoginError();
		Assert.assertTrue(actualError.equalsIgnoreCase(expectedError), "Expected login error message is not displaye");
	}
	
	@Then("verify user should see the following dropdown options")
	public void verify_user_should_see_the_following_dropdown_options(List<String> userMenuOptions) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.

		System.out.println("Expected user menu dropdown options: " +userMenuOptions);
		List<String> actualMenuList = home.getUserMenuList();
		System.out.println("Actual user Menu Dropdown List on Salesforce Homepage: " + actualMenuList);
		soft = new SoftAssert();
		soft.assertTrue(actualMenuList.containsAll(userMenuOptions), "User Menu dropdown list does NOT contain the expected options");
		soft.assertAll();
	}
	
	@After
	public void tearDown() throws InterruptedException {
		
		Thread.sleep(3000);
		driver.quit();
	}
}
