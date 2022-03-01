package br.com.joshua.baseproject.util;

public interface Converte<E, DTO> {
	 public DTO convertFromEntity(E entity);
	 public E convertFromDTO(DTO dto);
}
