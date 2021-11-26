package com.driveroo.appium.test;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    public SelenideElement seeAllToolsButton = $("a.wt-button_mode_primary");
    public SelenideElement toolsMenu = $x("//button[contains(@class, 'main-menu-item__action') and text() = 'Developer Tools']");
    public SelenideElement searchButton = $("[data-test='site-header-search-action']");
}
