package com.driveroo.api.test;

import com.driveroo.api.conditions.BodyFieldCondition;
import com.driveroo.api.conditions.Conditions;
import com.driveroo.api.conditions.StatusCodeCondition;
import com.driveroo.api.payload.UserPayload2;
import com.driveroo.api.payload.UserPayload;
import com.driveroo.api.services.ApiService;
import com.driveroo.api.services.UserApiService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.RestAssured;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.hc.core5.http.HttpStatus;
import org.assertj.core.api.BDDAssertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.driveroo.api.conditions.Conditions.statusCode;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class UserLogin extends ApiService {

    UserPayload userPayload = new UserPayload();
//    ApiService apiService = new ApiService();

    private final UserApiService userApiService = new UserApiService();

//    private final ApiService apiService = new ApiService();

//    private RequestSpecification setup(){
//        return RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .filters(new RequestLoggingFilter(LogDetail.BODY), new ResponseLoggingFilter(LogDetail.BODY), new ResponseLoggingFilter(LogDetail.STATUS),  new ResponseLoggingFilter(LogDetail.COOKIES))
//                ;
//    }
//
//    private Response userLogin(UserPayload userPayload){
//        // when
//        return setup()
//                .body(userPayload)
//
//                .when()
//                .post("/userApi/fleets/login")
//                ;
//    }
//
    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI ="https://sandbox.driveroo.com";
    }

    @Test
    void userCanLoginWithValidCredentials() {
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
        System.out.println("##################### = "+LoginToken);

//                .then()
//                .statusCode(200)
//                .body("name", containsString("Chuck Norris")
//                        , "token", Matchers.not(Matchers.isEmptyString())
//                );
    }

    @Test
    void userCantLoginWithInvalidPass() {
        // given
        String randomPass = RandomStringUtils.randomNumeric(6);

        userPayload
                .login("sergeyk+0")
                .password(randomPass)
                .deviceToken("74e879b8-eac0-46df-aa32-16bddadcebff")
                .deviceType("web")
                .loginBy("manual")
        ;

        // when + then
//        String error = userApiService.userLogin(userPayload)
//                .shouldHave(statusCode(200))
//                .shouldHave(Conditions.bodyField("error", not(isEmptyString())))
//                .getValue("error");
//        System.out.println(error);

        UserAuthorizationResponse pojo = userApiService.userLogin(userPayload)
                .shouldHave(statusCode(200))
//                .shouldHave(Conditions.bodyField("error", not(isEmptyString())))
                .shouldHave(Conditions.bodyField("error", not(is(emptyString()))))
                .asPojo(UserAuthorizationResponse.class);

        System.out.println("### 1 error = "+pojo.getError());

        assertThat(pojo.getError()).isNotEmpty();

        System.out.println("### 2 error_code = "+pojo.getError_code());
        System.out.println("### 2.1 Success = "+pojo.getSuccess());


        BDDAssertions.then(pojo.getError_code()).isNotEmpty();

        System.out.println("### 3 body = "+pojo.getBody());
        System.out.println("### 4 response = "+pojo.getResponse());

        Map<String, String> allCookies = userApiService.userLogin(userPayload)
                .getAllCookies("");
        System.out.println("### 5 = "+allCookies);

        Object resp1 = userApiService.userLogin(userPayload)
                .getValueLikeJSON("$");
        System.out.println("### 6 = "+resp1);

        String resp2 = userApiService.userLogin(userPayload)
                .getValueLikeString("error_code");
        System.out.println("### 7 = "+resp2);

        String Cookies_laravel_session = userApiService.userLogin(userPayload)
                .getCookiesByName("", "laravel_session");
        System.out.println("### 8 getCookiesByName 'laravel_session' = "+Cookies_laravel_session);

//                .then()
//                .statusCode(200)
//                .body("success", Matchers.is(false)
//                );
    }

    @Test
    void userCanLoginWithValidCredentialsLombok() {
        UserPayload2 userPayload2 = new UserPayload2();

        userPayload2
                .login("sergeyk+0")
                .password("123456")
                .deviceToken("74e879b8-eac0-46df-aa32-16bddadcebff")
                .deviceType("web")
                .loginBy("manual")
        ;
        // expect
        setup()
                .body(userPayload2)

                .when()
                .post("/userApi/fleets/login")

                .then()
                .statusCode(200)
                .body("name", containsString("Chuck Norris")
                );

//        loginToken(userPayload2);
//        System.out.println("==========");
//        loginToken(userPayload2);
//        loginToken(userPayload2);
    }

}

@Getter
@ToString
//@Accessors(fluent = true)
@JsonIgnoreProperties(ignoreUnknown = true)
class UserAuthorizationResponse{
    @JsonProperty
    private  String error;
    private  Boolean success;
    private  String error_code;
//    private  String error_messages;
    private  String id;
    private Map<Object, Object> _embedded;
    private Map<Object, Object> body;
    private Map<Object, Object> response;
    private  String jParse="_embedded.customer[0].lastName";

}