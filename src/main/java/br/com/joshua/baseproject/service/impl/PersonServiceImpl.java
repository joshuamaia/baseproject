package br.com.joshua.baseproject.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.joshua.baseproject.domain.Person;
import br.com.joshua.baseproject.dto.PersonDto;
import br.com.joshua.baseproject.repository.PersonRepository;
import br.com.joshua.baseproject.service.PersonService;

@Service
public class PersonServiceImpl extends ServiceBaseImpl<PersonDto, Long, Person, PersonRepository>
		implements PersonService {

	@Override
	public Page<PersonDto> searchAllPage(Integer page, Integer size, String wordSearch) {
		PageRequest pageRequest = PageRequest.of(page, size);
		if (wordSearch == null || wordSearch.trim().isEmpty()) {
			return repository.findAll(pageRequest).map(this::convertFromDTO);
		}
		wordSearch = wordSearch.toLowerCase();
		return repository.searchAllPage(wordSearch, pageRequest).map(this::convertFromDTO);

	}

}
