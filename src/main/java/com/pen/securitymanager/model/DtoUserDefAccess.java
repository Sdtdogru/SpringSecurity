package com.pen.securitymanager.model;

import com.towpen.base.annotations.definitions.EnumConverter;
import com.towpen.base.restservice.model.DtoEntityModel;
import com.towpen.base.restservice.model.EnumValue;
import jakarta.persistence.AccessType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DtoUserDefAccess  extends DtoEntityModel {
	private static final long serialVersionUID = 1L;

	private DtoUserDef userDef;

	private Boolean canLogin;

	private Boolean isForcePasswordChange;

	private Date lastChangeTime;

	private Boolean hasIpRestriction;

	private String ipRestriction;

	@EnumConverter(enumClass = AccessType.class, fieldName = "accessType")
	private EnumValue accessType;
}
