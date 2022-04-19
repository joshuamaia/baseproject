package br.com.joshua.baseproject.repository.specification;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import br.com.joshua.baseproject.domain.ExpenseControl;

public class ExpenseControlSpecification extends SpecificationBase<ExpenseControl> {

	private static final long serialVersionUID = 1L;
	
	public Specification<ExpenseControl> findByDescription(@Nullable String description) {
		return Optional.ofNullable(description).map(n -> prepareLikeSpecification("description", n)).orElse(null);
	}

	public Specification<ExpenseControl> findByPersonName(@Nullable String nome) {
		return Optional.ofNullable(nome).map(n -> prepareLikeSubSpecification("person", "name", n)).orElse(null);
	}

	public Specification<ExpenseControl> findByPersonEmail(@Nullable String email) {
		return Optional.ofNullable(email).map(n -> prepareLikeSubSpecification("person", "email", n)).orElse(null);
	}

}
