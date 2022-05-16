package br.com.joshua.baseproject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.joshua.baseproject.request.ExpenseControlRequest;
import br.com.joshua.baseproject.response.ExpenseControlResponse;
import br.com.joshua.baseproject.response.ExpenseSumResponse;

public interface ExpenseControlService extends ServiceBase<ExpenseControlResponse, ExpenseControlRequest, Long> {

	List<ExpenseSumResponse> searchSumExpense(Long personId);
	
	List<ExpenseSumResponse> searchSumExpenseTotal();
	
	public Page<ExpenseControlResponse> filter(
			@Nullable String description,
			@Nullable String name,
			@Nullable String email,
			@Nullable Integer page,
			@Nullable Integer size);
	
}
