package br.com.joshua.baseproject.util;

public interface Converte<E, T, Res> {
	public E convertFromEntity(T request);

	public Res convertFromResponse(E entity);
}
