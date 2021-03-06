package com.driveroo.api.conditions;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StatusCodeCondition implements Condition{

    private final int expectedStatusCode;

    @Override
    public void check(Response response) {
        response.then().statusCode(expectedStatusCode);
    }

    @Override
    public String toString() {
        return "Status code is = "+expectedStatusCode;
    }
}
