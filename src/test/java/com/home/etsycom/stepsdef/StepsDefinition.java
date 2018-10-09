package com.home.etsycom.stepsdef;

import com.home.etsycom.EtsyComPageObject;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class StepsDefinition {
    private WebDriver webDriver;
    private EtsyComPageObject etsyPage;
    final static String ETSY_MAIN_PAGE_LINK = "http://www.etsy.com/uk";

    @Before
    public void initializeTest() {
        System.out.println("Starting our Cucumber tests");
        ChromeOptions options = new ChromeOptions();
        boolean isHeadless = Boolean.valueOf(System.getProperty("isHeadless"));
        options.setHeadless(isHeadless);
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("chromeDriverBinaryPath") + "/chromedriver.exe");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        etsyPage = new EtsyComPageObject(webDriver);
    }

    @Given("^I am on the main page$")
    public void i_am_on_the_main_page() {
        etsyPage.goToMainPage(ETSY_MAIN_PAGE_LINK);
    }

/*
    @When("^I accept terms and conditions$")
    public void i_accept_terms_and_conditions() {
        etsyPage.acceptTermsAndConditions();
    }
*/

    @When("^I search for items and apply filters:$")
    public void search_for_items_and_apply_filters(DataTable table) {
        List<List<String>> values = table.asLists();
        String searchQuery = values.get(1).get(0);
        String category = values.get(1).get(1);
        String filter = values.get(1).get(2);
        etsyPage.searchFor(searchQuery);
        if (etsyPage.isSearchResultsVisible()) {
            etsyPage.applyFilterFromCategory(category, filter);
        }
    }

    @Then("^next filter tags are visible:$")
    public void checkAppliedFilterTagsOnSearchResults(DataTable table) {
        List<String> expectedTags = table.rows(1).asList();
        List<String> actualTags = etsyPage.getAppliedFilterTagsForSearchResults();
        for (String tag : actualTags) {
            Assertions.assertTrue(expectedTags.contains(tag), "Checking tag " + tag);
        }
    }


    @When("I search for \"(.*?)\" items")
    public void i_search_for_items(String items) {
        etsyPage.searchFor(items);
    }

    @When("I select \"(.*?)\" ship destination")
    public void selectShippingDestination(String destination) {
        etsyPage.selectShippingDestination(destination);
    }

    @When("I set page language to \"(.*?)\"")
    public void i_set_page_language_to(String language) {
        etsyPage.selectPageLanguage(language);

    }

    @When("I select region \"(.*?)\"")
    public void i_select_region(String region) {
        etsyPage.selectRegion(region);
    }

    @When("I select currency \"(.*?)\"")
    public void i_select_currency(String currency) {
        etsyPage.selectCurrency(currency);
    }

    @When("I change local settings for language to \"(.*?)\" for region to \"(.*?)\" and currency to \"(.*?)\"")
    public void i_change_local_settings_for_language_to_for_region_to_and_currency_to(String language, String region, String currency) {
        etsyPage.changeLocalSettings(language, region, currency);
    }

    @When("I change local settings:")
    public void i_change_local_settings(DataTable localSettings) {
        List<List<String>> settings = localSettings.asLists();
        String  language = settings.get(1).get(0);
        String  region = settings.get(1).get(1);
        String  currency = settings.get(1).get(2);
        etsyPage.changeLocalSettings(language, region, currency);
    }

    @When("^I set minimum price to \"(.*?)\"$")
    public void setFilterMinimumPriceTo(String minimumPrice) {
        etsyPage.setFilterMinimumPrice(minimumPrice);
    }

    @When("^I set maximum price to \"(.*?)\"$")
    public void setFilterMaximumPriceTo(String maximumPrice) {
        etsyPage.setFilterMaximumPrice(maximumPrice);
    }

    @When("^I filter items by price$")
    public void filterItemsByPrice() {
        etsyPage.filterItemsByPrice();
    }

    @Then("^next price filter tag \"(.*?)\" is visible$")
    public void isPriceFilterTagVisible(String priceFilter){
        Assertions.assertTrue(
                priceFilter.equalsIgnoreCase(etsyPage.getPriceFilterTag())
        );
    }



    @Then("I see search results")
    public void i_see_search_results() {
        Assertions.assertTrue(etsyPage.isSearchResultsVisible());
    }

    @Then("I get search results for \"(.*?)\" items")
    public void i_get_search_results_for_items(String items) {
        Assertions.assertTrue(etsyPage.getPageTitle().toLowerCase().contains(items.toLowerCase()));
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }


}
