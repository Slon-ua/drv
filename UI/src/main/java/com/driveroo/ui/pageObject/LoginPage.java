package com.driveroo.ui.pageObject;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public static LoginPage open(){
        Selenide.open("/login");
        return new LoginPage();
    }

    public void loginAs(String login, String pass) {
//        $(" label:nth-child(1) > input").waitUntil(Condition.visible,8000);
        //when
        $(" label:nth-child(1) > input").setValue(login);
        $(" label:nth-child(2) > input").setValue(pass);
        $("div > button").click();
    }

}
