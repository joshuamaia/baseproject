package br.com.joshua.baseproject.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.joshua.baseproject.domain.Person;
import br.com.joshua.baseproject.dto.PersonDto;
import br.com.joshua.baseproject.repository.PersonRepository;
import br.com.joshua.baseproject.service.PersonService;
import br.com.joshua.baseproject.util.Converte;

@Service
public class PersonServiceImpl implements PersonService, Converte<Person, PersonDto> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PersonRepository repository;

	@Override
	public PersonDto save(PersonDto entity) {
		return convertFromDTO(repository.save(convertFromEntity(entity)));
	}

	@Override
	public PersonDto findOne(Long id) {
		Optional<Person> person = repository.findById(id);
		if (!person.isPresent()) {
			throw new RuntimeException("Entity not present!");
		}
		return convertFromDTO(person.get());
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public Page<PersonDto> searchAllPage(Integer page, Integer size, String wordSearch) {
		PageRequest pageRequest = PageRequest.of(page, size);
		if (wordSearch == null || wordSearch.trim().isEmpty()) {
			return repository.findAll(pageRequest).map(this::convertFromDTO);
		}
		wordSearch = wordSearch.toLowerCase();
		return repository.searchAllPage(wordSearch, pageRequest).map(this::convertFromDTO);

	}

	@Override
	public Person convertFromEntity(PersonDto dto) {
		return modelMapper.map(dto, Person.class);
	}

	@Override
	public PersonDto convertFromDTO(Person entity) {
		return modelMapper.map(entity, PersonDto.class);
	}

	

}
