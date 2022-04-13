package br.com.joshua.baseproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joshua.baseproject.dto.ExpenseControlDto;
import br.com.joshua.baseproject.dto.ExpenseSumDto;
import br.com.joshua.baseproject.service.ExpenseControlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/expense-controls")
public class ExpenseControlController {

	@Autowired
	private ExpenseControlService service;

	@Operation(summary = "Search the sum of expenses by the person's id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Expense Sum", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseSumDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/expense-sum/{personId}")
	public ResponseEntity<List<ExpenseSumDto>> expenseSumByPersonId(@PathVariable("personId") Long personId) {
		List<ExpenseSumDto> expenseSum = service.searchSumExpense(personId);
		return new ResponseEntity<List<ExpenseSumDto>>(expenseSum, HttpStatus.OK);
	}

	@Operation(summary = "Search all Expense Controls")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of Expense Control", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExpenseControlDto.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/list")
	public ResponseEntity<List<ExpenseControlDto>> getAll() {
		List<ExpenseControlDto> expenseControls = service.getAll();
		return new ResponseEntity<List<ExpenseControlDto>>(expenseControls, HttpStatus.OK);
	}

	@Operation(summary = "Search all Expense Controls by page")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Expense Sum", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseControlPage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping(value = { "/{page}/{size}", "/{page}/{size}/{wordSearch}" })
	public ResponseEntity<Page<ExpenseControlDto>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable(required = false) String wordSearch) {
		Page<ExpenseControlDto> expenseControls = service.searchAllPage(page, size, wordSearch);
		return new ResponseEntity<Page<ExpenseControlDto>>(expenseControls, HttpStatus.OK);
	}

	@Operation(summary = "Create Expense Control")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Expense Control created with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseControlDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PostMapping
	public ResponseEntity<ExpenseControlDto> create(@RequestBody ExpenseControlDto expenseControl) {
		ExpenseControlDto expenseControlSave = service.save(expenseControl);
		return new ResponseEntity<ExpenseControlDto>(expenseControlSave, HttpStatus.CREATED);
	}

	@Operation(summary = "Update Expense Control")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Expense Control updated with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseControlDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PutMapping
	public ResponseEntity<ExpenseControlDto> update(@RequestBody ExpenseControlDto expenseControl) {
		ExpenseControlDto expenseControlUpdate = service.update(expenseControl);
		return new ResponseEntity<ExpenseControlDto>(expenseControlUpdate, HttpStatus.OK);
	}

	@Operation(summary = "Search Expense Control By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Expense Control", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseControlDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<ExpenseControlDto> getOneById(@PathVariable Long id) {
		ExpenseControlDto expenseControl = service.findOne(id);
		return new ResponseEntity<ExpenseControlDto>(expenseControl, HttpStatus.OK);
	}

	@Operation(summary = "Delete Expense Control By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "No Content", description = "Delete Expense Control", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	class ExpenseControlPage extends PageImpl<ExpenseControlDto> {
		public ExpenseControlPage(List<ExpenseControlDto> content, Pageable pageable, long total) {
			super(content, pageable, total);
		}
	}
}
