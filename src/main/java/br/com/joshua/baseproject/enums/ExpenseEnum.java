package br.com.joshua.baseproject.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExpenseEnum {

	EXPENSE("Expense"),
	REVENUE("Revenue");
	
	String description;
	
	ExpenseEnum(String description){
		this.description = description;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
	
	@JsonCreator
	public
	   static ExpenseEnum findValue(@JsonProperty("description") String descricao){
	      return Arrays.stream(ExpenseEnum.values()).filter(pt -> pt.description.equals(descricao)).findFirst().get();
	}
	
}
