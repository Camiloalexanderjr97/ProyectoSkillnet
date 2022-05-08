package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

public class Empresa_UsuarioModel {

	

	@NotEmpty
	private int id;

	@NotEmpty
    private int empresa_Id;
	
	@NotEmpty
    private int usuario_Id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpresa_Id() {
		return empresa_Id;
	}

	public void setEmpresa_Id(int empresa_Id) {
		this.empresa_Id = empresa_Id;
	}

	public int getUsuario_Id() {
		return usuario_Id;
	}

	public void setUsuario_Id(int usuario_Id) {
		this.usuario_Id = usuario_Id;
	}

	

	
	
}
