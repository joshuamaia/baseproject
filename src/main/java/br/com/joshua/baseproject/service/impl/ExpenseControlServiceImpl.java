package br.com.joshua.baseproject.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.joshua.baseproject.domain.ExpenseControl;
import br.com.joshua.baseproject.dto.ExpenseControlDto;
import br.com.joshua.baseproject.repository.ExpenseControlRepository;
import br.com.joshua.baseproject.service.ExpenseControlService;
import br.com.joshua.baseproject.util.Converte;

@Service
public class ExpenseControlServiceImpl implements ExpenseControlService, Converte<ExpenseControl, ExpenseControlDto> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ExpenseControlRepository repository;

	@Override
	public List<ExpenseControlDto> getAll() {
		return repository.findAll().stream().map(this::convertFromDTO).collect(Collectors.toList());
	}

	@Override
	public ExpenseControlDto save(ExpenseControlDto entity) {
		return convertFromDTO(repository.save(convertFromEntity(entity)));
	}

	@Override
	public ExpenseControlDto findOne(Long id) {
		Optional<ExpenseControl> ExpenseControl = repository.findById(id);
		if (!ExpenseControl.isPresent()) {
			throw new RuntimeException("Entity not present!");
		}
		return convertFromDTO(ExpenseControl.get());
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public Page<ExpenseControlDto> searchAllPage(Integer page, Integer size, String wordSearch) {
		PageRequest pageRequest = PageRequest.of(page, size);
		if (wordSearch == null || wordSearch.trim().isEmpty()) {
			return repository.findAll(pageRequest).map(this::convertFromDTO);
		}
		wordSearch = wordSearch.toLowerCase();
		return repository.searchAllPage(wordSearch, pageRequest).map(this::convertFromDTO);

	}

	@Override
	public ExpenseControl convertFromEntity(ExpenseControlDto dto) {
		return modelMapper.map(dto, ExpenseControl.class);
	}

	@Override
	public ExpenseControlDto convertFromDTO(ExpenseControl entity) {
		return modelMapper.map(entity, ExpenseControlDto.class);
	}

}
