package br.com.joshua.baseproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
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

}
