package com.driveroo.api.services;

import com.driveroo.api.assertions.AssertableResponse;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import com.driveroo.api.payload.UserPayload2;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
//@Slf4j
//@RequiredArgsConstructor
//@ToString
public class ApiService {

//    public final  UserPayload userPayload = new UserPayload();
    protected static String LoginToken ;  // в эту перерменную через фуню loginToken() записываем Токен или Куки

    protected RequestSpecification setup(){
//        System.out.println("loginToken = "+LoginToken);
          return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
//                  .header("Authorization","Bearer "+LoginToken)
//                  .cookie("@@@@#1","121")
//                  .cookie("@@@@#2","122")
//                  .cookie("@@@@#3","122")
//                  .cookies("Set-Cookie","Bearer4 : 124")
//                  .cookies("Set-Cookie","Bearer5 : 125")
//                  .cookies("Set-Cookie","Bearer6 : 126")
//                .filters(
//                        new RequestLoggingFilter(LogDetail.BODY)
//                        ,new RequestLoggingFilter(LogDetail.COOKIES)
//                        ,new ResponseLoggingFilter(LogDetail.BODY)
//                        ,new ResponseLoggingFilter(LogDetail.STATUS)
//                        ,new ResponseLoggingFilter(LogDetail.COOKIES)
//                        )
                  ;
    }

    public void   loginToken(UserPayload2 userPayload){
//        UserPayload userPayload = new UserPayload();
//
//        userPayload
//                .login("sergeyk+0")
//                .password("123456")
//                .deviceToken("74e879b8-eac0-46df-aa32-16bddadcebff")
//                .deviceType("web")
//                .loginBy("manual")
//        ;

        Response response = setup()
                .body(userPayload)

                .when()
                .post("/userApi/fleets/login");

//        Map<Object, Object>  resp1 = new AssertableResponse(response).getValueLikeJSON("$");
//        Map<Object, Object> token = new AssertableResponse(response).getValueLikeJSON("$");
        String qwe = response.jsonPath().getString("token");
//        loginToken = new AssertableResponse(response).getValueLikeString("$");
        LoginToken = response.jsonPath().getString("token");
        Cookies CookieName = response.getDetailedCookies();
        String CookieByName = response.getCookie("laravel_session");
//        loginToken = "1";

//        System.out.println("### 1 = "+response);
//        System.out.println("### 2 = "+resp1);
        System.out.println("### 3 = "+qwe);
//        System.out.println("### 4 = "+token);
        System.out.println("### 5 = "+LoginToken);
        System.out.println("### 6 = "+CookieName);
        System.out.println("### 7 = 'laravel_session':"+CookieByName);
//        return new AssertableResponse(response);
    }
}
