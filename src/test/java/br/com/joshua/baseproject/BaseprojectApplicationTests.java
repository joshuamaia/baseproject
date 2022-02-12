package br.com.joshua.baseproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.joshua.baseproject.domain.Person;
import br.com.joshua.baseproject.repository.PersonRepository;

@SpringBootTest
class BaseprojectApplicationTests {

	@Mock(answer = Answers.RETURNS_SMART_NULLS)
	PersonRepository repository;

	public Person person = null;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		person = new Person(1L, "Gina", "gina@gmail.com", LocalDate.now());

	}

	@Test
	public void savePersonTest() {

		when(repository.save(any(Person.class))).then(new Answer<Person>() {

			@Override
			public Person answer(InvocationOnMock invocation) throws Throwable {
				return person;
			}
		});

		Person response = repository.save(person);

		assertEquals("Gina", response.getName());
		assertEquals("gina@gmail.com", response.getEmail());
		assertEquals(1L, response.getId());
	}

	@Test
	public void deletePersonTest() {
		Optional<Person> optionalPerson = Optional.of(person);
		when(repository.findById(1L)).thenReturn(optionalPerson);

		repository.deleteById(1L);

		verify(repository, times(1)).deleteById(1L);
	}
	
	@Test
	public void findOnePersonTest() {
		Optional<Person> optionalPerson = Optional.of(person);
		when(repository.findById(1L)).thenReturn(optionalPerson);

		Optional<Person> personReturnOptional = repository.findById(1L);
		Person personReturn = personReturnOptional.get();
		
		verify(repository, times(1)).findById(1L);
		
		assertEquals("Gina", personReturn.getName());
		assertEquals("gina@gmail.com", personReturn.getEmail());
		assertEquals(1L, personReturn.getId());
	}

}
