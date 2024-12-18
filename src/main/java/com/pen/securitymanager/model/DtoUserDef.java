package com.pen.securitymanager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.towpen.base.annotations.definitions.EnumConverter;
import com.towpen.base.enums.model.LanguageType;
import com.towpen.base.restservice.model.DtoEntityModel;
import com.towpen.base.restservice.model.EnumValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoUserDef extends DtoEntityModel {
	private static final long serialVersionUID = -5206044985265202122L;

	@Schema(description = "UserName")
	private String userName;

	@Schema(description = "User Display Name")
	private String userDisplayName;

	@Schema(description = "User Language Select")
	@EnumConverter( enumClass = LanguageType.class,fieldName = "languageVal")
	private EnumValue languageVal;

	@Schema(description = "Is Active")
	@NotNull(message = "general.isActive")
	private Boolean isActive;
}
