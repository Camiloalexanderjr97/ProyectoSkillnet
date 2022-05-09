package com.example.demo.Usuario.Service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Usuario.Entity.Usuario;
import com.example.demo.Usuario.Entity.Empresa;
import com.example.demo.Usuario.Entity.Empresa_Funcionalidad;
import com.example.demo.Usuario.Entity.Funcionalidad;
import com.example.demo.Usuario.Entity.Usuario_empresa;
import com.example.demo.Usuario.Repository.Empresa_FuncionalidadRepository;
import com.example.demo.Usuario.Repository.Empresa_UsuarioRepository;
import com.example.demo.Usuario.Repository.empresaRepository;

	@Service
	@Transactional
	public class Empresa_FuncionalidadService {
	    
		@Autowired
		Empresa_FuncionalidadRepository empresa_FuncionalidadRepository;
	    // public abstract UsuarioModel getUsusarioUsername(String username);

	   

	    public List<Funcionalidad> getEmpresa_FuncionalidadByIDEmpresa(int id){
	    	List<Empresa_Funcionalidad> listado= empresa_FuncionalidadRepository.findAll();

	    	List<Funcionalidad> funcionalidades = new ArrayList<>();
	    	
	    	for(Empresa_Funcionalidad lista : listado) {
	    		if(lista.getEmpresa().getId()==id) {
	    			funcionalidades.add(lista.getFuncionalidad());
	    		}
	    	}
			return funcionalidades;
	    }
	    

	    public boolean getFuncionalidad_Empresa(int fun_id, int empresa_id ){
	    	List<Empresa_Funcionalidad> listado= empresa_FuncionalidadRepository.findAll();
	    		
	    	boolean respuesta =false;
	    	for(Empresa_Funcionalidad lista : listado) {
	    		if(lista.getFuncionalidad().getId()==fun_id && lista.getEmpresa().getId()==empresa_id) {
	    			respuesta=true;
	    		}
	    	}
			return respuesta;
	    }
	    
	    public List<Empresa_Funcionalidad> listado(){
	    	return empresa_FuncionalidadRepository.findAll();
	    }
	    
	    
	    
	    public boolean save(Empresa_Funcionalidad empr)
	    { boolean rta=false;
	    try {
	    	if(empr!=null) {
	    		
	    	
				empresa_FuncionalidadRepository.save(empr);
	    	rta=true;
	    	
	    	}
		} catch (Exception e) {
			// TODO: handle exception
		}
	    return rta;
	    }
	    
	
}
