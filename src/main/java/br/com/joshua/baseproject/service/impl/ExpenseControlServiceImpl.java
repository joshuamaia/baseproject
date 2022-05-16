package br.com.joshua.baseproject.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import br.com.joshua.baseproject.domain.ExpenseControl;
import br.com.joshua.baseproject.repository.ExpenseControlRepository;
import br.com.joshua.baseproject.repository.specification.ExpenseControlSpecification;
import br.com.joshua.baseproject.request.ExpenseControlRequest;
import br.com.joshua.baseproject.response.ExpenseControlResponse;
import br.com.joshua.baseproject.response.ExpenseSumResponse;
import br.com.joshua.baseproject.service.ExpenseControlService;

@Service
public class ExpenseControlServiceImpl extends
		ServiceBaseImpl<ExpenseControlResponse, ExpenseControlRequest, Long, ExpenseControl, ExpenseControlRepository>
		implements ExpenseControlService {

	@Override
	public Page<ExpenseControlResponse> searchAllPage(Integer page, Integer size, String wordSearch) {
		PageRequest pageRequest = PageRequest.of(page, size);
		if (wordSearch == null || wordSearch.trim().isEmpty()) {
			return repository.findAll(pageRequest).map(this::convertFromResponse);
		}
		wordSearch = wordSearch.toLowerCase();
		return repository.searchAllPage(wordSearch, pageRequest).map(this::convertFromResponse);

	}

	@Override
	public List<ExpenseSumResponse> searchSumExpense(Long personId) {
		return this.repository.searchSumExpense(personId);
	}
	
	@Override
	public List<ExpenseSumResponse> searchSumExpenseTotal() {
		return this.repository.searchSumExpenseTotal();
	}

	@Override
	public Page<ExpenseControlResponse> filter(@Nullable String description, @Nullable String name,
			@Nullable String email, @Nullable Integer page, @Nullable Integer size) {
		var specification = this.prepareSpecification(description, name, email);
		return this.repository.findAll(specification, this.preparePageable(PageRequest.of(page, size)))
				.map(this::convertFromResponse);
	}

	@NotNull
	private Specification<ExpenseControl> prepareSpecification(@Nullable String description, @Nullable String name,
			@Nullable String email) {
		final var specification = new ExpenseControlSpecification();

		return specification.and(specification.findByDescription(description)).and(specification.findByPersonName(name))
				.and(specification.findByPersonEmail(email));
	}

	private Pageable preparePageable(@Nullable Pageable pageable) {
		return Optional.ofNullable(pageable).orElse(Pageable.unpaged());
	}



}
