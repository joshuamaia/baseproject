package br.com.joshua.baseproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joshua.baseproject.enums.GenderEnum;

@RestController
@RequestMapping("/api/enums")
public class EnumController {
	
	@GetMapping("/gender")
	public ResponseEntity<GenderEnum[]> getAll() {
		GenderEnum[] genders = GenderEnum.values();
		return new ResponseEntity<GenderEnum[]>(genders, HttpStatus.OK);
	}

}
