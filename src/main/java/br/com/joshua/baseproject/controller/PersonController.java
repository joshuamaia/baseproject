package br.com.joshua.baseproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.joshua.baseproject.dto.PersonDto;
import br.com.joshua.baseproject.service.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

	@Autowired
	private PersonService service;

	@GetMapping("/{page}/{size}")
	public ResponseEntity<List<PersonDto>> getAll(@PathVariable Integer page, @PathVariable Integer size) {
		List<PersonDto> persons = service.findAll(page, size);
		return new ResponseEntity<List<PersonDto>>(persons, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PersonDto> create(@RequestBody PersonDto person) {
		PersonDto personSave = service.save(person);
		return new ResponseEntity<PersonDto>(personSave, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PersonDto> update(@RequestBody PersonDto person) {
		PersonDto personUpdate = service.save(person);
		return new ResponseEntity<PersonDto>(personUpdate, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonDto> getOneById(@PathVariable(required = false) Long id) {
		PersonDto person = service.findOne(id);
		return new ResponseEntity<PersonDto>(person, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(required = false) Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
