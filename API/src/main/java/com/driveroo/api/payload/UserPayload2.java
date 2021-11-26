package com.driveroo.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.Generated;

@Getter
@Setter
@Accessors(fluent = true)
public @Data class UserPayload2 {
    @JsonProperty("password")
    private String password;
    @JsonProperty("device_token")
    private String deviceToken;
    @JsonProperty("device_type")
    private String deviceType;
    @JsonProperty("login")
    private String login;
    @JsonProperty("login_by")
    private String loginBy;
}