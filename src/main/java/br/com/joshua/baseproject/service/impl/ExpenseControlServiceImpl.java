package br.com.joshua.baseproject.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.joshua.baseproject.domain.ExpenseControl;
import br.com.joshua.baseproject.dto.ExpenseControlDto;
import br.com.joshua.baseproject.dto.ExpenseSumDto;
import br.com.joshua.baseproject.repository.ExpenseControlRepository;
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

}
