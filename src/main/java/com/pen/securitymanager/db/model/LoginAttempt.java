package com.pen.securitymanager.db.model;

import com.towpen.base.db.model.TOpenDbEntity;
import com.towpen.base.db.model.comment.DBComments;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Entity
@Comment(DBComments.Tables.TABLE_LOGIN_ATTEMPT)
public class LoginAttempt extends TOpenDbEntity {
	private static final long serialVersionUID = 1548794291179595065L;
	@Comment(DBComments.Fields.FIELD_LOGIN_ATTEMPT_USER_NAME)
	@Column(name = "user_name",length = 40,nullable = false)
	private String userName;

	@Comment(DBComments.Fields.FIELD_LOGIN_ATTEMPT_IS_LOGIN_SUCCESS)
	@Column(name = "is_login_success",nullable = false)
	private boolean isLoginSuccess;

	@Comment(DBComments.Fields.FIELD_LOGIN_ATTEMPT_IP_ADDRESS)
	@Column(name = "ip_address",nullable = false,length = 40)
	private String ipAddress;

	@Comment(DBComments.Fields.FIELD_LOGIN_ATTEMPT_BROWSER)
	@Column(name = "browser",nullable = false,length = 500)
	private String browser;

	@Comment(DBComments.Fields.FIELD_LOGIN_ATTEMPT_SERVER_NAME)
	@Column(name = "server_name",nullable = true,length = 100)
	private String serverName;

	@Comment(DBComments.Fields.FIELD_LOGIN_ATTEMPT_ERROR_MESSAGE)
	@Column(name = "error_message",nullable = true,length = 500)
	private String errorMessage;

	@Column(name = "session_id",nullable = true,length = 100)
	private String sessionId;
}
