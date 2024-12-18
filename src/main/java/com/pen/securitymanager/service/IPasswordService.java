package com.pen.securitymanager.service;

import com.towpen.base.enums.model.LanguageType;
import com.towpen.model.PasswordModel;

import java.util.List;

public interface IPasswordService {
	boolean checkPassword(String password);

	List<String> getPasswordRulesText(LanguageType languageType);

	List<String> getPasswordRulesText();

	PasswordModel generatePasswordHash(String password);

	boolean isExpectedPassword(String salting, String encryeptedCurrentPassword, String newPassword);
}
