@smoke
@datatable
Feature: Search for items on Etsy
  As a user
  I want to search for different items
  So that I will be able to buy necessary and can select shipping destination country

  Scenario: Search for particular item
    Given I am on the main page
    #When I change local settings for language to "English (US)" for region to "United Kingdom" and currency to "$ United States Dollar (USD)"
    When I change local settings:
      | language     | region         | currency                     |
      | English (US) | United Kingdom | $ United States Dollar (USD) |
    When I search for "leather bag" items
    Then I see search results
    When I select "Ukraine" ship destination
    Then I get search results for "Leather bag" items