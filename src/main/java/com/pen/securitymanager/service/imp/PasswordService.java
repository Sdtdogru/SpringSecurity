package com.pen.securitymanager.service.imp;

import com.pen.securitymanager.model.DtoCompanyPasswordRules;
import com.pen.securitymanager.service.ICompanyPasswordRulesService;
import com.pen.securitymanager.service.IPasswordService;
import com.towpen.base.enums.model.LanguageType;
import com.towpen.base.security.ISessionInstanceService;
import com.towpen.model.PasswordModel;
import com.towpen.utils.PasswordUtil;
import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordService implements IPasswordService {
	@Autowired
	private ISessionInstanceService sessionInstanceService;

	@Autowired
	private ICompanyPasswordRulesService companyPasswordRulesService;

	@Override
	public boolean checkPassword(String password) {
		DtoCompanyPasswordRules passwordRules = companyPasswordRulesService.findCompanyPasswordRulesDto();
		List<Rule> rules = new ArrayList<>();

		if(passwordRules != null) {
			rules.add(new LengthRule(passwordRules.getMinLength(), passwordRules.getMaxLength()));
			rules.add(new WhitespaceRule());

			if(passwordRules.getDigitCount().compareTo((short) 0) > 0) {
				rules.add(new CharacterRule(EnglishCharacterData.Digit, passwordRules.getDigitCount()));
			}

			if(passwordRules.getLowerCaseCharacterCount().compareTo((short) 0) > 0) {
				rules.add(new CharacterRule(EnglishCharacterData.LowerCase, passwordRules.getLowerCaseCharacterCount()));
			}

			if(passwordRules.getUpperCaseCharacterCount().compareTo((short) 0) > 0) {
				rules.add(new CharacterRule(EnglishCharacterData.UpperCase, passwordRules.getUpperCaseCharacterCount()));
			}

			if(passwordRules.getSpecialCharacterCount().compareTo((short) 0) > 0) {
				rules.add(new CharacterRule(EnglishCharacterData.Special, passwordRules.getSpecialCharacterCount()));
			}
		}

		PasswordValidator validator = new PasswordValidator(rules);
		RuleResult result = validator.validate(new PasswordData(password));
		return result.isValid();
	}

	@Override
	public List<String> getPasswordRulesText(LanguageType languageType) {

		return companyPasswordRulesService.findCompanyPasswordRulesDto().getPasswordRulesText();
	}

	@Override
	public List<String> getPasswordRulesText() {
		return getPasswordRulesText(sessionInstanceService.getLanguage());
	}

	@Override
	public boolean isExpectedPassword(String salting, String encryeptedCurrentPassword, String checkPassword) {
		return PasswordUtil.isExpectedPassword(checkPassword.toCharArray(), salting, encryeptedCurrentPassword.toCharArray());
	}

	@Override
	public PasswordModel generatePasswordHash(String password) {
		return PasswordUtil.createHashPassword(password);
	}
}
