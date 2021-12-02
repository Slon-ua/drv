//***** этот класс создается для красоты и краткости кода, при вызове сравнения респонса согласно разных Кондишенов из файла/класса userApiService.java, с которого стартуют тесты
package com.driveroo.api.conditions;

import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import org.hamcrest.Matcher;

@UtilityClass                    // это означает, что в этом классе можем писать только static методы
public class Conditions {

    @Step
    public static StatusCodeCondition statusCode(int statusCode){
        return new StatusCodeCondition(statusCode);
    }

    @Step
    public static BodyFieldCondition bodyField(String jsonPath, Matcher matcher) { //принимает 2 пераметра: 1) строка - jsonPath ,2) matcher - искомый текст в jsonPath
        return new BodyFieldCondition(jsonPath, matcher);
    }
}
