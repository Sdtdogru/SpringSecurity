package com.pen.securitymanager.db.model;

import com.towpen.base.db.model.TOpenDbEntity;
import com.towpen.base.db.model.comment.DBComments;
import com.towpen.base.db.model.security.Company;
import com.towpen.base.db.model.security.UserDef;
import com.towpen.base.enums.model.UserDefGenericIdType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Comment(DBComments.Tables.TABLE_USER_DEF_COMPANY_AUTH)
@Table(name = "user_def_company_auth", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_def_id", "company_id" }) })
@NamedEntityGraph(name = "user-def-company-auth-entity", attributeNodes = { @NamedAttributeNode(value = "company"), @NamedAttributeNode(value = "userDef") })

public class UserDefCompanyAuth extends TOpenDbEntity {
	private static final long serialVersionUID = 1L;

	@Comment(DBComments.Fields.FIELD_USER_DEF_COMPANY_AUTH_USER_DEF_ID)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserDef userDef;

	@Comment(DBComments.Fields.FIELD_USER_DEF_COMPANY_AUTH_COMPANY_ID)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Company company;

	@Comment(DBComments.Fields.FIELDS_USER_DEF_GENERIC_IDENTIFIER)
	@Column(name = "generic_identifier", nullable = true)
	private String genericIdentifier;

	@Comment(DBComments.Fields.FIELDS_USER_DEF_GENERIC_IDENTIFIER_TYPE)
	@Enumerated(EnumType.STRING)
	@Column(name = "user_def_generic_id_type", nullable = true)
	private UserDefGenericIdType userDefGenericIdType;
}
