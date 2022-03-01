package br.com.joshua.baseproject.util;

public interface Converte<E, DTO> {
	public E convertFromEntity(DTO dto);

	public DTO convertFromDTO(E entity);
}
