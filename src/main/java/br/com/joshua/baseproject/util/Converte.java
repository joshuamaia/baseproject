package br.com.joshua.baseproject.util;

public interface Converte<E, T> {
	public E convertFromEntity(T dto);

	public T convertFromDTO(E entity);
}
