package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

public class FuncionalidadModel {

	

	@NotEmpty
	private int id;

	@NotEmpty
    private String Nombre;

	private String Descripcion;

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

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	
	
}
