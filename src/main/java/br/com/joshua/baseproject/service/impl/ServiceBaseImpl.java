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

import br.com.joshua.baseproject.request.RequestBase;
import br.com.joshua.baseproject.service.ServiceBase;
import br.com.joshua.baseproject.util.Converte;

public abstract class ServiceBaseImpl<Res, T extends RequestBase<ID>, ID extends Number, E, R extends JpaRepository<E, ID>> implements ServiceBase<Res, T, ID>, Converte<E, T, Res> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	protected R repository;
	
	@Override
	public Res save(T request) {
		return convertFromResponse(repository.save(convertFromEntity(request)));
	}
	
	@Override
	public Res update(T request) {
		if (request.getId() == null) {
			throw new RuntimeException("Id not present!");
		}
		Optional<E> e = repository.findById(request.getId());
		if (!e.isPresent()) {
			throw new RuntimeException("Entity not present!");
		}
		return convertFromResponse(repository.save(convertFromEntity(request)));
	}

	@Override
	public Res findOne(ID id) {
		Optional<E> e = repository.findById(id);
		if (!e.isPresent()) {
			throw new RuntimeException("Entity not present!");
		}
		return convertFromResponse(e.get());
	}

	@Override
	public abstract Page<Res> searchAllPage(Integer page, Integer size, String wordSearch);

	@Override
	public void delete(ID id) {
		this.repository.deleteById(id);		
	}

	@Override
	public List<Res> getAll() {
		return repository.findAll().stream().map(this::convertFromResponse).collect(Collectors.toList());
	}
	
	@Override
	public E convertFromEntity(T dto) {
		return modelMapper.map(dto, getGenericClassType(3));
	}

	@Override
	public Res convertFromResponse(E entity) {
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
