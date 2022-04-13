package br.com.joshua.baseproject.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.joshua.baseproject.enums.GenderEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
@FieldDefaults(level = AccessLevel.PROTECTED)
public class Person extends EntityBase<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(nullable = false, length = 100)
	String name;

	@NotEmpty
	@Column(nullable = false, length = 100)
	String email;

	@NotNull
	LocalDate birthDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	GenderEnum gender;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	Address address;
}
