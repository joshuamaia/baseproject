package br.com.joshua.baseproject.controller;

import static org.springframework.http.MediaType.APPLICATION_PDF;
import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joshua.baseproject.service.PersonReportService;

@RestController
@RequestMapping("/api/persons-reports")
public class PersonReportController {

	public static final String X_SUGGESTED_FILENAME_HEADER = "X-SUGGESTED-FILENAME";

	@Autowired
	private PersonReportService service;

	@GetMapping("/pdf")
	public ResponseEntity<byte[]> generateReportPersons()  {

		byte[] relatorio = service.generatePersonReport();
		return ok().contentType(APPLICATION_PDF) //
				.header(X_SUGGESTED_FILENAME_HEADER, "person.pdf")
				.body(relatorio);
	}

}
