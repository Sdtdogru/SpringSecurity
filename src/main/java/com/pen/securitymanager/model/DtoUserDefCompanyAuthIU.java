package com.pen.securitymanager.model;

import com.towpen.base.restservice.model.DtoCrudModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUserDefCompanyAuthIU extends DtoCrudModel {
	private static final long serialVersionUID = 1L;

	@Schema(description = "User Def Id")
	@NotEmpty(message = "userdef.id")
	private String userDefId;

	@Schema(description = "Company Id")
	@NotEmpty(message = "company.id")
	private String companyId;
}
