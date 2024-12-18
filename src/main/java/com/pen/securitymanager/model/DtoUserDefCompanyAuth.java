package com.pen.securitymanager.model;

import com.towpen.base.restservice.model.DtoEntityModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUserDefCompanyAuth extends DtoEntityModel {
	private static final long serialVersionUID = 1L;

	private DtoUserDef userDef;

	private DtoCompany company;
}
