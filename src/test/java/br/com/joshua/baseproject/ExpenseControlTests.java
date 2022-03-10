package br.com.joshua.baseproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
import br.com.joshua.baseproject.domain.ExpenseControl;
import br.com.joshua.baseproject.domain.Person;
import br.com.joshua.baseproject.dto.ExpenseControlDto;
import br.com.joshua.baseproject.dto.PersonDto;
import br.com.joshua.baseproject.enums.ExpenseEnum;
import br.com.joshua.baseproject.enums.GenderEnum;
import br.com.joshua.baseproject.repository.ExpenseControlRepository;
import br.com.joshua.baseproject.service.ExpenseControlService;

@SpringBootTest
class ExpenseControlTests {

	@Mock(answer = Answers.RETURNS_SMART_NULLS)
	ExpenseControlRepository repository;

	@Mock(answer = Answers.RETURNS_SMART_NULLS)
	ExpenseControlService service;

	@Autowired
	private ModelMapper modelMapper;

	private Person person = null;
	private Address address = null;
	private PersonDto personDto = null;
	private ExpenseControl expenseControl = null;
	private ExpenseControlDto expenseControlDto = null;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		address = new Address(1L, "Street Test", "District Test", 123);
		person = new Person(1L, "Gina", "gina@gmail.com", LocalDate.now(), GenderEnum.MALE, address);
		personDto = modelMapper.map(person, PersonDto.class);
		expenseControl = new ExpenseControl(1L, ExpenseEnum.EXPENSE, "Test", LocalDate.now(), new BigDecimal("50.0"),
				person);
		expenseControlDto = modelMapper.map(expenseControl, ExpenseControlDto.class);

	}

	@Test
	public void should_saveExpenseControl_when_use_service() {

		when(service.save(any(ExpenseControlDto.class))).then(new Answer<ExpenseControlDto>() {

			@Override
			public ExpenseControlDto answer(InvocationOnMock invocation) throws Throwable {
				return expenseControlDto;
			}
		});

		ExpenseControlDto responseDto = service.save(expenseControlDto);

		assertEquals(1L, responseDto.getId());
		assertEquals(ExpenseEnum.EXPENSE, responseDto.getExpense());
		assertEquals("Test", responseDto.getDescription());
		assertEquals(new BigDecimal("50.0"), responseDto.getValue());
	}

}
