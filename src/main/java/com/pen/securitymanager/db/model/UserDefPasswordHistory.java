package com.pen.securitymanager.db.model;

import com.towpen.base.db.model.TOpenSimpleCompanyEntity;
import com.towpen.base.db.model.comment.DBComments;
import com.towpen.base.db.model.security.UserDef;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Entity
@Getter
@Setter
@Comment(DBComments.Tables.TABLE_USER_DEF_PASSWORD_HISTORY)
public class UserDefPasswordHistory  extends TOpenSimpleCompanyEntity {
	private static final long serialVersionUID = 4756245851035528238L;

	@Comment(DBComments.Fields.FIELD_USER_DEF_PASSWORD_HISTORY_USER_DEF_ID)
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	private UserDef userDef;

	@Comment(DBComments.Fields.FIELD_USER_DEF_PASSWORD_HISTORY_SALT_KEY)
	@Column(name = "salt_key", nullable = false,length = 100)
	private String saltKey;

	@Comment(DBComments.Fields.FIELD_USER_DEF_PASSWORD_HISTORY_PASSWORD_HASH)
	@Column(name = "password_hash",length = 500,nullable = false)
	private String passwordHash;

	@Comment(DBComments.Fields.FIELD_USER_DEF_PASSWORD_HISTORY_PASSWORD_CHANGE_TIME)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "password_change_time")
	private Date passwordChangeTime;
}
