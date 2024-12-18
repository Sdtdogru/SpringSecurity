package com.pen.securitymanager.model;

import com.towpen.base.restservice.model.DtoCrudModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCompanyPasswordRulesIU extends DtoCrudModel {
	private static final long serialVersionUID = 1L;

	@Min(value = 6,message = "password.min")
	@Max(value = 18)
	@Schema(description = "Minimum Password Length")
	private Short minLength;

	@Min(value = 6, message = "password.max")
	@Max(value = 18)
	@Schema(description = "Maximum Password Length")
	private Short maxLength;

	@Min(value = 0, message = "password.specialChar.min")
	@Max(value = 18, message = "password.specialChar.max")
	@Schema(description = "Special Character Count")
	private Short specialCharacterCount;

	@Min(value = 0, message = "password.digit.min")
	@Max(value = 18, message = "password.digit.max")

	@Schema(description = "Digit Count")
	private Short digitCount;

	@Min(value = 0, message = "password.lowerChar.min")
	@Max(value = 18, message = "password.lowerChar.max")
	@Schema(description = "Lower Case Character Count")
	private Short lowerCaseCharacterCount;

	@Min(value = 0, message = "password.upperChar.min")
	@Max(value = 18, message = "password.upperChar.max")
	@Schema(description = "Upper Case Character Count")
	private Short upperCaseCharacterCount;

	@Min(value = 1, message = "password.expirationPeriod.min")
	@Max(value = 999, message = "password.expirationPeriod.max")
	@Schema(description = "Password Expiration Period")
	private Short expirationPeriod;

	@Min(value = 1, message = "password.olderDifference.min")
	@Max(value = 999, message = "password.olderDifference.max")
	@Schema(description = "Older Password Difference Count")
	private Short passwordDifferentCount;
}
