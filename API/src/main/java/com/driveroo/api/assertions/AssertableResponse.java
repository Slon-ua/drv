package com.driveroo.api.assertions;

import com.driveroo.api.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AssertableResponse { //декоратор

    private final Response response;

    @Step ("API response should have {condition} ")
    public AssertableResponse shouldHave(Condition condition){
        log.info("About to check condition [{}]",condition);
        condition. check(response);
        return this;
    }

    public String getValue(String jsonPath){
        return response.jsonPath().getString(jsonPath);
    }

    public <T> T asPojo(Class<T> tClass){
        return response.as(tClass);
    }

    public Map<String, String> getAllCookies(String s){ //Выведит все куки из ответа от сервера
        return response.getCookies();
    }

    public String getCookiesByName(String jsonPath, String name){  //Выведит куки по имени,  из ответа от сервера
        return response.getCookie(name);
    }





    public String getValueLikeString(String jsonPath) {  //метод присвоения/вывода значения элементов боди к переменной
/*
        log.info("-----------1-------------"+response.jsonPath().getMap("_embedded.customer[0]"));
       // log.info("-----------2-------------"+response.jsonPath().getMap(jsonPath));
        log.info("-----------3-------------"+response.jsonPath().get("_embedded.customer[0].lastName"));
        log.info("-----------3-------------"+response.jsonPath().getString("_embedded.customer[0].lastName"));
        log.info("-----------4-------------"+response.jsonPath().getString(jsonPath));
        log.info("-----------5-------------"+jsonPath);  //.assertThat().body("data.leagueId", equalTo(35)) RestAssured.registerParser
     //   log.info("-----------6-------------"+response.then().assertThat().body("_embedded.customer[0].lastName",not(isEmptyString())));
*/
        return  response.jsonPath().getString(jsonPath);  // присвоит переменной значение либо всего JSON, либо конкретного значения, если его передать в функцию -> jsonPath (id) из JSON респона с сервера (5ceda0dcee11cb0001002ddb)
    }

    public Map<Object, Object> getValueLikeMap(String jsonPath) {  //метод присвоения/вывода значения элементов боди к переменной

//        log.info("-----------1-------------"+response.jsonPath().getMap("_token"));
//        log.info("-----------2-------------"+response.jsonPath().getMap(jsonPath));
//        log.info("-----------3-------------"+response.jsonPath().get("_embedded.customer[0].lastName"));
//        log.info("-----------4-------------"+response.jsonPath().getString(jsonPath));
//        log.info("-----------4-------------"+response.jsonPath().getString("token"));
//        log.info("-----------5-------------"+response.jsonPath().getJsonObject(jsonPath));
////        log.info("-----------6-------------"+response.jsonPath().getList(jsonPath));
//        log.info("-----------7-------------"+response.jsonPath().get(jsonPath));
//        log.info("-----------10-------------"+jsonPath);

        return  response.jsonPath().getMap(jsonPath);  // присвоит переменной значение либо всего JSON, либо конкретного значения, если его передать в функцию -> jsonPath (id) из JSON респона с сервера (5ceda0dcee11cb0001002ddb)
    }

    public Object getValueLikeJSON(String jsonPath) {  //метод присвоения/вывода значения элементов боди к переменной

//        log.info("-----------1-------------"+response.jsonPath().getMap("_token"));
        log.info("-----------2-------------"+response.jsonPath().getMap(jsonPath));
//        log.info("-----------3-------------"+response.jsonPath().get("_embedded.customer[0].lastName"));
        log.info("-----------4-------------"+response.jsonPath().getString(jsonPath));
//        log.info("-----------4-------------"+response.jsonPath().getString("token"));
        log.info("-----------5-------------"+response.jsonPath().getJsonObject(jsonPath));
//        log.info("-----------6-------------"+response.jsonPath().getList(jsonPath));
        log.info("-----------7-------------"+response.jsonPath().get(jsonPath));
//        log.info("-----------8-------------"+response.jsonPath().getObject());
//        log.info("-----------9-------------"+response.jsonPath().getObject(jsonPath, MyObject[].class));
//        log.info("-----------10-------------"+jsonPath);

        return  response.jsonPath().getJsonObject(jsonPath);  // присвоит переменной значение либо всего JSON, либо конкретного значения, если его передать в функцию -> jsonPath (id) из JSON респона с сервера (5ceda0dcee11cb0001002ddb)
    }
    public List<Object> getValueLikeList(String jsonPath) {  //метод присвоения/вывода значения элементов боди к переменной

//        log.info("-----------1-------------"+response.jsonPath().getMap("_token"));
//        log.info("-----------2-------------"+response.jsonPath().getMap(jsonPath));
//        log.info("-----------3-------------"+response.jsonPath().get("_embedded.customer[0].lastName"));
//        log.info("-----------4-------------"+response.jsonPath().getString(jsonPath));
//        log.info("-----------4-------------"+response.jsonPath().getString("token"));
//        log.info("-----------5-------------"+response.jsonPath().getJsonObject(jsonPath));
////        log.info("-----------6-------------"+response.jsonPath().getList(jsonPath));
//        log.info("-----------7-------------"+response.jsonPath().get(jsonPath));
//        log.info("-----------10-------------"+jsonPath);

        return  response.jsonPath().getList(jsonPath);  // присвоит переменной значение либо всего JSON, либо конкретного значения, если его передать в функцию -> jsonPath (id) из JSON респона с сервера (5ceda0dcee11cb0001002ddb)
    }

    public int getValueLikeInt(String jsonPath) {  //метод присвоения/вывода значения элементов боди к переменной
        return  response.jsonPath().getInt(jsonPath);  // присвоит переменной значение либо всего JSON, либо конкретного значения, если его передать в функцию -> jsonPath (id) из JSON респона с сервера (5ceda0dcee11cb0001002ddb)
    }

    public boolean getValueLikeBoolean(String jsonPath) {  //метод присвоения/вывода значения элементов боди к переменной
        return  response.jsonPath().getBoolean(jsonPath);  // присвоит переменной значение либо всего JSON, либо конкретного значения, если его передать в функцию -> jsonPath (id) из JSON респона с сервера (5ceda0dcee11cb0001002ddb)
    }

}
