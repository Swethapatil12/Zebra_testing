#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Test Scenarios for Various Navigation and Search Functionalities

  Scenario: Launching the URL
    Given I create a ChromeDriver object
    When I access the specified URL using Chrome
    Then I click on the Industry menu
    And I capture all menu options under the Industry section and store them in a text file
    And I click on Mobile Technology for Energy and Utilities menu

  Scenario: Industry and Mobile Technology Navigation
    When I click on Digital Safety Inspection and Compliance Reporting menu
    And I navigate through the Hardware section
    And I navigate through the Software section
    And I navigate through the Service and Maintenance section
    And I navigate through the Supplies section
    Then I click on the Find Partner section
    And I click on Partner Apps and Offerings section

  Scenario Outline: Search & Profile View
    When In the search bar, I type "<searchTerm>" and click search
    And I capture the search results
    Then I click on 'View Profile' link next to 'Abetech' in the search results
    And I click on 'Back to search results'

    Examples: 
      | searchTerm |
      | abc        |

  Scenario Outline: Search Filtering & Home Page Navigation
    When I filter search by selecting 'RFID' at Cross Vertical and 'LINUX' at Supported OS
    When I click on 'Nedap Harmony' from filtered results and view the profile
    Then In the search bar, I type "<searchTerms>" and clicks search

    Examples: 
      | searchTerms |
      | wave        |

  Scenario Outline: Fill Contact Form and Return to Homepage
    When I search and click on Wave Warehouse Management System
    And I fill in the inquiry form with "<FirstName>", "<LastName>", "<Company>", and "<Email>"
    Then I click on the Zebra logo image to return to the homepage

    Examples: 
      | FirstName | LastName | Company | Email              |
      | John      | Doe      | ABC     | john.doe@email.com |