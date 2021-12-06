package com.driveroo.ui.test;

import com.codeborne.selenide.Condition;
import com.driveroo.api.conditions.BodyFieldCondition;
import com.driveroo.api.conditions.StatusCodeCondition;
import com.driveroo.api.payload.UserPayload;
import com.driveroo.api.services.UserApiService;
import com.driveroo.ui.pageObject.LoginPage;
import com.driveroo.ui.pageObject.MainPage;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.Matchers.is;

public class UserLogin extends BaseUITest {

    UserApiService userApiService = new UserApiService();

//    @BeforeAll
//    public static void setUpAll() {
//        Configuration.browserSize = "1280x800";b
//        SelenideLogger.addListener("allure", new AllureSelenide());
//
//    }
//
//    @BeforeEach
//    public void setUp() {
//        open("https://sand_fleet.driveroo.com/login");
//    }

    @Test
    void name() {
//        RestAssured.baseURI ="https://sandbox.driveroo.com";

        UserPayload userPayload = new UserPayload();
        // given
        userPayload
                .login("sergeyk+0")
                .password("123456")
                .deviceToken("74e879b8-eac0-46df-aa32-16bddadcebff")
                .deviceType("web")
                .loginBy("manual")
        ;

        // expect
        String success = userApiService.userLogin(userPayload)
                .shouldHave(new StatusCodeCondition(HttpStatus.SC_OK))
                .shouldHave(new BodyFieldCondition("success", is(true)))
                .getValue("success")
                ;
        System.out.println(success);

        LoginPage
                .open()
                .loginAs(userPayload.login(),userPayload.password());
        
//        //when
//        $(" label:nth-child(1) > input").setValue(userPayload.login());
//        $(" label:nth-child(2) > input").setValue(userPayload.login());
//        $("div > button").click();

        //then
        MainPage mainPage = at(MainPage.class);

        mainPage.companiName().shouldHave(text("Driveroo_Test_Auto_Env"));
        mainPage.whatIsNew().shouldHave(text("What's new?"));

        at(MainPage.class).companiName().shouldHave(text("Driveroo_Test_Auto_Env"));
        at(MainPage.class).companiName().shouldHave(Condition.text("Driveroo_Test_Auto_Env"));

        $("h5 > h2 > span").shouldHave(text("What's new?"));
        $("div.modal-header > button").click();
        $("ul > div > h4").shouldHave(text("Driveroo_Test_Auto_Env"));
    }
}
