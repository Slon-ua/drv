package com.driveroo.api.test;

import com.driveroo.api.ProjectConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.getProperty;
import static java.util.Collections.singletonMap;

public class BaseTest {

    private static final String ENV_VARIABLE_KEY = "env";  //  инициал статическую файнл переменную, котоой присаиваем имя проперти из  onfig.properties  и дальше в коде используем ее

    @BeforeAll
    static void beforeAll() {
//        RestAssured.baseURI ="https://sandbox.driveroo.com";
//        RestAssured.baseURI = System.getProperty("Host","https://sandbox.driveroo.com");

        Map myVars = singletonMap(ENV_VARIABLE_KEY, getProperty(ENV_VARIABLE_KEY));

        if (myVars.get("env")==null) {
             myVars = singletonMap(ENV_VARIABLE_KEY, "sandbox");
        }

        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, myVars, System.getProperties());  // вызов пропертей енвайрмента и его переменных (env.host)
        RestAssured.baseURI = config.host();  //вызов конкретной проперти
    }
}