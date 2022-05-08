package br.com.joshua.baseproject.service;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ServiceBase<Res, T, ID> {

	Res save(T request);
	
	Res update(T request);
	
	Res findOne(ID id);
	
	Page<Res> searchAllPage(Integer page, Integer size, String wordSearch);
	
	void delete(ID id);
	
	List<Res> getAll();
	
}
