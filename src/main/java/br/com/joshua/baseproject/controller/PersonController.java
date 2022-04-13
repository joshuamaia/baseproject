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

import br.com.joshua.baseproject.dto.PersonDto;
import br.com.joshua.baseproject.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

	@Autowired
	private PersonService service;

	@Operation(summary = "Search all Persons")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the List of Person", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonDto.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/list")
	public ResponseEntity<List<PersonDto>> getAll() {
		List<PersonDto> persons = service.getAll();
		return new ResponseEntity<List<PersonDto>>(persons, HttpStatus.OK);
	}

	@Operation(summary = "Search all Persons pagened")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of Person", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonPage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping(value = { "/{page}/{size}", "/{page}/{size}/{wordSearch}" })
	public ResponseEntity<Page<PersonDto>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable(required = false) String wordSearch) {
		Page<PersonDto> persons = service.searchAllPage(page, size, wordSearch);
		return new ResponseEntity<Page<PersonDto>>(persons, HttpStatus.OK);
	}

	@Operation(summary = "Search all Persons pagened")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of Person", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonPage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/filter")
	public ResponseEntity<Page<PersonDto>> filter(@RequestParam(required = false) String name,
			@RequestParam(required = false) String email, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {

		var persons = this.service.filter(name, email, page, size);
		return ResponseEntity.ok().body(persons);
	}

	@Operation(summary = "Create Person")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Person created with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PostMapping
	public ResponseEntity<PersonDto> create(@RequestBody PersonDto person) {
		PersonDto personSave = service.save(person);
		return new ResponseEntity<PersonDto>(personSave, HttpStatus.CREATED);
	}

	@Operation(summary = "Update Person")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Person updated with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PutMapping
	public ResponseEntity<PersonDto> update(@RequestBody PersonDto person) {
		PersonDto personUpdate = service.update(person);
		return new ResponseEntity<PersonDto>(personUpdate, HttpStatus.OK);
	}

	@Operation(summary = "Search Person By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Person", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<PersonDto> getOneById(@PathVariable Long id) {
		PersonDto person = service.findOne(id);
		return new ResponseEntity<PersonDto>(person, HttpStatus.OK);
	}

	@Operation(summary = "Delete Person By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "No Content", description = "Delete Person", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	class PersonPage extends PageImpl<PersonDto> {
		public PersonPage(List<PersonDto> content, Pageable pageable, long total) {
			super(content, pageable, total);
		}
	}
}
