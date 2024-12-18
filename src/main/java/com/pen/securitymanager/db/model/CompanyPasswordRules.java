package com.pen.securitymanager.db.model;

import com.towpen.base.db.model.TOpenSimpleCompanyEntity;
import com.towpen.base.db.model.comment.DBComments;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@Table(name = "company_password_rules")
@Comment(DBComments.Tables.TABLE_COMPANY_PASSWORD_RULES)
public class CompanyPasswordRules extends TOpenSimpleCompanyEntity {
	private static final long serialVersionUID = 1L;
	@Comment(DBComments.Fields.FIELD_COMPANY_PASSWORD_RULES_MIN_LENGTH)
	@Column(name = "min_length", nullable = false)
	Short minLength;

	@Comment(DBComments.Fields.FIELD_COMPANY_PASSWORD_RULES_MAX_LENGTH)
	@Column(name = "max_length", nullable = false)
	Short maxLength;

	@Comment(DBComments.Fields.FIELD_COMPANY_PASSWORD_RULES_SPECIAL_CHARACTER_COUNT)
	@Column(name = "special_character_count", nullable = false)
	Short specialCharacterCount;

	@Comment(DBComments.Fields.FIELD_COMPANY_COMPANY_NAME)
	@Column(name = "digit_count", nullable = false)
	Short digitCount;

	@Comment(DBComments.Fields.FIELD_COMPANY_PASSWORD_RULES_LOWER_CASE_CHAR_COUNT)
	@Column(name = "lower_case_char_count", nullable = false)
	Short lowerCaseCharacterCount;

	@Comment(DBComments.Fields.FIELD_COMPANY_PASSWORD_RULES_UPPER_CASE_CHAR_COUNT)
	@Column(name = "upper_case_char_count", nullable = false)
	Short upperCaseCharacterCount;

	@Comment(DBComments.Fields.FIELD_COMPANY_PASSWORD_RULES_EXPIRATION_PERIOD)
	@Column(name = "expiration_period", nullable = false)
	Short expirationPeriod;

	@Comment(DBComments.Fields.FIELD_COMPANY_PASSWORD_RULES_PASSWORD_DIFFERENT_COUNT)
	@Column(name = "password_different_count", nullable = false)
	Short passwordDifferentCount;
}
