package com.driveroo.ui.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.driveroo.api.ProjectConfig;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

import static java.lang.System.getProperty;
import static java.util.Collections.singletonMap;

public class BaseUITest {

    private static final String ENV_VARIABLE_KEY = "env";  //  инициал статическую файнл переменную, котоой присаиваем имя проперти из  onfig.properties  и дальше в коде используем ее

    @BeforeAll
    static void beforeAll() {
//        RestAssured.baseURI = "https://sandbox.driveroo.com";
//        Configuration.baseUrl = "https://sand_fleet.driveroo.com";
//        Configuration.baseUrl = "https://fleet.driveroo.com";



        Map myVars = singletonMap(ENV_VARIABLE_KEY, getProperty(ENV_VARIABLE_KEY));

        if (myVars.get("env")==null) {
             myVars = singletonMap(ENV_VARIABLE_KEY, "sandbox");
        }

        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, myVars, System.getProperties());  // вызов пропертей енвайрмента и его переменных (env.host)
        RestAssured.baseURI = config.host();  //вызов конкретной проперти
        Configuration.baseUrl = config.hostUI();  //вызов конкретной проперти

        Configuration.browserSize = "1280x800";
//        Configuration.timeout = 4000;
        SelenideLogger.addListener("allure", new AllureSelenide());

    }



    protected  <T> T at(Class<T> pageClass) {   //Дженерики, метод для  возврата класса конкретного типа (например класс типа String)
        return Selenide.page(pageClass);          // c его помощью можно присваивать переменной все содержимое Боди респонса от сервера, а после проверять
    }

//    @BeforeAll
//    public static void setUpAll() {
//        Configuration.browserSize = "1280x800";
//        SelenideLogger.addListener("allure", new AllureSelenide());
//
//    }

//        @BeforeEach
//        public void setUp() {
//            open("https://sand_fleet.driveroo.com/login");
//        }

    }
