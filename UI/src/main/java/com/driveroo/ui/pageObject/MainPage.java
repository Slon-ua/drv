package com.driveroo.ui.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public SelenideElement whatIsNew(){
        return $("h5 > h2 > span");
    }

    public SelenideElement companiName(){
        return $("ul > div > h4");
    }


}
