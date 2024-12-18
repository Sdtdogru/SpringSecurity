package com.pen.securitymanager.service.imp;

import com.pen.securitymanager.db.model.UserDefCompanyAuth;
import com.pen.securitymanager.model.DtoUserDefCompanyAuth;
import com.pen.securitymanager.model.DtoUserDefCompanyAuthIU;
import com.pen.securitymanager.repository.UserDefCompanyAuthRepository;
import com.pen.securitymanager.repository.UserDefRepository;
import com.pen.securitymanager.service.ICompanyService;
import com.pen.securitymanager.service.IUserDefCompanyAuthLogService;
import com.pen.securitymanager.service.IUserDefCompanyAuthService;
import com.towpen.base.db.model.security.Company;
import com.towpen.base.db.model.security.UserDef;
import com.towpen.base.enums.model.TMessageType;
import com.towpen.base.exceptions.TOpenException;
import com.towpen.base.exceptions.models.TMessageFactory;
import com.towpen.base.restservice.model.DtoBaseModel;
import com.towpen.base.restservice.model.TOpenMessage;
import com.towpen.base.security.BaseDbServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional(propagation = Propagation.REQUIRED)
@Slf4j
public class UserDefCompanyAuthServiceImpl extends BaseDbServiceImp<UserDefCompanyAuthRepository, UserDefCompanyAuth> implements IUserDefCompanyAuthService {
	@Autowired
	private ICompanyService companyService;

	@Autowired
	private UserDefRepository userService;

	@Autowired
	private IUserDefCompanyAuthLogService logService;

	@Override
	public Class<?> getDTOClassForService() {
		return DtoUserDefCompanyAuth.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <D extends DtoBaseModel> D toDTO(UserDefCompanyAuth t) {
		DtoUserDefCompanyAuth result = super.toDTO(t);
		result.setCompany(companyService.toDTO(t.getCompany()));

		return (D) result;
	}

	@Override
	public DtoUserDefCompanyAuth saveUserDefCompanyAuth(DtoUserDefCompanyAuthIU input) {
		Optional<Company> optCompany = companyService.findById(input.getCompanyId());
		if (optCompany.isEmpty()) {
			throw new TOpenException(new TOpenMessage(TMessageType.NOT_EXISTS_IN_THE_RECORDS_1006, TMessageFactory.ofStatic(input.getCompanyId())));
		}

		Optional<UserDef> optUserDef = userService.findById(input.getUserDefId());
		if (optUserDef.isEmpty()) {
			throw new TOpenException(new TOpenMessage(TMessageType.NOT_EXISTS_IN_THE_RECORDS_1006, TMessageFactory.ofStatic(input.getUserDefId())));
		}

		Optional<UserDefCompanyAuth> optAuth = dao.findByCompanyIdAndUserDefId(input.getCompanyId(), input.getUserDefId());
		if (optAuth.isPresent()) {
			throw new TOpenException(new TOpenMessage(TMessageType.ALREADY_EXISTS_1004, TMessageFactory.ofBundle("company.list"), TMessageFactory.ofStatic(optCompany.get().getCompanyName())));
		}

		UserDefCompanyAuth entity = toDAOForInsert(input);
		entity.setCompany(optCompany.get());
		entity.setUserDef(optUserDef.get());
		save(entity);
		logService.addUserDefCompanyAuthLog(entity.getCompany(), entity.getUserDef(), "ADD");
		return toDTO(entity);
	}

	@Override
	public DtoUserDefCompanyAuth deleteUserDefCompanyAuth(String id) {
		Optional<UserDefCompanyAuth> optAuth = dao.findById(id);
		if (optAuth.isEmpty()) {
			throw new TOpenException(new TOpenMessage(TMessageType.NOT_EXISTS_IN_THE_RECORDS_1006, TMessageFactory.ofStatic(id)));
		}
		logService.addUserDefCompanyAuthLog(optAuth.get().getCompany(), optAuth.get().getUserDef(), "DELETE");
		delete(optAuth.get());
		return toDTO(optAuth.get());
	}

	@Override
	public List<DtoUserDefCompanyAuth> findAuthDefListByParams(String companyId, String userDefId) {
		return toDTOList(dao.findByParams(companyId, userDefId));
	}
}
