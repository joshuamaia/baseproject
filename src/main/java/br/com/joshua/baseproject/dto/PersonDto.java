package br.com.joshua.baseproject.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
	
	private Long id;
	
	private String name;

	private String email;
	
	private LocalDate birthDate;

}
