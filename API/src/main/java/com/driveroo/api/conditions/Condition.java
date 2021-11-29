package com.driveroo.api.conditions;

import io.restassured.response.Response;

public interface Condition {  // с помощью него проверяються разные условия проверок
    void check(Response response); // этот метод получает респонс от класса AssertableResponse
}
