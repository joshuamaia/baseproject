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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.joshua.baseproject.request.ExpenseControlRequest;
import br.com.joshua.baseproject.response.ExpenseControlResponse;
import br.com.joshua.baseproject.response.ExpenseSumResponse;
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
	
	@Operation(summary = "Search the sum of expenses")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the Expense Sum", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseSumResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/expense-sum")
	public ResponseEntity<List<ExpenseSumResponse>> searchSumExpenseTotal() {
		List<ExpenseSumResponse> expenseSum = service.searchSumExpenseTotal();
		return new ResponseEntity<List<ExpenseSumResponse>>(expenseSum, HttpStatus.OK);
	}

	@Operation(summary = "Search the sum of expenses by the person's id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the Expense Sum", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseSumResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/expense-sum/{personId}")
	public ResponseEntity<List<ExpenseSumResponse>> expenseSumByPersonId(@PathVariable("personId") Long personId) {
		List<ExpenseSumResponse> expenseSum = service.searchSumExpense(personId);
		return new ResponseEntity<List<ExpenseSumResponse>>(expenseSum, HttpStatus.OK);
	}

	@Operation(summary = "Search all Expense Controls")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of Expense Control", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExpenseControlResponse.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/list")
	public ResponseEntity<List<ExpenseControlResponse>> getAll() {
		List<ExpenseControlResponse> expenseControls = service.getAll();
		return new ResponseEntity<List<ExpenseControlResponse>>(expenseControls, HttpStatus.OK);
	}

	@Operation(summary = "Search all Expense Controls by page")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Expense Sum", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseControlPage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping(value = { "/{page}/{size}", "/{page}/{size}/{wordSearch}" })
	public ResponseEntity<Page<ExpenseControlResponse>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable(required = false) String wordSearch) {
		Page<ExpenseControlResponse> expenseControls = service.searchAllPage(page, size, wordSearch);
		return new ResponseEntity<Page<ExpenseControlResponse>>(expenseControls, HttpStatus.OK);
	}

	@Operation(summary = "Search all Expense Controls pagened")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of Expense Control", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseControlPage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/filter")
	public ResponseEntity<Page<ExpenseControlResponse>> filter(@RequestParam(required = false) String description,
			@RequestParam(required = false) String name, @RequestParam(required = false) String email,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {

		var expenseControls = this.service.filter(description, name, email, page, size);
		return ResponseEntity.ok().body(expenseControls);
	}

	@Operation(summary = "Create Expense Control")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Expense Control created with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseControlResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PostMapping
	public ResponseEntity<ExpenseControlResponse> create(@RequestBody ExpenseControlRequest expenseControl) {
		ExpenseControlResponse expenseControlSave = service.save(expenseControl);
		return new ResponseEntity<ExpenseControlResponse>(expenseControlSave, HttpStatus.CREATED);
	}

	@Operation(summary = "Update Expense Control")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Expense Control updated with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseControlResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PutMapping
	public ResponseEntity<ExpenseControlResponse> update(@RequestBody ExpenseControlRequest expenseControl) {
		ExpenseControlResponse expenseControlUpdate = service.update(expenseControl);
		return new ResponseEntity<ExpenseControlResponse>(expenseControlUpdate, HttpStatus.OK);
	}

	@Operation(summary = "Search Expense Control By id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the Expense Control", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseControlResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<ExpenseControlResponse> getOneById(@PathVariable Long id) {
		ExpenseControlResponse expenseControl = service.findOne(id);
		return new ResponseEntity<ExpenseControlResponse>(expenseControl, HttpStatus.OK);
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

	class ExpenseControlPage extends PageImpl<ExpenseControlResponse> {
		public ExpenseControlPage(List<ExpenseControlResponse> content, Pageable pageable, long total) {
			super(content, pageable, total);
		}
	}
}
