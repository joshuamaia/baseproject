package br.com.joshua.baseproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joshua.baseproject.enums.ExpenseEnum;
import br.com.joshua.baseproject.enums.GenderEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/enums")
public class EnumController {
	
	@Operation(summary = "Search all Genders")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of Expenses", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GenderEnum.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/gender")
	public ResponseEntity<GenderEnum[]> getAllGeders() {
		GenderEnum[] genders = GenderEnum.values();
		return new ResponseEntity<GenderEnum[]>(genders, HttpStatus.OK);
	}
	
	@Operation(summary = "Search all Expenses")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of Expenses", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExpenseEnum.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/expense")
	public ResponseEntity<ExpenseEnum[]> getAllExpenses() {
		ExpenseEnum[] genders = ExpenseEnum.values();
		return new ResponseEntity<ExpenseEnum[]>(genders, HttpStatus.OK);
	}

}
