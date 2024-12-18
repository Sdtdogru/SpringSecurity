package com.pen.securitymanager.service.imp;

import com.pen.securitymanager.db.model.LoginAttempt;
import com.pen.securitymanager.model.DtoLoginAttempt;
import com.pen.securitymanager.repository.LoginAttemptRepository;
import com.pen.securitymanager.service.ILoginAttemptService;
import com.towpen.base.security.BaseDbServiceImp;
import com.towpen.base.enums.model.TMessageType;
import com.towpen.base.exceptions.TOpenException;
import com.towpen.base.exceptions.models.TMessageFactory;
import com.towpen.base.restservice.model.TOpenMessage;
import com.towpen.utils.TDateUtils;
import com.towpen.utils.TStringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class LoginAttemptServiceImpl extends BaseDbServiceImp<LoginAttemptRepository, LoginAttempt> implements ILoginAttemptService{
	private static final String GENERAL_VALIDITY_END_DATE = "general.validity.end-date";

	@Override
	public Class<?> getDTOClassForService() {
		return DtoLoginAttempt.class;
	}

	@Override
	public Page<LoginAttempt> findLoginAttemptsWithSearchParams(Pageable pageable, String userName, String ipAddress, Date createTime, String startAccessTime, String endAccessTime, Boolean isLoginSuccess, String errorMessage, String sessionId) {
		Date st = null;
		Date et = null;
		TOpenException exceptions = new TOpenException();
		try {
			if(TStringUtil.hasText(startAccessTime))
				st = TDateUtils.convertStringToDateWithHourAndMinute(startAccessTime);
			if(TStringUtil.hasText(endAccessTime)) {
				et = TDateUtils.convertStringToDateWithHourAndMinute(endAccessTime);
				//et = JDateUtils.atEndOfDay(et); //bu kisim calendar tipi hour minute ile calisir hale gelince degistirilmeli simdilik gun sonu set ettik
			}
		} catch (Exception e) {
			exceptions.addMessage(new TOpenMessage(TMessageType.ENTERED_DATA_IS_NOT_IN_FORMAT_1046, TMessageFactory.ofStatic("yyyy-MM-dd'T'HH:mm")));
		}

		if (st != null && et != null && st.compareTo(et) > 0) {
			exceptions.addMessage(new TOpenMessage(TMessageType.CAN_NOT_BE_LARGER_THAN_1003, TMessageFactory.ofBundle("general.validity.start-date"), TMessageFactory.ofBundle(GENERAL_VALIDITY_END_DATE)));
		}
		exceptions.checkError();
		return dao.findLoginAttemptsWithSearchParams(pageable, userName, ipAddress, createTime, st, et, isLoginSuccess, errorMessage, sessionId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public LoginAttempt save(LoginAttempt entity) {
		return super.save(entity);
	}
}
