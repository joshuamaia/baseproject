package br.com.joshua.baseproject.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.joshua.baseproject.domain.Person;
import br.com.joshua.baseproject.dto.PersonDto;
import br.com.joshua.baseproject.repository.PersonRepository;
import br.com.joshua.baseproject.service.PersonService;
import br.com.joshua.baseproject.util.Conveter;

@Service
public class PersonServiceImpl implements PersonService, Conveter<Person, PersonDto> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PersonRepository repository;

	@Override
	public PersonDto save(PersonDto entity) {
		Person person = convertFromDTO(entity);
		person = repository.save(person);
		return convertFromEntity(person);
	}

	@Override
	public PersonDto findOne(Long id) {
		Optional<Person> person = repository.findById(id);
		if (!person.isPresent()) {
			throw new RuntimeException("Entity not present!");
		}
		return convertFromEntity(person.get());
	}

	@Override
	public List<PersonDto> findAll(Integer page, Integer size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return this.repository.findAll(pageRequest).stream().map(p -> convertFromEntity(p)).collect(Collectors.toList());
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public PersonDto convertFromEntity(Person entity) {
		return modelMapper.map(entity, PersonDto.class);
	}

	@Override
	public Person convertFromDTO(PersonDto dto) {
		return modelMapper.map(dto, Person.class);
	}

}
