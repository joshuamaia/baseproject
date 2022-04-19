package br.com.joshua.baseproject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.joshua.baseproject.dto.ExpenseControlDto;
import br.com.joshua.baseproject.dto.ExpenseSumDto;

public interface ExpenseControlService extends ServiceBase<ExpenseControlDto, Long> {

	List<ExpenseSumDto> searchSumExpense(Long personId);
	
	public Page<ExpenseControlDto> filter(
			@Nullable String description,
			@Nullable String name,
			@Nullable String email,
			@Nullable Integer page,
			@Nullable Integer size);
	
}
