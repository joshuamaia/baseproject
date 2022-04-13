package br.com.joshua.baseproject.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.joshua.baseproject.enums.ExpenseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ExpenseControl extends EntityBase<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Enumerated(EnumType.STRING)
	ExpenseEnum expense;

	@NotEmpty
	String description;

	@NotNull
	LocalDate dateExpense;

	@NotNull
	@Column(precision = 10, scale = 2)
	BigDecimal value;

	@ManyToOne
	@JoinColumn(name = "person_id")
	Person person;

}
