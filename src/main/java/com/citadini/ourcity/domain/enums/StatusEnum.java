package com.citadini.ourcity.domain.enums;

public enum StatusEnum {
	CRIADOR(1, "Pendente"),
	FINALIZADO(2, "Finalizado");
	
	private int cod;
	private String descricao;
	
	private StatusEnum(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static StatusEnum toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (StatusEnum x : StatusEnum.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
