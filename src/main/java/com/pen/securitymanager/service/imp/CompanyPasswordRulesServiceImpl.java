package com.pen.securitymanager.service.imp;

import com.pen.securitymanager.db.model.CompanyPasswordRules;
import com.pen.securitymanager.model.DtoCompanyPasswordRules;
import com.pen.securitymanager.model.DtoCompanyPasswordRulesIU;
import com.pen.securitymanager.repository.CompanyPasswordRulesRepository;
import com.pen.securitymanager.service.ICompanyPasswordRulesService;
import com.towpen.base.BaseDbServiceImp;
import com.towpen.base.enums.model.LanguageType;
import com.towpen.base.enums.model.TMessageType;
import com.towpen.base.exceptions.TOpenException;
import com.towpen.base.exceptions.models.TMessageFactory;
import com.towpen.base.internalization.TOpenMessageManager;
import com.towpen.base.restservice.model.TOpenMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyPasswordRulesServiceImpl extends BaseDbServiceImp<CompanyPasswordRulesRepository, CompanyPasswordRules> implements ICompanyPasswordRulesService {

	@Autowired
	private TOpenMessageManager jsureMessageManager;

	public DtoCompanyPasswordRules findCompanyPasswordRulesDto() {
		Optional<CompanyPasswordRules> passwordRulesDao = dao.findByCompanyCode(sessionInstanceService.getSelectedCompanyCode());
		LanguageType lang = sessionInstanceService.getLanguage();

		if (passwordRulesDao.isEmpty()) {
			throw new TOpenException(new TOpenMessage(TMessageType.COMPANY_PASS_RULES_RETRIEVE_ERROR_1031, TMessageFactory.ofStatic(sessionInstanceService.getSelectedCompanyCode())));
		}

		DtoCompanyPasswordRules passwordRulesDto = toDTO(passwordRulesDao.get());

		passwordRulesDto.setPasswordRulesText(createRulesList(passwordRulesDao.get(), lang));
		return passwordRulesDto;
	}

	@Override
	public DtoCompanyPasswordRules updateCompanyPasswordRulesDto(DtoCompanyPasswordRulesIU dtoCompanyPasswordRules) {
		Short sumRules = (short) (dtoCompanyPasswordRules.getDigitCount() + dtoCompanyPasswordRules.getLowerCaseCharacterCount() + dtoCompanyPasswordRules.getSpecialCharacterCount() + dtoCompanyPasswordRules.getUpperCaseCharacterCount());

		if (sumRules.compareTo(dtoCompanyPasswordRules.getMaxLength()) > 0) {
			throw new TOpenException(new TOpenMessage(TMessageType.PASS_NOT_MEET_REQUIREMENTS_1013));
		}

		CompanyPasswordRules daoCompanyPasswordRules = toDAOForInsert(dtoCompanyPasswordRules);
		Optional<CompanyPasswordRules> optionalByCompanyCode = dao.findByCompanyCode(sessionInstanceService.getSelectedCompanyCode());
		if (optionalByCompanyCode.isEmpty()) {
			daoCompanyPasswordRules.setCompanyCode(sessionInstanceService.getSelectedCompanyCode());
			save(daoCompanyPasswordRules);
		} else {
			daoCompanyPasswordRules.setId(optionalByCompanyCode.get().getId());
			update(daoCompanyPasswordRules);
		}
		DtoCompanyPasswordRules rules = toDTO(daoCompanyPasswordRules);
		rules.setPasswordRulesText(createRulesList(daoCompanyPasswordRules, sessionInstanceService.getLanguage()));
		return rules;
	}

	@Override
	public Class<?> getDTOClassForService() {
		return DtoCompanyPasswordRules.class;
	}

	private List<String> createRulesList(CompanyPasswordRules dao, LanguageType lang) {
		List<String> passwordRulesText = new ArrayList<>();
		passwordRulesText.add(jsureMessageManager.getMessageText(TMessageType.PASS_MIN_LENGTH_1022, lang, new Object[] { dao.getMinLength() }));
		passwordRulesText.add(jsureMessageManager.getMessageText(TMessageType.PASS_MAX_LENGTH_1023, lang, new Object[] { dao.getMaxLength() }));
		passwordRulesText.add(jsureMessageManager.getMessageText(TMessageType.PASS_CAN_NOT_HAVE_SPACE_1028, lang, new Object[] {}));
		passwordRulesText.add(jsureMessageManager.getMessageText(TMessageType.PASS_NOT_THE_SAME_1014, lang, new Object[] {}));

		if (dao.getDigitCount().compareTo((short) 0) > 0) {
			passwordRulesText.add(jsureMessageManager.getMessageText(TMessageType.PASS_MIN_DIGIT_LENGTH_1024, lang, new Object[] { dao.getDigitCount() }));
		}

		if (dao.getSpecialCharacterCount().compareTo((short) 0) > 0) {
			passwordRulesText.add(jsureMessageManager.getMessageText(TMessageType.PASS_MIN_SPEC_CHAR_LENGTH_1025, lang, new Object[] { dao.getSpecialCharacterCount() }));
		}

		if (dao.getLowerCaseCharacterCount().compareTo((short) 0) > 0) {
			passwordRulesText.add(jsureMessageManager.getMessageText(TMessageType.PASS_MIN_LOWER_CASE_LETTER_1026, lang, new Object[] { dao.getLowerCaseCharacterCount() }));
		}

		if (dao.getUpperCaseCharacterCount().compareTo((short) 0) > 0) {
			passwordRulesText.add(jsureMessageManager.getMessageText(TMessageType.PASS_MIN_CAP_CASE_LETTER_1027, lang, new Object[] { dao.getUpperCaseCharacterCount() }));
		}

		if (dao.getPasswordDifferentCount().compareTo((short) 0) > 0) {
			passwordRulesText.add(jsureMessageManager.getMessageText(TMessageType.PASSWORD_HISTORY_PROBLEM, lang, new Object[] { dao.getPasswordDifferentCount() }));
		}

		return passwordRulesText;
	}

}
