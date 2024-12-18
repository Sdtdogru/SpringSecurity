package com.pen.securitymanager.auth.model;

import com.towpen.base.restservice.model.DtoBaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest extends DtoBaseModel {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;


}
