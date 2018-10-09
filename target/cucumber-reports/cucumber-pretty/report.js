$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/EtsyComSample.feature");
formatter.feature({
  "name": "Search for items on Etsy",
  "description": "  As a user\n  I want to search for different items\n  So that I will be able to buy necessary and can select shipping destination country",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@smoke"
    },
    {
      "name": "@datatable"
    }
  ]
});
formatter.scenario({
  "name": "Search for particular item",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@smoke"
    },
    {
      "name": "@datatable"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I am on the main page",
  "keyword": "Given "
});
formatter.match({
  "location": "StepsDefinition.i_am_on_the_main_page()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I change local settings:",
  "rows": [
    {
      "cells": [
        "language",
        "region",
        "currency"
      ]
    },
    {
      "cells": [
        "English (US)",
        "United Kingdom",
        "$ United States Dollar (USD)"
      ]
    }
  ],
  "keyword": "When "
});
formatter.match({
  "location": "StepsDefinition.i_change_local_settings(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I search for \"leather bag\" items",
  "keyword": "When "
});
formatter.match({
  "location": "StepsDefinition.i_search_for_items(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I see search results",
  "keyword": "Then "
});
formatter.match({
  "location": "StepsDefinition.i_see_search_results()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I select \"Ukraine\" ship destination",
  "keyword": "When "
});
formatter.match({
  "location": "StepsDefinition.selectShippingDestination(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get search results for \"Leather bag\" items",
  "keyword": "Then "
});
formatter.match({
  "location": "StepsDefinition.i_get_search_results_for_items(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});