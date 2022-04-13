package br.com.joshua.baseproject.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joshua.baseproject.dto.DtoBase;
import br.com.joshua.baseproject.service.ServiceBase;
import br.com.joshua.baseproject.util.Converte;

public abstract class ServiceBaseImpl<T extends DtoBase<ID>, ID extends Number, E, R extends JpaRepository<E, ID>> implements ServiceBase<T, ID>, Converte<E, T> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	protected R repository;
	
	@Override
	public T save(T dto) {
		return convertFromDTO(repository.save(convertFromEntity(dto)));
	}
	
	@Override
	public T update(T dto) {
		if (dto.getId() == null) {
			throw new RuntimeException("Id not present!");
		}
		Optional<E> e = repository.findById(dto.getId());
		if (!e.isPresent()) {
			throw new RuntimeException("Entity not present!");
		}
		return convertFromDTO(repository.save(convertFromEntity(dto)));
	}

	@Override
	public T findOne(ID id) {
		Optional<E> e = repository.findById(id);
		if (!e.isPresent()) {
			throw new RuntimeException("Entity not present!");
		}
		return convertFromDTO(e.get());
	}

	@Override
	public abstract Page<T> searchAllPage(Integer page, Integer size, String wordSearch);

	@Override
	public void delete(ID id) {
		this.repository.deleteById(id);		
	}

	@Override
	public List<T> getAll() {
		return repository.findAll().stream().map(this::convertFromDTO).collect(Collectors.toList());
	}
	
	@Override
	public E convertFromEntity(T dto) {
		return modelMapper.map(dto, getGenericClassType(2));
	}

	@Override
	public T convertFromDTO(E entity) {
		return modelMapper.map(entity, getGenericClassType(0));
	}

	private Type getGenericClassType(int index) {
        Type type = getClass().getGenericSuperclass();

        while (!(type instanceof ParameterizedType)) {
            if (type instanceof ParameterizedType) {
                type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
            } else {
                type = ((Class<?>) type).getGenericSuperclass();
            }
        }

        return ((ParameterizedType) type).getActualTypeArguments()[index];
    }

}
