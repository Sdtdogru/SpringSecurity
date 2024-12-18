package com.pen.securitymanager.model;

import com.towpen.base.restservice.model.DtoCrudModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DtoUserDefAccessIU  extends DtoCrudModel {
	private static final long serialVersionUID = 1L;

	@Schema(description = "Can Login")
	@NotNull(message = "userdef.canlogin")
	private Boolean canLogin;

	@Schema(description = "is Force Password Change")
	@NotNull(message = "userdef.force.pass.change")
	private Boolean isForcePasswordChange;

	@Schema(description = "has Ip Restriction")
	@NotNull(message = "userdef.has.ip.restriction")
	private Boolean hasIpRestriction;

	@Schema(description = "IP List")
	private List<String> ipRestrictionList;
}
