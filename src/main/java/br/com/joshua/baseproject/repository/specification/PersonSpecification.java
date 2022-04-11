package br.com.joshua.baseproject.repository.specification;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import br.com.joshua.baseproject.domain.Person;

public class PersonSpecification extends SpecificationBase<Person> {

	private static final long serialVersionUID = 1L;

	public Specification<Person> findByName(@Nullable String nome) {
		return Optional.ofNullable(nome).map(n -> prepareLikeSpecification("name", n)).orElse(null);
	}

	public Specification<Person> findByEmail(@Nullable String email) {
		return Optional.ofNullable(email).map(n -> prepareLikeSpecification("email", n)).orElse(null);
	}

}
