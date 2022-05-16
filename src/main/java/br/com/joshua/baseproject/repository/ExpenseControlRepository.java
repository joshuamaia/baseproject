package br.com.joshua.baseproject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.joshua.baseproject.domain.ExpenseControl;
import br.com.joshua.baseproject.response.ExpenseSumResponse;

public interface ExpenseControlRepository
		extends JpaRepository<ExpenseControl, Long>, JpaSpecificationExecutor<ExpenseControl> {

	@Query("FROM ExpenseControl ec WHERE LOWER(ec.description) like %:wordSearch%")
	Page<ExpenseControl> searchAllPage(@Param("wordSearch") String wordSearch, Pageable pageable);

	@Query(value = "SELECT expense, sum(value) as value FROM expense_control " + "	where person_id = :personId "
			+ "	group by 1", nativeQuery = true)
	List<ExpenseSumResponse> searchSumExpense(@Param("personId") Long personId);

	@Query(value = "SELECT expense, sum(value) as value FROM expense_control group by 1", nativeQuery = true)
	List<ExpenseSumResponse> searchSumExpenseTotal();

}
