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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.joshua.baseproject.domain.Address;
import br.com.joshua.baseproject.domain.Person;
import br.com.joshua.baseproject.dto.PersonDto;
import br.com.joshua.baseproject.enums.GenderEnum;
import br.com.joshua.baseproject.repository.PersonRepository;
import br.com.joshua.baseproject.service.PersonService;

@SpringBootTest
class BaseprojectApplicationTests {

	@Mock(answer = Answers.RETURNS_SMART_NULLS)
	PersonRepository repository;

	@Mock(answer = Answers.RETURNS_SMART_NULLS)
	PersonService service;

	@Autowired
	private ModelMapper modelMapper;

	public Person person = null;
	public Address address = null;
	public PersonDto personDto = null;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		address = new Address(1L, "Street Test", "District Test", 123);
		person = new Person(1L, "Gina", "gina@gmail.com", LocalDate.now(), GenderEnum.MALE, address);
		personDto = modelMapper.map(person, PersonDto.class);

	}

	@Test
	public void should_savePerson_when_use_service() {

		when(service.save(any(PersonDto.class))).then(new Answer<PersonDto>() {

			@Override
			public PersonDto answer(InvocationOnMock invocation) throws Throwable {
				return personDto;
			}
		});

		PersonDto responseDto = service.save(personDto);

		assertEquals("Gina", responseDto.getName());
		assertEquals("gina@gmail.com", responseDto.getEmail());
		assertEquals(1L, responseDto.getId());
		assertEquals(GenderEnum.MALE, responseDto.getGender());
		assertEquals("Street Test", responseDto.getAddress().getStreet());
		assertEquals("District Test", responseDto.getAddress().getDistrict());
		assertEquals(123, responseDto.getAddress().getNumber());
	}

	@Test
	public void should_savePerson_when_use_repository() {

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
		assertEquals(GenderEnum.MALE, response.getGender());
		assertEquals("Street Test", response.getAddress().getStreet());
		assertEquals("District Test", response.getAddress().getDistrict());
		assertEquals(123, response.getAddress().getNumber());

	}

	@Test
	public void deletePersonTest() {
		Optional<Person> optionalPerson = Optional.of(person);
		when(repository.findById(1L)).thenReturn(optionalPerson);

		repository.deleteById(1L);

		verify(repository, times(1)).deleteById(1L);
	}

	@Test
	public void should_findPerson_when_use_service() {

		when(service.findOne(1L)).thenReturn(personDto);

		PersonDto personReturnDto = service.findOne(1L);

		verify(service, times(1)).findOne(1L);

		assertEquals("Gina", personReturnDto.getName());
		assertEquals("gina@gmail.com", personReturnDto.getEmail());
		assertEquals(1L, personReturnDto.getId());
		assertEquals(GenderEnum.MALE, personReturnDto.getGender());
		assertEquals("Street Test", personReturnDto.getAddress().getStreet());
		assertEquals("District Test", personReturnDto.getAddress().getDistrict());
		assertEquals(123, personReturnDto.getAddress().getNumber());
	}

	@Test
	public void should_findPerson_when_use_repository() {
		Optional<Person> optionalPerson = Optional.of(person);
		when(repository.findById(1L)).thenReturn(optionalPerson);

		Optional<Person> personReturnOptional = repository.findById(1L);
		Person personReturn = personReturnOptional.get();

		verify(repository, times(1)).findById(1L);

		assertEquals("Gina", personReturn.getName());
		assertEquals("gina@gmail.com", personReturn.getEmail());
		assertEquals(1L, personReturn.getId());
		assertEquals(GenderEnum.MALE, personReturn.getGender());
		assertEquals("Street Test", personReturn.getAddress().getStreet());
		assertEquals("District Test", personReturn.getAddress().getDistrict());
		assertEquals(123, personReturn.getAddress().getNumber());
	}

}
