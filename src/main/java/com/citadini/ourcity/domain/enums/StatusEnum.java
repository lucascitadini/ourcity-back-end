package com.citadini.ourcity.domain.enums;

public enum StatusEnum {
	PENDING(1, "Pending"),
	FINISHED(2, "Finished");
	
	private final int cod;
	private final String description;
	
	private StatusEnum(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
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
		
		throw new IllegalArgumentException("Invalid Code: " + cod);
	}
}
