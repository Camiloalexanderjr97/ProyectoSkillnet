package com.example.demo.Usuario.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Usuario.Entity.Usuario;
import com.example.demo.Usuario.Entity.Empresa;
import com.example.demo.Usuario.Repository.empresaRepository;

	@Service
	@Transactional
	public class EmpresaService {
	    
		@Autowired
		empresaRepository empresaRepositor;
	    // public abstract UsuarioModel getUsusarioUsername(String username);

	   

	    public boolean getEmpresaByNombre(String nombre){
	    	
	    	boolean resultado=false;
	    	List<Empresa> empr =  	empresaRepositor.findAll();
	    	for(Empresa lista : empr) {
	    		if(lista.getNombre().equalsIgnoreCase(nombre)) {
	    			resultado=true;
	    		}
	    	}
			return resultado;
	    }
	    
	    
	    
	    
	    public boolean save(Empresa empr)
	    { boolean rta=false;
	    try {

	    	empresaRepositor.save(empr);
	    	rta=true;
		} catch (Exception e) {
			// TODO: handle exception
		}
	    return rta;
	    }
	    
	    public Empresa buscarEmpresaById(long id) {
		
			
			return empresaRepositor.findById(id).get();
		}
	
}
