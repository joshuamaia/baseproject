package br.com.joshua.baseproject.dto;

import java.time.LocalDate;

import br.com.joshua.baseproject.domain.Person;
import br.com.joshua.baseproject.enums.ExpenseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseControlDto {
	private Long id;

	private ExpenseEnum expense;

	private String description;

	private LocalDate dateExpense;
	
	private Person person;

}
