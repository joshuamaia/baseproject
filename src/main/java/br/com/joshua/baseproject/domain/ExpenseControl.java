package br.com.joshua.baseproject.domain;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.joshua.baseproject.enums.ExpenseEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseControl implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private ExpenseEnum expense;

	private String description;

	private LocalDate dateExpense;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

}
