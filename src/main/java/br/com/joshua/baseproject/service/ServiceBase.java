package br.com.joshua.baseproject.service;

import java.util.List;

public interface ServiceBase<T, ID> {

	T save(T entity);
	
	T findOne(ID id);
	
	List<T> findAll(Integer page, Integer size);
	
	void delete(ID id);
	
}
