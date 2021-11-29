package com.driveroo.api.services;

import com.driveroo.api.assertions.AssertableResponse;
import com.driveroo.api.payload.UserPayload;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class UserApiService extends ApiService {

//     public RequestSpecification setup(){
//        return RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .filters(new RequestLoggingFilter(LogDetail.BODY), new ResponseLoggingFilter(LogDetail.BODY), new ResponseLoggingFilter(LogDetail.STATUS),  new ResponseLoggingFilter(LogDetail.COOKIES))
//                ;
//    }

    public AssertableResponse userLogin(UserPayload userPayload){
//        log.info("============================================================");
        log.info("============================================================ \n About to send request with data to Login user {}",userPayload);

        // when
        Response response = setup()
                .body(userPayload)

                .when()
                .post("/userApi/fleets/login");

        log.info("Successfully send requests");

        return new AssertableResponse(response);
    }
}
