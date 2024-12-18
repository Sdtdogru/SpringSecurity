package com.pen.securitymanager.db.model;

import com.towpen.base.db.model.TOpenDbEntity;
import com.towpen.base.db.model.comment.DBComments;
import com.towpen.base.db.model.security.Company;
import com.towpen.base.db.model.security.UserDef;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Table(name = "user_def_company_auth_log")
@Comment(DBComments.Tables.TABLE_USER_DEF_COMPANY_AUTH_LOG)
@NamedEntityGraph(name = "user-def-company-auth-log-entity", attributeNodes = { @NamedAttributeNode(value = "company"), @NamedAttributeNode(value = "userDef") })

public class UserDefCompanyAuthLog extends TOpenDbEntity {

	private static final long serialVersionUID = 1L;
	@Comment(DBComments.Fields.FIELD_USER_DEF_COMPANY_AUTH_LOG_USER_DEF_ID)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserDef userDef;

	@Comment(DBComments.Fields.FIELD_USER_DEF_COMPANY_AUTH_LOG_COMPANY_ID)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Company company;

	@Comment(DBComments.Fields.FIELD_USER_DEF_COMPANY_AUTH_LOG_PROCESS)
	@Column(name = "process", length = 20)
	private String process;

	@Comment(DBComments.Fields.FIELD_USER_DEF_COMPANY_AUTH_LOG_PROCESS_USER)
	@Column(name = "process_user", length = 20)
	private String processUser;
}
