package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

public class Funcionalidad_EmpresaModel {

	

	@NotEmpty
	private int id;

	@NotEmpty
    private int empresa_Id;
	
	@NotEmpty
    private int funcionalidad_id;

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

	public int getFuncionalidad_id() {
		return funcionalidad_id;
	}

	public void setFuncionalidad_id(int funcionalidad_id) {
		this.funcionalidad_id = funcionalidad_id;
	}

	
	
}
