package br.com.joshua.baseproject.service;

import java.util.List;

public interface ServiceBase<T, ID> {

	T save(T entity);
	
	T findOne(ID id);
	
	List<T> findAll();
	
	void delete(ID id);
	
}
