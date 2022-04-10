package br.com.joshua.baseproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.joshua.baseproject.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

	@Query("FROM Person p WHERE LOWER(p.name) like %:wordSearch% OR LOWER(p.email) like %:wordSearch%")
	Page<Person> searchAllPage(@Param("wordSearch") String wordSearch, Pageable pageable);

}
