package com.pen.securitymanager.auth.model;

import com.towpen.base.restservice.model.DtoBaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeRequest extends DtoBaseModel {
	private static final long serialVersionUID = 1L;


	@Schema(description = "Current Password")
	@NotBlank(message = "general.security.currentpassword")
	private String currentPassword;

	@Schema(description = "New Password")
	@NotBlank(message = "general.security.newpassword")
	private String newPassword;

	@Schema(description = "New Password(Again)")
	@NotBlank(message = "general.security.newpassword-again")
	private String newPasswordAgain;
}
