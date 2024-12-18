package com.pen.securitymanager.service.imp;

import com.pen.securitymanager.config.LdapSecurityConfig;
import com.pen.securitymanager.service.ILdapService;
import com.pen.securitymanager.statics.LdapOptionStatics;
import com.towpen.base.enums.model.TMessageType;
import com.towpen.base.exceptions.TOpenException;
import com.towpen.base.restservice.model.TOpenMessage;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
@Slf4j
public class LdapServiceImpl implements ILdapService {
	@Value("${ldap.partitionSuffix}")
	private String ldapPartitionSuffix;

	@Autowired
	private LdapTemplate ldapTemplate;

	@Autowired
	private LdapSecurityConfig securityConfig;

	@Override
	public List<String> searchLdapUserWithSpecifiedLdapOptions(String searchInput, String searchField,
			String resultField) {

		return ldapTemplate.search(query().where(searchField).is(searchInput),
				(AttributesMapper<String>) attrs -> (String) attrs.get(resultField).get());

	}

	@Override
	public String authenticate(String username, String password) {
		if (authenticateReturnBoolean(username, password)) {
			return "Ldap Authentication Success";
		} else {
			throw new TOpenException(new TOpenMessage(TMessageType.USERNAME_PASSWORD_ERROR_1010));
		}
	}

	@Override
	public boolean authenticateReturnBoolean(String username, String password) {
		if (password.isBlank()) {
			throw new TOpenException(new TOpenMessage(TMessageType.USERNAME_PASSWORD_REQUIRED_1009));
		}
		try {
			LdapContextSource contextSource = securityConfig.contextSource();
			contextSource.afterPropertiesSet();
			boolean authenticated = ldapTemplate.authenticate("", "("+ LdapOptionStatics.SAMACCOUNTNAME +"=" + username + ")", password);

			if (!authenticated) {
				throw new TOpenException(new TOpenMessage(TMessageType.USERNAME_PASSWORD_ERROR_1010));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
