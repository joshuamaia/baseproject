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
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.joshua.baseproject.domain.Person;
import br.com.joshua.baseproject.dto.PersonDto;
import br.com.joshua.baseproject.repository.PersonRepository;
import br.com.joshua.baseproject.service.PersonService;

@SpringBootTest
class BaseprojectApplicationTests {

	@Mock(answer = Answers.RETURNS_SMART_NULLS)
	PersonRepository repository;
	
	@Mock(answer = Answers.RETURNS_SMART_NULLS)
	PersonService service;

	public Person person = null;
	public PersonDto personDto = null;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		person = new Person(1L, "Gina", "gina@gmail.com", LocalDate.now());
		personDto = new PersonDto(person.getId(), person.getName(), person.getEmail(), person.getBirthDate());

	}

	@Test
	public void savePersonTest() {

		when(repository.save(any(Person.class))).then(new Answer<Person>() {

			@Override
			public Person answer(InvocationOnMock invocation) throws Throwable {
				return person;
			}
		});
		
		when(service.save(any(PersonDto.class))).then(new Answer<PersonDto>() {

			@Override
			public PersonDto answer(InvocationOnMock invocation) throws Throwable {
				return personDto;
			}
		});

		Person response = repository.save(person);
		PersonDto responseDto = service.save(personDto);

		assertEquals("Gina", response.getName());
		assertEquals("gina@gmail.com", response.getEmail());
		assertEquals(1L, response.getId());
		
		assertEquals("Gina", responseDto.getName());
		assertEquals("gina@gmail.com", responseDto.getEmail());
		assertEquals(1L, responseDto.getId());
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
		
		when(service.findOne(1L)).thenReturn(personDto);

		Optional<Person> personReturnOptional = repository.findById(1L);
		Person personReturn = personReturnOptional.get();
		
		PersonDto personReturnDto = service.findOne(1L);
		
		verify(repository, times(1)).findById(1L);
		verify(service, times(1)).findOne(1L);
		
		assertEquals("Gina", personReturn.getName());
		assertEquals("gina@gmail.com", personReturn.getEmail());
		assertEquals(1L, personReturn.getId());
		
		assertEquals("Gina", personReturnDto.getName());
		assertEquals("gina@gmail.com", personReturnDto.getEmail());
		assertEquals(1L, personReturnDto.getId());
	}

}
