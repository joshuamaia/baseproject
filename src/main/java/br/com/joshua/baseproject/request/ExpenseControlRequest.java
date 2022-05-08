package br.com.joshua.baseproject.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.joshua.baseproject.enums.ExpenseEnum;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ExpenseControlRequest extends RequestBase<Long> {
	private static final long serialVersionUID = 1L;

	ExpenseEnum expense;

	String description;

	LocalDate dateExpense;

	BigDecimal value;

	PersonRequest person;

}
