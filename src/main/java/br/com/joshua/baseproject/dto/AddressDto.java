package br.com.joshua.baseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

	private Long id;

	private String street;

	private String district;

	private Integer number;

}
