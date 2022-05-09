package com.example.demo.Usuario.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Usuario.Entity.Usuario;
import com.example.demo.Usuario.Entity.Empresa;
import com.example.demo.Usuario.Entity.Funcionalidad;
import com.example.demo.Usuario.Repository.FuncionalidadRepository;
import com.example.demo.Usuario.Repository.empresaRepository;

	@Service
	@Transactional
	public class FuncionalidadService {
	    
		@Autowired
		FuncionalidadRepository funcionalidadRepository;
	    // public abstract UsuarioModel getUsusarioUsername(String username);

	   

	    public boolean getFuncionalidadByNombre(String nombre){
	    	
	    	boolean resultado=false;
	    	List<Funcionalidad> empr =  	funcionalidadRepository.findAll();
	    	for(Funcionalidad lista : empr) {
	    		if(lista.getNombre().equalsIgnoreCase(nombre)) {
	    			resultado=true;
	    		}
	    	}
			return resultado;
	    }
	    
	    
	    
	    
	    public boolean save(Funcionalidad fun)
	    { boolean rta=false;
	    try {

	    	funcionalidadRepository.save(fun);
	    	rta=true;
		} catch (Exception e) {
			// TODO: handle exception
		}
	    return rta;
	    }
	    
	    public Funcionalidad buscarFuncionalidadById(long id) {
		
			
			return funcionalidadRepository.findById(id).get();
		}
	
}
