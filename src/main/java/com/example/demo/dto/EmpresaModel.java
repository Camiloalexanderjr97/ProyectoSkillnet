package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

public class EmpresaModel {

	

	@NotEmpty
	private int id;

	@NotEmpty
    private String Nombre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	
	
}
