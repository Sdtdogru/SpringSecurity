package com.pen.securitymanager.db.model;

import com.towpen.base.db.model.TOpenDbEntity;
import com.towpen.base.db.model.comment.DBComments;
import com.towpen.base.enums.model.MenuTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Comment(DBComments.Tables.TABLE_MENU)
public class Menu extends TOpenDbEntity {
	private static final long serialVersionUID = -4669292709557389552L;
	@Comment(DBComments.Fields.FIELD_MENU_PARENT_MENU_ID)
	@OneToOne
	private Menu parentMenu;

	@Comment(DBComments.Fields.FIELD_MENU_MENU_TYPE)
	@Enumerated(EnumType.STRING)
	@Column(name = "menu_type",nullable = false)
	private MenuTypeEnum menuType;

	@Comment(DBComments.Fields.FIELD_MENU_MENU_TEXT_TR)
	@Basic
	@Column(name = "menu_text_tr", length = 100, nullable = false)
	private String menuTextTr;

	@Comment(DBComments.Fields.FIELD_MENU_MENU_TEXT_EN)
	@Basic
	@Column(name = "menu_text_en", length = 100, nullable = false)
	private String menuTextEn;

	@Comment(DBComments.Fields.FIELD_MENU_TOOLTIP_TR)
	@Basic
	@Column(name = "tooltip_tr", length = 150, nullable = true)
	private String tooltipTr;

	@Comment(DBComments.Fields.FIELD_MENU_TOOLTIP_EN)
	@Basic
	@Column(name = "tooltip_en", length = 150, nullable = true)
	private String tooltipEn;

	@Comment(DBComments.Fields.FIELD_MENU_ICON)
	@Basic
	@Column(name = "icon", length = 50, nullable = true)
	private String icon;

	@Comment(DBComments.Fields.FIELD_MENU_MENU_LINK)
	@Basic
	@Column(name = "menu_link", length = 300, nullable = true)
	private String menuLink;

	@Comment(DBComments.Fields.FIELD_MENU_EXTERNAL_LINK)
	@Basic
	@Column(name = "external_link", length = 300, nullable = true)
	private String externalLink;

	@Comment(DBComments.Fields.FIELD_MENU_ACTIVE)
	@Basic
	@Column(name = "active", nullable = false)
	private Boolean active ;

	@Comment(DBComments.Fields.FIELD_MENU_ROW_ORDER_NO)
	@Basic
	@Column(name = "row_order_no", nullable = true)
	private Integer rowOrderNo;
}
