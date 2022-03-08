package br.com.joshua.baseproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import br.com.joshua.baseproject.service.ExpenseControlService;

@RestController
@RequestMapping("/api/expense-controls")
public class ExpenseControlController {

	@Autowired
	private ExpenseControlService service;

	@GetMapping("/list")
	public ResponseEntity<List<ExpenseControlDto>> getAll() {
		List<ExpenseControlDto> expenseControls = service.getAll();
		return new ResponseEntity<List<ExpenseControlDto>>(expenseControls, HttpStatus.OK);
	}

	@GetMapping("/{page}/{size}/{wordSearch}")
	public ResponseEntity<Page<ExpenseControlDto>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable String wordSearch) {
		Page<ExpenseControlDto> expenseControls = service.searchAllPage(page, size, wordSearch);
		return new ResponseEntity<Page<ExpenseControlDto>>(expenseControls, HttpStatus.OK);
	}

	@GetMapping("/{page}/{size}")
	public ResponseEntity<Page<ExpenseControlDto>> getAll(@PathVariable Integer page, @PathVariable Integer size) {
		Page<ExpenseControlDto> expenseControls = service.searchAllPage(page, size, null);
		return new ResponseEntity<Page<ExpenseControlDto>>(expenseControls, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ExpenseControlDto> create(@RequestBody ExpenseControlDto expenseControl) {
		ExpenseControlDto expenseControlSave = service.save(expenseControl);
		return new ResponseEntity<ExpenseControlDto>(expenseControlSave, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ExpenseControlDto> update(@RequestBody ExpenseControlDto expenseControl) {
		ExpenseControlDto expenseControlUpdate = service.save(expenseControl);
		return new ResponseEntity<ExpenseControlDto>(expenseControlUpdate, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExpenseControlDto> getOneById(@PathVariable(required = false) Long id) {
		ExpenseControlDto expenseControl = service.findOne(id);
		return new ResponseEntity<ExpenseControlDto>(expenseControl, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(required = false) Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
