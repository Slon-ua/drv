package com.driveroo.api.test;

import com.driveroo.api.payload.UserPayload2;
import com.driveroo.api.payload.UserPayload;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class UserLogin {

    UserPayload userPayload = new UserPayload();

    private RequestSpecification setup(){
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .filters(new RequestLoggingFilter(LogDetail.BODY), new ResponseLoggingFilter(LogDetail.BODY), new ResponseLoggingFilter(LogDetail.STATUS),  new ResponseLoggingFilter(LogDetail.COOKIES))
                ;
    }

    private Response userLogin(UserPayload userPayload){
        // when
        return setup()
                .body(userPayload)

                .when()
                .post("/userApi/fleets/login")
                ;
    }

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
        userLogin(userPayload)
                .then()
                .statusCode(200)
                .body("name", containsString("Chuck Norris")
//                        , "token", Matchers.not(Matchers.isEmptyString())
                );
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
        userLogin(userPayload)
                .then()
                .statusCode(200)
                .body("success", Matchers.is(false)
                );
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
    }

}
