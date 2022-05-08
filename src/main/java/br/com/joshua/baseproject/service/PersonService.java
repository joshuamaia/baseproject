package br.com.joshua.baseproject.service;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.joshua.baseproject.request.PersonRequest;
import br.com.joshua.baseproject.response.PersonResponse;

public interface PersonService extends ServiceBase<PersonResponse, PersonRequest, Long> {

	public Page<PersonResponse> filter(
			@Nullable String name,
			@Nullable String email,
			@Nullable Integer page,
			@Nullable Integer size);
	
}
