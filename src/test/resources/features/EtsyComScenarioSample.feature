@real
Feature: Filter search items by price
  As a user
  I want to see items which will be filtered by price
  So that I can buy items which fit the best for me

  Scenario Outline: Search for leather bag and apply price filter
    Given I am on the main page
    And I search for "<item>" items
    Then I see search results
    Then I get search results for "<item>" items
    When I set minimum price to "<price from>"
    And I set maximum price to "<price to>"
    And I filter items by price
    Then next price filter tag "<price tag>" is visible

    Examples:
      | item        | price from | price to | price tag     |
      | leather bag | 20         | 50       | PLN20 - PLN50 |