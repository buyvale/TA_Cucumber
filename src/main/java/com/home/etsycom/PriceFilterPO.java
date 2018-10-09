package com.home.etsycom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PriceFilterPO extends PageObject {
    public static final String FILTER_MIN_PRICE_INPUT = "//*[@id='search-filter-min-price-input']";
    private static final String FILTER_MAX_PRICE_INPUT = "//*[@id='search-filter-max-price-input']";

    public PriceFilterPO(WebDriver webDriver) {
        super(webDriver);
    }

    public void setMinimumFilterPrice(String minimumPrice) {
        enterPriceIntoFilterField(FILTER_MIN_PRICE_INPUT, minimumPrice);

    }

    private void enterPriceIntoFilterField(String xpath, String price) {
        WebElement minimumPriceField =
                findElementWithWait(By.xpath(xpath));
        minimumPriceField.clear();
        minimumPriceField.sendKeys(price);
    }

    public void setMaximumFilterPrice(String maximumPrice) {
        enterPriceIntoFilterField(FILTER_MAX_PRICE_INPUT,
                maximumPrice);
    }

    public void clickFilterButton() {
        findElementWithWait(By.xpath("//button[@data-context = 'price']")).
                click();
    }
}
