package br.com.joshua.baseproject.dto;

import java.time.LocalDate;

import br.com.joshua.baseproject.enums.GenderEnum;
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
	
	private GenderEnum gender;
	
	private AddressDto address;

}
