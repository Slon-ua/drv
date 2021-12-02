package com.driveroo.api.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@ToString
//@Accessors(fluent = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuthorizationResponse {
    @JsonProperty
    private String error;
    private Boolean success;
    private String error_code;
    //    private  String error_messages;
    private String id;
    private Map<Object, Object> _embedded;
    private Map<Object, Object> body;
    private Map<Object, Object> response;
    private String jParse = "_embedded.customer[0].lastName";

}
