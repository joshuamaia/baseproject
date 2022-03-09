package br.com.joshua.baseproject.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.joshua.baseproject.enums.ExpenseEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

	@NotNull
	@Enumerated(EnumType.STRING)
	private ExpenseEnum expense;

	@NotEmpty
	private String description;

	@NotEmpty
	private LocalDate dateExpense;
	
	@NotNull
	@Column( precision = 10, scale = 2)
	private BigDecimal value;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

}
