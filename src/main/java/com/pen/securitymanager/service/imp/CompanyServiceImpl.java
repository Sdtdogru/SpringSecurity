package com.pen.securitymanager.service.imp;

import com.pen.securitymanager.model.DtoCompany;
import com.pen.securitymanager.repository.CompanyRepository;
import com.pen.securitymanager.service.ICompanyService;
import com.towpen.base.db.model.security.Company;
import com.towpen.base.enums.model.TMessageType;
import com.towpen.base.exceptions.TOpenException;
import com.towpen.base.exceptions.models.TMessageFactory;
import com.towpen.base.restservice.model.TOpenMessage;
import com.towpen.base.security.ISessionInstanceService;
import com.towpen.utils.RestUtil;
import com.towpen.utils.TStringUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CompanyServiceImpl extends com.towpen.base.BaseDbServiceImp<CompanyRepository, Company> implements ICompanyService {
	@Autowired
	ISessionInstanceService sessionInstanceService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	RestUtil restUtil;



	@Override
	public Company findByCompanyCode() {
		Optional<Company> optComp = dao.findByCompanyCode(sessionInstanceService.getSelectedCompanyCode());
		if (optComp.isEmpty()) {
			throw new TOpenException(new TOpenMessage(TMessageType.NOT_EXISTS_IN_THE_RECORDS_1006, TMessageFactory.ofStatic(sessionInstanceService.getSelectedCompanyCode())));
		}
		return optComp.get();
	}

	@Override
	public Class<?> getDTOClassForService() {
		return DtoCompany.class;
	}

	@Override
	public String getCompanyLogoBase64(boolean isSmall) {
		Company comp = findByCompanyCode();
		if ((isSmall && TStringUtil.isNullOrBlank(comp.getMiniLogoId())) || (!isSmall && TStringUtil.isNullOrBlank(comp.getLogoId()))) {
			throw new TOpenException(new TOpenMessage(TMessageType.NOT_EXISTS_IN_THE_RECORDS_1006, TMessageFactory.ofBundle("company.minilogo")));
		}
		try {
		/*	URI url = new URI(apiDocManagerPathFirst + (isSmall ? comp.getMiniLogoId() : comp.getLogoId()) + apiDocManagerPathSecond);
			RestTemplate rt = restUtil.createRestTemplate();
			HttpHeaders headers = new HttpHeaders();
			String authTokenHeader = request.getHeader("Authorization");
			headers.add("Authorization", authTokenHeader);
			HttpEntity<String> entity = new HttpEntity<>("body", headers);

			ResponseEntity<RestRootEntity<String>> response = rt.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<RestRootEntity<String>>() {
			});
			RestRootEntity<String> value = response.getBody();
			if (value == null || value.getPayload() == null) {
				return "";
			}*/
			return comp.getCompanyCode();
		} catch (Exception e) {
			log.error("logo URL error", e);
		}
		return "";
	}

	@Override
	public List<DtoCompany> getAllCompany() {
		List<Company> company = dao.findAll();
		return toDTOList(company);
	}

	@Override
	public List<DtoCompany> getAllCompanyDto() {
		return toDTOList(dao.findCompanyListNotMain());
	}
}
