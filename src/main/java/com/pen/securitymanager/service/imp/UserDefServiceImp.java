package com.pen.securitymanager.service.imp;

import com.pen.securitymanager.db.model.AuthorizationDef;
import com.pen.securitymanager.db.model.LoginAttempt;
import com.pen.securitymanager.db.model.UserDefLoginLog;
import com.pen.securitymanager.model.DtoUserDef;
import com.pen.securitymanager.model.DtoUserDefCompanyAuth;
import com.pen.securitymanager.repository.*;
import com.pen.securitymanager.service.*;
import com.towpen.base.security.BaseDbServiceImp;
import com.towpen.base.db.model.security.Company;
import com.towpen.base.db.model.security.UserDef;
import com.towpen.base.db.model.security.UserDefAccess;
import com.towpen.base.enums.model.AccessType;
import com.towpen.base.enums.model.LanguageType;
import com.towpen.base.enums.model.TMessageType;
import com.towpen.base.exceptions.TOpenException;
import com.towpen.base.exceptions.rest.ApiErrorBeanController;
import com.towpen.base.restservice.model.TOpenMessage;
import com.towpen.base.security.model.TOpenCompanyInfo;
import com.towpen.base.security.model.TOpenLoginUser;
import com.towpen.base.security.model.TOpenSessionInstance;
import com.towpen.base.security.model.TOpenUserRoleAuth;
import com.towpen.utils.PasswordUtil;
import com.towpen.utils.TObjectUtils;
import com.towpen.utils.TStringUtil;
import com.towpen.utils.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserDefServiceImp extends BaseDbServiceImp <UserDefRepository, UserDef> implements IUserDefService {

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private SessionManager sessionManager;

	@Autowired
	private IUserDefAccessService userDefAccessService;

	@Autowired
	private IUserDefPasswordHistoryService userDefPasswordHistoryService;

	@Autowired
	private IPasswordService passwordService;

	@Autowired
	private ICompanyPasswordRulesService companyPasswordRuleService;

	@Autowired
	private ILoginAttemptService loginAttemptService;

	@Autowired
	private ApiErrorBeanController apiBeanController;

	@Autowired
	private IUserDefLoginLogService userLoginLogService;

	@Autowired
	private AuthorizationDefRepository authorizationDefService;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private RoleAuthorizationRepository roleAuthorizationRepository;

	@Autowired
	private RoleDefRepository roleDefRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private IUserDefCompanyAuthService userCompanyAuthService;

	@Autowired
	private ILdapService ldapService;

	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor = { TOpenException.class })
	@Override
	public TOpenSessionInstance login(String userName, String password) {
		boolean success = false;
		UserDef userDef = null;
		TOpenException tOpenException = null;
		boolean usernameAndPasswordInValid = TStringUtil.isNull(userName) || TStringUtil.isNull(password);
		String sessionId = null;
		try {

		if (usernameAndPasswordInValid) {
			throw new TOpenException(new TOpenMessage(TMessageType.USERNAME_PASSWORD_REQUIRED_1009));
		}
		Optional<UserDef> userDefOptional = findByUserName(userName);
		if (userDefOptional.isEmpty()) {
			throw new TOpenException(new TOpenMessage(TMessageType.USERNAME_PASSWORD_ERROR_1010));
		}
		userDef = userDefOptional.get();

		userDef = userDefOptional.get();

		Optional<UserDefAccess> userDefAccessOptional = userDefAccessService.findByUserDef(userDef);

		if (userDefAccessOptional.isEmpty()) {
			throw new TOpenException(new TOpenMessage(TMessageType.USERNAME_PASSWORD_ERROR_1010));
		}
		UserDefAccess userDefAccess = userDefAccessOptional.get();
		validateUserAccess(userDefAccess);
		boolean passwordValid = validateUserNameAndPassword(password, userDefAccess, userDef);
		if (!passwordValid) {
			throw new TOpenException(new TOpenMessage(TMessageType.USERNAME_PASSWORD_ERROR_1010));
		}
			success = true;

			TOpenSessionInstance sessionInstance = createSessionInstance(userDef);
			sessionId = sessionInstance.getUserInformation().getSessionId();
			return sessionInstance;
		} catch (TOpenException e) {
			success = false;
			tOpenException = e;
			throw e;
		} finally {
			if (!usernameAndPasswordInValid) {
				LoginAttempt logLoginAttempt = logLoginAttempt(userName, success, tOpenException, sessionId);
				if (success) {
					logUserDefLogging(logLoginAttempt, userDef);
				}
			}
		}
	}


	@Override
	public TOpenSessionInstance createSessionInstance(UserDef userDef) {
		TOpenLoginUser userSession = createLoginUserInformation(userDef);

		//userSession.setDynamicLoginParameters(additionalSessionInfos(userDef));

		TOpenUserRoleAuth auth = findAuthorizationsByUserId(userDef.getId());//TODO HARUN SONRA CACHE KONACAK
		List<String> roles = new ArrayList<>();// role listesini okusun ancak sonra cacheden alacak
		Map<String, TOpenCompanyInfo> companies = new HashMap<>();

		Optional<Company> optComp = companyRepository.findByCompanyCode(userDef.getCompanyCode());
		if (optComp.isPresent()) {
			companies.put(optComp.get().getCompanyCode(), new TOpenCompanyInfo(true, optComp.get().getCompanyCode(), optComp.get().getCompanyName()));
		}
		List<DtoUserDefCompanyAuth> authCompanyList = userCompanyAuthService.findAuthDefListByParams(null, userDef.getId());
		if (!authCompanyList.isEmpty()) {
			for (DtoUserDefCompanyAuth item : authCompanyList) {
				if (companies.computeIfAbsent(item.getCompany().getCompanyCode(), k -> companies.get(item.getCompany().getCompanyCode())) == null) {
					companies.put(item.getCompany().getCompanyCode(), new TOpenCompanyInfo(false, item.getCompany().getCompanyCode(), item.getCompany().getCompanyName()));
				}
			}
		}
		String sessionId = sessionManager.createSessionId();
		userSession.setSessionId(sessionId);
//		if(userDef.getUserType().isAddIpAddressToToken()) {
//			userSession.setIpAddress(WebUtils.getRemoteAddress(httpServletRequest));
//		}
		userSession.setSupportedCompanies(new ArrayList<>(companies.values()));
		return new TOpenSessionInstance(userSession, roles);
	}
//	private HashMap<String, Object> additionalSessionInfos(UserDef userDef) {
//		if (userDef.getUserDefGenericIdType() != null) {
//			if (userDef.getUserDefGenericIdType() == UserDefGenericIdType.AGENCY_SALES_MEMBER_ID) {
//				return loadForAgencySalesMember(userDef.getGenericIdentifier());
//			}
//
//		}
//		return null;
//	}

//	private HashMap<String, Object> loadForAgencySalesMember(String id) {
//		HashMap<String, Object> loginStatics = new HashMap<>();
//		Optional<AgencySalesMember> agencySalesMemberOpt = agencySalesMemberService.findAgencySalesMemberById(id);
//		if (agencySalesMemberOpt.isEmpty()) {
//			return loginStatics;
//		}
//	/*	Optional<AgencySalesMember> agencyTechnicalSalesMember = agencySalesMemberService.findAgencyTechnicalSalesMemberById(id);
//		if(agencyTechnicalSalesMember.isEmpty()) {
//			return loginStatics;
//		}*/
//		AgencySalesMember agencySalesMember = agencySalesMemberOpt.get();
//		loginStatics.put(JCoreLoginStatics.AGENCY_ID, agencySalesMember.getAgency().getId());
//		loginStatics.put(JCoreLoginStatics.SALES_MEMBER_ID, agencySalesMember.getId());
//		//loginStatics.put(JCoreLoginStatics.AGENCY_TECHNICAL_SALES_MEMBER_ID, agencyTechnicalSalesMember.get().getId());
//		return loginStatics;
//	}
	@Override
	public TOpenUserRoleAuth findAuthorizationsByUserId(String userId) {
		TOpenUserRoleAuth userAuthObj = new TOpenUserRoleAuth();
		List<AuthorizationDef> auths = findAuthorizations(userId);

		if (!TObjectUtils.isEmpty(auths)) {
			userAuthObj.setAuthorizations(new ArrayList<>());
			for (AuthorizationDef authorizationDef : auths) {
				TOpenUserRoleAuth.TOpenAuth auth = new TOpenUserRoleAuth.TOpenAuth();
				auth.setCode(authorizationDef.getShortCode());
				auth.setName(authorizationDef.getName());
				userAuthObj.getAuthorizations().add(auth);
			}
		}
		//userAuthObj.setRestrictions(createUserRestrictions(userId));
		return userAuthObj;
	}

	@Override
	public List<AuthorizationDef> findAuthorizations(String userId) {
		return authorizationDefService.findAuthorizationDefListByUser(userId);
	}
	
	@Override
	public TOpenLoginUser createLoginUserInformation(UserDef userDef) {
		TOpenLoginUser loginUser = new TOpenLoginUser();
		loginUser.setSelectedCompanyCode(userDef.getCompanyCode());
		loginUser.setDisplayName(userDef.getUserDisplayName());
		loginUser.setUserId(userDef.getId());
		loginUser.setUserName(userDef.getUserName());
		loginUser.setLanguageVal(LanguageType.getLanguageFromValue(userDef.getLanguageVal() != null ? userDef.getLanguageVal().getValue() : null));

		return loginUser;
	}
	
	private boolean validateUserNameAndPassword(String password, UserDefAccess userDefAccess, UserDef userDef) {
		if (userDefAccess.getAccessType() == AccessType.INTERNAL) {
			return PasswordUtil.isExpectedPassword(password.toCharArray(), userDefAccess.getSaltKey(), userDefAccess.getPasswordHash().toCharArray());
		} else {
			return ldapService.authenticateReturnBoolean(userDef.getUserName(), password);
		}
	}

private void validateUserAccess(UserDefAccess userDefAccess) {
	if (Boolean.FALSE == userDefAccess.getCanLogin()) {
		throw new TOpenException(new TOpenMessage(TMessageType.USERNAME_CANNOT_LOGIN_1011));
	}
	if (Boolean.TRUE == userDefAccess.getHasIpRestriction() && TStringUtil.hasText(userDefAccess.getIpRestriction()) && !checkIpAdress(userDefAccess.getIpRestriction())) {
		throw new TOpenException(new TOpenMessage(TMessageType.DONT_MATCH_IP_ADDRESS_1034));
	}
}

private boolean checkIpAdress(String ipAddress) {

	List<String> ipList = Arrays.asList(ipAddress.trim().split(";"));
	for (String ip : ipList) {
		if (WebUtils.getRemoteAddress(httpServletRequest).equals(ip.trim())) {
			return true;
		}
	}
	return false;
}
	private UserDefLoginLog logUserDefLogging(LoginAttempt attempt, UserDef userDef) {
		UserDefLoginLog userDefLoginLog = new UserDefLoginLog();
		userDefLoginLog.setLoginAttempt(attempt);
		userDefLoginLog.setLoginTime(Calendar.getInstance().getTime());

		userDefLoginLog.setUserDef(userDef);
		userLoginLogService.save(userDefLoginLog);
		return userDefLoginLog;
	}
	private LoginAttempt logLoginAttempt(String userName, boolean success, TOpenException ex, String sessionId) {
		LoginAttempt attempt = new LoginAttempt();
		attempt.setLoginSuccess(success);
		attempt.setUserName(TStringUtil.getDefault(userName, "no-user"));
		attempt.setBrowser(WebUtils.getBrowser(httpServletRequest));
		attempt.setIpAddress(WebUtils.getRemoteAddress(httpServletRequest));
		attempt.setServerName(WebUtils.getSystemHostName());
		if (success && !TStringUtil.isNullOrBlank(sessionId)) {
			attempt.setSessionId(sessionId);
		}
		if (!success && ex != null) {
			attempt.setErrorMessage(apiBeanController.convertToApiMessagesToText(ex.getMessages()));
		}
		loginAttemptService.save(attempt);
		return attempt;
	}

	private Optional<UserDef> findByUserName(String userName) {
		return dao.findByUserName(userName);
	}

	@Override
	public Class<?> getDTOClassForService() {
		return DtoUserDef.class;
	}
}
