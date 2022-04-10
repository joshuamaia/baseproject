package br.com.joshua.baseproject.service;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.joshua.baseproject.dto.PersonDto;

public interface PersonService extends ServiceBase<PersonDto, Long> {

	public Page<PersonDto> filter(
			@Nullable String name,
			@Nullable String email,
			@Nullable Integer page,
			@Nullable Integer size);
	
}
