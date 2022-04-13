package br.com.joshua.baseproject.dto;

import java.time.LocalDate;

import br.com.joshua.baseproject.enums.GenderEnum;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@ToString
@FieldDefaults(level = AccessLevel.PROTECTED)
public class PersonDto extends DtoBase<Long> {

	private static final long serialVersionUID = 1L;

	Long id;

	String name;

	String email;

	LocalDate birthDate;

	GenderEnum gender;

	AddressDto address;

}
