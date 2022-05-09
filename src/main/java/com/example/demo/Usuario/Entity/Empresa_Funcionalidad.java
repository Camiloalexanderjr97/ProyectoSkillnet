package com.example.demo.Usuario.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Empresa_Funcionalidad {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)                                                                                                                                     
    private int id;

    @NotNull   
    @ManyToOne
    @JoinColumn(name = "funcionalidad", referencedColumnName = "id")
    private  Funcionalidad funcionalidad;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "id")
    private Empresa empresa;

	

	
	public Empresa_Funcionalidad() {
	}


	public Empresa_Funcionalidad(@NotNull Funcionalidad funcionalidad, @NotNull Empresa empresa) {
		this.funcionalidad = funcionalidad;
		this.empresa = empresa;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}





	public Funcionalidad getFuncionalidad() {
		return funcionalidad;
	}


	public void setFuncionalidad(Funcionalidad funcionalidad) {
		this.funcionalidad = funcionalidad;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	
    
}
