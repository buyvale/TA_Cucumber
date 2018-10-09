@datatable
Feature: Search for items (data table mode)
  As a user
  I want to search for different items
  So that I will be able to buy necessary items fast

  Background:
    Given I am on the main page

  Scenario: Handmade tag visibility
    When I search for items and apply filters:
      | items        | filter category | filter   |
      | wedding gift | Item type       | Handmade |
    Then next filter tags are visible:
      | tags     |
      | Handmade |

  Scenario: Free shipping tag visibility
    When I search for items and apply filters:
      | items        | filter category | filter        |
      | wedding gift | Shipping        | Free shipping |
    Then next filter tags are visible:
      | tags          |
      | Free shipping |
