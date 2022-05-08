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
public class usuario_empresa {
	

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
    private empresa empresa;


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


	public empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(empresa empresa) {
		this.empresa = empresa;
	}


	public usuario_empresa(@NotNull Usuario user, com.example.demo.Usuario.Entity.@NotNull empresa empresa) {
		super();
		this.user = user;
		this.empresa = empresa;
	}


	public usuario_empresa() {
		super();
	}

    
    
    

    
    
}
