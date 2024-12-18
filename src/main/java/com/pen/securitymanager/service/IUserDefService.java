package com.pen.securitymanager.service;

import com.pen.securitymanager.db.model.AuthorizationDef;
import com.towpen.base.db.model.security.UserDef;
import com.towpen.base.security.model.TOpenLoginUser;
import com.towpen.base.security.model.TOpenSessionInstance;
import com.towpen.base.security.model.TOpenUserRoleAuth;

import java.util.List;

public interface IUserDefService {
	TOpenSessionInstance login(String userName,String password);

	TOpenSessionInstance createSessionInstance(UserDef userDef);

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
	TOpenUserRoleAuth findAuthorizationsByUserId(String userId);

	List<AuthorizationDef> findAuthorizations(String userId);

	TOpenLoginUser createLoginUserInformation(UserDef userDef);
}
