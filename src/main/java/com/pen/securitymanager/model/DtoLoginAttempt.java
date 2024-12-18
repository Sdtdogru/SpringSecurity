package com.pen.securitymanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.towpen.base.restservice.model.DtoEntityModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DtoLoginAttempt extends DtoEntityModel {

	private static final long serialVersionUID = 1L;

	private String userName;
	@JsonProperty("isLoginSuccess")
	private boolean isLoginSuccess;

	private String ipAddress;

	private String browser;

	private String serverName;

	private Date createTime;

	private String errorMessage;

	private String sessionId;
}
