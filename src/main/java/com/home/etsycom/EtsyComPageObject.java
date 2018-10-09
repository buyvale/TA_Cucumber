package com.home.etsycom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class EtsyComPageObject extends PageObject {


    private static final String FILTER_FOR_CATEGORY_LINK = "//h5[text()='%s']/../..//a[contains(.,'%s')]";
    private static final String SEARCH_BUTTON_WRAPPER = "button.homepage-search-button";
    private static final String SEARCH_FIRST_SUGGESTION = "#search-suggestions ul li.as-first";
    private final String SEARCH_SUGGESTION_LIST = "#search-suggestions ul li";
    private final String SHOP_LOCATION_SEARCH_BUTTON = "button.shop-location-submit";
    private final String SHOP_LOCATION_TEXT_FIELD = "#shop-location-input";
    private final String SEARCH_FIELD = "#search-query";

    private UpdateSettingsForm updateSettingsForm;
    private PriceFilterPO priceFilterSection;

    public EtsyComPageObject(WebDriver webDriver) {
        super(webDriver);
        priceFilterSection = new PriceFilterPO(webDriver);
        webDriver.get("http://www.etsy.com/uk");
    }

    public EtsyComPageObject goToMainPage(String linkToMainPage) {
        webDriver.get(linkToMainPage);
        return this;
    }
    public void setLanguage(String language) throws InterruptedException {
        clickSettingsButton();
        updateSettingsForm = new UpdateSettingsForm(webDriver);
        updateSettingsForm.setPageLanguage(language);
        updateSettingsForm.saveSettings();
    }

    public EtsyComPageObject pickFirstSuggestion() {
        findElementWithWait(By.cssSelector(SEARCH_SUGGESTION_LIST));
        WebElement firstSuggestion = getFirstSuggestionElement();
        firstSuggestion.click();
        return this;
    }

    private WebElement getFirstSuggestionElement() {
        return findElementWithWait(By.cssSelector(SEARCH_FIRST_SUGGESTION));
    }

    private void waitForSuggestionDropDown() {
        new WebDriverWait(webDriver,30).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SEARCH_SUGGESTION_LIST)));
    }

    private void clickSettingsButton() {
        getLocaleSettingsButton().click();
        findElementWithWait(By.cssSelector("#locale-picker-overlay"));
    }

       private WebElement getLocaleSettingsButton() {
        return findElementWithWait(By.cssSelector("button.footer-locale-settings-button"));
    }

    public EtsyComPageObject searchFor(String items) {
        WebElement etsyComSearchingField = findElementWithWait(By.id("search-query"));
        etsyComSearchingField.clear();
        etsyComSearchingField.sendKeys(items);
        etsyComSearchingField.submit();
        return this;
    }
    public void searchFor_Alternative(String items) {
        enterSearchQuery(items);
        pickFirstSuggestion();
    }

    private void enterSearchQuery(String items) {
        WebElement searchField = findElementWithWait(By.cssSelector(SEARCH_FIELD));
        searchField.clear();
        searchField.sendKeys(items);
    }

    public boolean isSearchResultsVisible() {

        WebElement searchResults = findElementWithWait(By.xpath("//*[@id=\"content\"]/div/div/div/div/div[2]/div[2]/div[1]/div[1]/div/span[5]"));
        return searchResults.getText().contains("Results");
    }

    public String getPageTitle() {
        return webDriver.getTitle().toLowerCase();
    }

    public void applyFilterFromCategory(String category, String filter) {
        WebElement filter_link = filterCheckboxForCategorySection(filter, category);
        filter_link.click();
    }

    private WebElement filterCheckboxForCategorySection(String filter, String category) {
        String xpath = String.format(FILTER_FOR_CATEGORY_LINK, category, filter);
        return new WebDriverWait(webDriver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))
        );
    }

    public List<String> getAppliedFilterTagsForSearchResults() {
        return findElementsWithWait(By.cssSelector(".tag"))
                .stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());
    }

    public void selectShippingDestination(String destination) {
        WebElement shippingDestinationsList = findElementWithWait(By.name("ship_to"));
        Select wrapper = new Select(shippingDestinationsList);
        wrapper.selectByVisibleText(destination);
    }

    public void selectPageLanguage(String language) {
        WebElement pageLanguage = findElementWithWait(By.name("language_code"));
        Select languageWrapper = new Select(pageLanguage);
        languageWrapper.selectByVisibleText(language);
    }

    public void selectRegion(String region) {
        WebElement regionCode = findElementWithWait(By.id("locale-overlay-select-region_code"));
        Select regionWrapper = new Select(regionCode);
        regionWrapper.selectByVisibleText(region);
    }

    public void selectCurrency(String currency) {
        WebElement shoppingCurrency = findElementWithWait(By.name("currency_code"));
        Select currencyWrapper = new Select(shoppingCurrency);
        currencyWrapper.selectByVisibleText(currency);
    }

    public void clickOnSettingsButton() {
        WebElement settingsButton = findElementWithWait(By.className("footer-locale-settings-button"));
        settingsButton.click();
    }

    public void clickOnSaveSettingsButton() {
        WebElement saveSettingsButton = findElementWithWait(By.name("save"));
        saveSettingsButton.click();
    }

    public void changeLocalSettings(String language, String region, String currency) {
        clickOnSettingsButton();
        selectPageLanguage(language);
        selectRegion(region);
        selectCurrency(currency);
        clickOnSaveSettingsButton();
    }

    public void setFilterMinimumPrice(String minimumPrice) {
        priceFilterSection.setMinimumFilterPrice(minimumPrice);
    }

    public void setFilterMaximumPrice(String maximumPrice) {
        priceFilterSection.setMaximumFilterPrice(maximumPrice);
    }

    public void filterItemsByPrice() {
        priceFilterSection.clickFilterButton();
    }

    public String getPriceFilterTag() {
        return getAppliedFilterTagsForSearchResults().get(0);

    }

}
