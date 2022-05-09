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
public class Usuario_empresa {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)                                                                                                                                     
    private int id;

    @NotNull   
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private  Usuario user;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "id")
    private Empresa empresa;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Usuario getUser() {
		return user;
	}


	public void setUser(Usuario user) {
		this.user = user;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public Usuario_empresa(@NotNull Usuario user, com.example.demo.Usuario.Entity.@NotNull Empresa empresa) {
		super();
		this.user = user;
		this.empresa = empresa;
	}


	public Usuario_empresa() {
		super();
	}

    
    
    

    
    
}
