package com.driveroo.api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.annotation.Generated;

@Getter
@Setter
@ToString
@Accessors(fluent = true)
@Generated("com.robohorse.robopojogenerator")
public class UserPayload {

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