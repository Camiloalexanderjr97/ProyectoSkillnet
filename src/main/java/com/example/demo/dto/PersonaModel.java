package com.example.demo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;


import com.example.demo.Usuario.Entity.Rol;
import com.example.demo.Usuario.Entity.Usuario;

public class PersonaModel {
	
	@NotEmpty
	private Long id;

	@NotEmpty
    private String name;

	@NotEmpty
    private String documento;

	
	@NotEmpty
    private String tipoDocumento;
	

    private Date fechaCreacion;
	
	private long creador;
	



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getCreador() {
		return creador;
	}

	public void setCreador(long creador) {
		this.creador = creador;
	}

    
    
}
