package com.pen.securitymanager.db.model;

import com.towpen.base.db.model.TOpenSimpleCompanyEntity;
import com.towpen.base.db.model.comment.DBComments;
import com.towpen.base.db.model.security.RoleDef;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Entity
@Table(name = "role_authorization")
@Comment(DBComments.Tables.TABLE_ROLE_AUTHORIZATION)
@NamedEntityGraph(name = "roleAuthorizationDetails", attributeNodes = { @NamedAttributeNode(value = "roleDef"), @NamedAttributeNode(value = "authorizationDef") })
public class RoleAuthorization extends TOpenSimpleCompanyEntity {
	private static final long serialVersionUID = -7576044527219525062L;
	@Comment(DBComments.Fields.FIELD_ROLE_AUTHORIZATION_ROLE_DEF_ID)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private RoleDef roleDef;

	@Comment(DBComments.Fields.FIELD_ROLE_AUTHORIZATION_AUTHORIZATION_DEF_ID)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private AuthorizationDef authorizationDef;
}
