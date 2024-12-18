package com.pen.securitymanager.model;

import com.towpen.base.restservice.model.DtoEntityModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DtoCompanyPasswordRules extends DtoEntityModel {
	private static final long serialVersionUID = 1L;

	private Short minLength;

	private Short maxLength;

	private Short specialCharacterCount;

	private Short digitCount;

	private Short lowerCaseCharacterCount;

	private Short upperCaseCharacterCount;

	private Short expirationPeriod;

	private Short passwordDifferentCount;

	private List<String> passwordRulesText;
}
