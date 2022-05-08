package br.com.joshua.baseproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import br.com.joshua.baseproject.enums.ExpenseEnum;
import br.com.joshua.baseproject.enums.GenderEnum;
import br.com.joshua.baseproject.repository.ExpenseControlRepository;
import br.com.joshua.baseproject.request.ExpenseControlRequest;
import br.com.joshua.baseproject.response.ExpenseControlResponse;
import br.com.joshua.baseproject.response.ExpenseSumResponse;
import br.com.joshua.baseproject.response.PersonResponse;
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
	private ExpenseControl expenseControl = null;
	private ExpenseControlResponse expenseControlResponse = null;
	private ExpenseControlRequest expenseControlRequest = null;
	List<ExpenseSumResponse> expenseSumDto = null;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		expenseSumDto = new ArrayList<ExpenseSumResponse>();
		expenseSumDto.add(new ExpenseSumResponse() {

			@Override
			public BigDecimal getValue() {
				return new BigDecimal(579.00);
			}

			@Override
			public String getExpense() {
				return "EXPENSE";
			}
		});
		expenseSumDto.add(new ExpenseSumResponse() {

			@Override
			public BigDecimal getValue() {
				return new BigDecimal(65.00);
			}

			@Override
			public String getExpense() {
				return "REVENUE";
			}
		});

		address = new Address("Street Test", "District Test", 123);
		address.setId(1L);
		person = new Person("Gina", "gina@gmail.com", LocalDate.now(), GenderEnum.MALE, address);
		person.setId(1L);
		expenseControl = new ExpenseControl(ExpenseEnum.EXPENSE, "Test", LocalDate.now(), new BigDecimal("50.0"),
				person);
		expenseControl.setId(1L);
		expenseControlRequest = new ExpenseControlRequest();
		expenseControlRequest.setId(1L);
		expenseControlResponse = modelMapper.map(expenseControl, ExpenseControlResponse.class);

	}

	@Test
	public void should_searchSumExpenseExpenseControl_when_use_repository() {

		when(repository.searchSumExpense(any(Long.class))).then(new Answer<List<ExpenseSumResponse>>() {

			@Override
			public List<ExpenseSumResponse> answer(InvocationOnMock invocation) throws Throwable {
				return expenseSumDto;
			}
		});
		
		List<ExpenseSumResponse> expenseSumDtoResponse = repository.searchSumExpense(2L);

		BigDecimal expense = expenseSumDtoResponse.stream().map(ecs -> {
			if (ecs.getExpense().equals("EXPENSE")) {
				return ecs.getValue();
			}
			return BigDecimal.ZERO;
		}).reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal revenue = expenseSumDtoResponse.stream().map(ecs -> {
			if (ecs.getExpense().equals("REVENUE")) {
				return ecs.getValue();
			}
			return BigDecimal.ZERO;
		}).reduce(BigDecimal.ZERO, BigDecimal::add);

		assertEquals(new BigDecimal("579"), expense);
		assertEquals(new BigDecimal("65"), revenue);

	}
	
	@Test
	public void should_searchSumExpenseExpenseControl_when_use_service() {

		when(service.searchSumExpense(any(Long.class))).then(new Answer<List<ExpenseSumResponse>>() {

			@Override
			public List<ExpenseSumResponse> answer(InvocationOnMock invocation) throws Throwable {
				return expenseSumDto;
			}
		});

		List<ExpenseSumResponse> expenseSumDtoResponse = service.searchSumExpense(2L);

		BigDecimal expense = expenseSumDtoResponse.stream().map(ecs -> {
			if (ecs.getExpense().equals("EXPENSE")) {
				return ecs.getValue();
			}
			return BigDecimal.ZERO;
		}).reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal revenue = expenseSumDtoResponse.stream().map(ecs -> {
			if (ecs.getExpense().equals("REVENUE")) {
				return ecs.getValue();
			}
			return BigDecimal.ZERO;
		}).reduce(BigDecimal.ZERO, BigDecimal::add);

		assertEquals(new BigDecimal("579"), expense);
		assertEquals(new BigDecimal("65"), revenue);

	}

	@Test
	public void should_saveExpenseControl_when_use_service() {

		when(service.save(any(ExpenseControlRequest.class))).then(new Answer<ExpenseControlResponse>() {

			@Override
			public ExpenseControlResponse answer(InvocationOnMock invocation) throws Throwable {
				return expenseControlResponse;
			}
		});

		ExpenseControlResponse responseDto = service.save(expenseControlRequest);

		assertEquals(1L, responseDto.getId());
		assertEquals(ExpenseEnum.EXPENSE, responseDto.getExpense());
		assertEquals("Test", responseDto.getDescription());
		assertEquals(new BigDecimal("50.0"), responseDto.getValue());
	}

	@Test
	public void should_saveExpenseControl_when_use_repository() {

		when(repository.save(any(ExpenseControl.class))).then(new Answer<ExpenseControl>() {

			@Override
			public ExpenseControl answer(InvocationOnMock invocation) throws Throwable {
				return expenseControl;
			}
		});

		ExpenseControl response = repository.save(expenseControl);

		assertEquals(1L, response.getId());
		assertEquals(ExpenseEnum.EXPENSE, response.getExpense());
		assertEquals("Test", response.getDescription());
		assertEquals(new BigDecimal("50.0"), response.getValue());

	}

	@Test
	public void should_deleteExpenseControl_when_use_repository() {
		Optional<ExpenseControl> optionalExpenseControl = Optional.of(expenseControl);
		when(repository.findById(1L)).thenReturn(optionalExpenseControl);

		repository.deleteById(1L);

		verify(repository, times(1)).deleteById(1L);
	}

	@Test
	public void should_findExpenseControl_when_use_service() {

		when(service.findOne(1L)).thenReturn(expenseControlResponse);

		ExpenseControlResponse expenseControlReturnDto = service.findOne(1L);

		verify(service, times(1)).findOne(1L);

		assertEquals(1L, expenseControlReturnDto.getId());
		assertEquals(ExpenseEnum.EXPENSE, expenseControlReturnDto.getExpense());
		assertEquals("Test", expenseControlReturnDto.getDescription());
		assertEquals(new BigDecimal("50.0"), expenseControlReturnDto.getValue());
	}

	@Test
	public void should_findExpenseControl_when_use_repository() {
		Optional<ExpenseControl> optionalExpenseControl = Optional.of(expenseControl);
		when(repository.findById(1L)).thenReturn(optionalExpenseControl);

		Optional<ExpenseControl> expenseControlReturnOptional = repository.findById(1L);
		ExpenseControl expenseControlReturn = expenseControlReturnOptional.get();

		verify(repository, times(1)).findById(1L);

		assertEquals(1L, expenseControlReturn.getId());
		assertEquals(ExpenseEnum.EXPENSE, expenseControlReturn.getExpense());
		assertEquals("Test", expenseControlReturn.getDescription());
		assertEquals(new BigDecimal("50.0"), expenseControlReturn.getValue());
	}

}
