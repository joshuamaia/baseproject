package br.com.joshua.baseproject.service.impl;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import br.com.joshua.baseproject.domain.Person;
import br.com.joshua.baseproject.repository.PersonRepository;
import br.com.joshua.baseproject.repository.specification.PersonSpecification;
import br.com.joshua.baseproject.request.PersonRequest;
import br.com.joshua.baseproject.response.PersonResponse;
import br.com.joshua.baseproject.service.PersonService;

@Service
public class PersonServiceImpl extends ServiceBaseImpl<PersonResponse, PersonRequest, Long, Person, PersonRepository>
		implements PersonService {

	@Override
	public Page<PersonResponse> searchAllPage(Integer page, Integer size, String wordSearch) {
		PageRequest pageRequest = PageRequest.of(page, size);
		if (wordSearch == null || wordSearch.trim().isEmpty()) {
			return repository.findAll(pageRequest).map(this::convertFromResponse);
		}
		wordSearch = wordSearch.toLowerCase();
		return repository.searchAllPage(wordSearch, pageRequest).map(this::convertFromResponse);

	}

	@Override
	public Page<PersonResponse> filter(@Nullable String name, @Nullable String email, @Nullable Integer page,
			@Nullable Integer size) {
		var specification = this.prepareSpecification(name, email);
		return this.repository.findAll(specification, this.preparePageable(PageRequest.of(page, size)))
				.map(this::convertFromResponse);
	}

	@NotNull
	private Specification<Person> prepareSpecification(@Nullable String name, @Nullable String email) {
		final var specification = new PersonSpecification();

		return specification.and(specification.findByName(name)).and(specification.findByEmail(email));
	}

	private Pageable preparePageable(@Nullable Pageable pageable) {
		return Optional.ofNullable(pageable).orElse(Pageable.unpaged());
	}

}
