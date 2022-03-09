package br.com.joshua.baseproject.service;

import java.util.List;

import br.com.joshua.baseproject.dto.ExpenseControlDto;
import br.com.joshua.baseproject.dto.ExpenseSumDto;

public interface ExpenseControlService extends ServiceBase<ExpenseControlDto, Long> {

	List<ExpenseSumDto> searchSumExpense(Long personId);
	
}
