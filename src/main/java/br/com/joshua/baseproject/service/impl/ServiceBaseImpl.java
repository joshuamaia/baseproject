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

import br.com.joshua.baseproject.service.ServiceBase;
import br.com.joshua.baseproject.util.Converte;

public abstract class ServiceBaseImpl<T, ID, E, R extends JpaRepository<E, ID>> implements ServiceBase<T, ID>, Converte<E, T> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	protected R repository;
	
	@Override
	public T save(T entity) {
		return convertFromDTO(repository.save(convertFromEntity(entity)));
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
