package br.com.joshua.baseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joshua.baseproject.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
