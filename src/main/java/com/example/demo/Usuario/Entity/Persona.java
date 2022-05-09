package com.example.demo.Usuario.Entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)                                                                                                                                     
    private long id;

    @NotNull
    @Column(name = "nombre")
    private String nombre;


    @NotNull
    @Column(name = "documento")
    private String documento;

    @NotNull
    @Column(name = "tipoDocumento")
    private String tipoDocumento;

	@NotNull
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;

	@NotNull
	@ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario creador;


	

	public Persona() {
	}

	

	


	public Persona(@NotNull String nombre, @NotNull String documento, 
			@NotNull String tipoDocumento, @NotNull Date fechaCreacion, @NotNull Usuario creador) {
		this.nombre = nombre;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
		this.fechaCreacion = fechaCreacion;
		this.creador = creador;
	}






	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return nombre;
	}

	public void setName(String nombre) {
		this.nombre = nombre;
	}



	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}






	public Date getFechaCreacion() {
		return fechaCreacion;
	}






	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}






	public Usuario getCreador() {
		return creador;
	}






	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

  
 
}