package com.pen.securitymanager.model;

import com.towpen.base.restservice.model.DtoEntityModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCompany extends DtoEntityModel {
	private static final long serialVersionUID = 1L;

	private String companyCode;

	private String companyName;

	private String miniLogoId;

	private String logoId;

	private Boolean isMainCompany;
}
