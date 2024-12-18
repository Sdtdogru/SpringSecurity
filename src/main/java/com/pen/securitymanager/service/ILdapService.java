package com.pen.securitymanager.service;

import java.util.List;

public interface ILdapService {
	List<String> searchLdapUserWithSpecifiedLdapOptions(String searchInput, String searchField, String resultField);

	String authenticate(String name, String username);

	boolean authenticateReturnBoolean(String name, String username);
}
