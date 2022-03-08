package br.com.joshua.baseproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.joshua.baseproject.domain.ExpenseControl;

public interface ExpenseControlRepository extends JpaRepository<ExpenseControl, Long> {
	
	@Query("FROM ExpenseControl ec WHERE LOWER(ec.description) like %:wordSearch%")
	Page<ExpenseControl> searchAllPage(@Param("wordSearch") String wordSearch, Pageable pageable);

}
