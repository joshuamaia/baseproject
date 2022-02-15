package br.com.joshua.baseproject.service;

import org.springframework.data.domain.Page;

public interface ServiceBase<T, ID> {

	T save(T entity);
	
	T findOne(ID id);
	
	Page<T> searchAllPage(Integer page, Integer size, String wordSearch);
	
	void delete(ID id);
	
}
