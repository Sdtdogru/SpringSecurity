package com.pen.securitymanager.db.model;

import com.towpen.base.db.model.TOpenDbEntity;
import com.towpen.base.db.model.comment.DBComments;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
@Getter
@Setter
@Entity
@Table(name = "authorization_def")
@Comment(DBComments.Tables.TABLE_AUTHORIZATION_DEF)
@NamedEntityGraph(name = "authorization-menu", attributeNodes = {@NamedAttributeNode(value = "menu") })
public class AuthorizationDef extends TOpenDbEntity{
	private static final long serialVersionUID = -3109369093696759934L;
	@Comment(DBComments.Fields.FIELD_AUTHORIZATION_DEF_NAME)
	@Column(name = "name", nullable = false, unique = true, length = 60)
	private String name;

	@Comment(DBComments.Fields.FIELD_AUTHORIZATION_DEF_DESCRIPTION)
	@Column(name = "description", length = 255,nullable = false)
	private String description;

	@Comment(DBComments.Fields.FIELD_AUTHORIZATION_DEF_SHORT_CODE)
	@Column(name = "short_code", unique = true, length = 100, nullable = false)
	private String shortCode;

	@Comment(DBComments.Fields.FIELD_AUTHORIZATION_DEF_IS_ACTIVE)
	@Column(name = "is_active", nullable = false)
	private Boolean isActive;

	@Comment(DBComments.Fields.FIELD_AUTHORIZATION_DEF_MENU_ID)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Menu menu;
}
