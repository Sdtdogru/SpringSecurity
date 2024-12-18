package com.pen.securitymanager.db.model;

import com.towpen.base.db.model.TOpenSimpleCompanyEntity;
import com.towpen.base.db.model.comment.DBComments;
import com.towpen.base.db.model.security.UserDef;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Getter
@Setter
@Entity
@Comment(DBComments.Tables.TABLE_USER_DEF_LOGIN_LOG)
public class UserDefLoginLog extends TOpenSimpleCompanyEntity{
	private static final long serialVersionUID = -7427562433104547105L;

	@Comment(DBComments.Fields.FIELD_USER_DEF_LOGIN_LOG_USER_DEF_ID)
	@ManyToOne(optional = false,fetch = FetchType.EAGER)
	private UserDef userDef;

	@Comment(DBComments.Fields.FIELD_USER_DEF_LOGIN_LOG_LOGIN_ATTEMPT_ID)
	@ManyToOne(optional = false,fetch = FetchType.EAGER)
	private LoginAttempt loginAttempt;

	@Comment(DBComments.Fields.FIELD_USER_DEF_LOGIN_LOG_LOGIN_TIME)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "login_time",nullable = false)
	private Date loginTime;

	@Comment(DBComments.Fields.FIELD_USER_DEF_LOGIN_LOG_LOGOUT_TIME)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "logout_time",nullable = true)
	private Date logoutTime;

}
