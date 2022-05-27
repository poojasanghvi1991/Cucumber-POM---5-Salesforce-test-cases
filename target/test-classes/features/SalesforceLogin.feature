#This is written for training prupose

Feature: Login to Salesforce Application

Background: 
	Given user opens Salesforce Application with url "https://login.salesforce.com/"

	@noPasswordLogin
	Scenario:
	When user is on "LoginPage"
	And user enters username "pooja1991@tekarch.com" in Username textbox
	And user clears password textbox and leaves it empty
	And user clicks on login button
	Then verify user gets the error message "Please enter your password."
	
	@validLogin
	Scenario:
	When user is on "LoginPage"
	And user enters username "pooja1991@tekarch.com" in Username textbox
	And user enters password "Test@2022" in Password textbox
	And user clicks on login button
	When user is on "HomePage"
	Then verify title of the page is "Home Page ~ Salesforce - Developer Edition"
	And name of user on Home Page dashboard is "Pooja Sanghvi"
	
	@rememberMeChecked
	Scenario:
	When user is on "LoginPage"
	And user enters username "pooja1991@tekarch.com" in Username textbox
	And user enters password "Test@2022" in Password textbox
	And user check the Remember Me checkbox
	And user clicks on login button
	When user is on "HomePage"
	Then verify title of the page is "Home Page ~ Salesforce - Developer Edition"
	When user clicks on user menu dropdown
	And user clicks on Logout link
	And user is on "LoginPage"
	Then verify displayed username is "pooja1991@tekarch.com"
	And verify Remember Me checkbox is checked
	
	@forgotPassword
	Scenario:
	When user is on "LoginPage"
	And user clicks on Forgot Password link
	And user is on "ForgotPasswordPage"
	Then verify forgot password page header text is "Forgot Your Password"
	When user enters email "pooja1991@tekarch.com" in Username textbox
	And user clicks on Continue button
	When user is on "CheckEmailPage"
	Then verify check email page header text is "Check Your Email"
	
	@invalidCredentials
	Scenario:
	When user is on "LoginPage"
	And user enters username "123" in Username textbox
	And user enters password "22131" in Password textbox
	And user clicks on login button
	Then verify user gets invalid login error "Please check your username and password. If you still can't log in, contact your Salesforce administrator."


	
	
	