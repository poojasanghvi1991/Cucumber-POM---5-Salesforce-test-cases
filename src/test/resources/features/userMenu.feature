Feature: Explore user menu dropdown

Background:
	Given user opens Salesforce Application with url "https://login.salesforce.com/"
	When user is on "LoginPage"
	And user enters username "pooja1991@tekarch.com" in Username textbox
	And user enters password "Test@2022" in Password textbox
	And user clicks on login button
	
	@validateUserMenuDropdown
	Scenario:
	When user is on "HomePage"
	Then verify title of the page is "Home Page ~ Salesforce - Developer Edition"
	And name of user on Home Page dashboard is "Pooja Sanghvi"
	When user clicks on user menu dropdown
	Then verify user should see the following dropdown options
	| My Profile|
	| My Settings|
	| Developer Console|
	| Switch to Lightning Experience|
	| Logout |


