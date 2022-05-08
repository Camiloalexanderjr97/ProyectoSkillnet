package com.example.demo.Usuario.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.example.demo.Usuario.Login.RolNombre;

@Entity
public class empresa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    
    
    
    
	public empresa() {
		super();
	}

	public empresa(int id, @NotNull String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public empresa( @NotNull String nombre) {
		super();
		this.nombre = nombre;
	}
	

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
   
    
    

}
