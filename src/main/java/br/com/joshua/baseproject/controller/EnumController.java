package br.com.joshua.baseproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joshua.baseproject.enums.ExpenseEnum;
import br.com.joshua.baseproject.enums.GenderEnum;

@RestController
@RequestMapping("/api/enums")
public class EnumController {
	
	@GetMapping("/gender")
	public ResponseEntity<GenderEnum[]> getAllGeders() {
		GenderEnum[] genders = GenderEnum.values();
		return new ResponseEntity<GenderEnum[]>(genders, HttpStatus.OK);
	}
	
	@GetMapping("/expense")
	public ResponseEntity<ExpenseEnum[]> getAllExpenses() {
		ExpenseEnum[] genders = ExpenseEnum.values();
		return new ResponseEntity<ExpenseEnum[]>(genders, HttpStatus.OK);
	}

}
