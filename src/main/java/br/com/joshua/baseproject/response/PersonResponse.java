package br.com.joshua.baseproject.response;

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
public class PersonResponse extends ResponseBase<Long> {

	private static final long serialVersionUID = 1L;

	String name;

	String email;

	LocalDate birthDate;

	GenderEnum gender;

	AddressResponse address;

}
