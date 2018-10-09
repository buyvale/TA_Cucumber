package com.home.etsycom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class UpdateSettingsForm  extends PageObject{

    public UpdateSettingsForm(WebDriver webDriver) {
        super(webDriver);
    }

    public void setPageLanguage(String language) {
        getLanguageDropdown().selectByVisibleText(language);
    }

    private Select getLanguageDropdown() {
        return new Select(findElementWithWait(By.name("language_code")));
    }

    public void saveSettings() {
        findElementWithWait(By.name("save")).click();
    }
}
