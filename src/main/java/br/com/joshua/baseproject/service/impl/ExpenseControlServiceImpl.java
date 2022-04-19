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
import br.com.joshua.baseproject.dto.ExpenseControlDto;
import br.com.joshua.baseproject.dto.ExpenseSumDto;
import br.com.joshua.baseproject.repository.ExpenseControlRepository;
import br.com.joshua.baseproject.repository.specification.ExpenseControlSpecification;
import br.com.joshua.baseproject.service.ExpenseControlService;

@Service
public class ExpenseControlServiceImpl
		extends ServiceBaseImpl<ExpenseControlDto, Long, ExpenseControl, ExpenseControlRepository>
		implements ExpenseControlService {

	@Override
	public Page<ExpenseControlDto> searchAllPage(Integer page, Integer size, String wordSearch) {
		PageRequest pageRequest = PageRequest.of(page, size);
		if (wordSearch == null || wordSearch.trim().isEmpty()) {
			return repository.findAll(pageRequest).map(this::convertFromDTO);
		}
		wordSearch = wordSearch.toLowerCase();
		return repository.searchAllPage(wordSearch, pageRequest).map(this::convertFromDTO);

	}

	@Override
	public List<ExpenseSumDto> searchSumExpense(Long personId) {
		return this.repository.searchSumExpense(personId);
	}

	@Override
	public Page<ExpenseControlDto> filter(@Nullable String description, @Nullable String name, @Nullable String email,
			@Nullable Integer page, @Nullable Integer size) {
		var specification = this.prepareSpecification(description, name, email);
		return this.repository.findAll(specification, this.preparePageable(PageRequest.of(page, size)))
				.map(this::convertFromDTO);
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
