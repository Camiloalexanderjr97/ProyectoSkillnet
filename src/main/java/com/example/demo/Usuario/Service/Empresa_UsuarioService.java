package com.example.demo.Usuario.Service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Usuario.Entity.Usuario;
import com.example.demo.Usuario.Entity.empresa;
import com.example.demo.Usuario.Entity.usuario_empresa;
import com.example.demo.Usuario.Repository.Empresa_UsuarioRepository;
import com.example.demo.Usuario.Repository.empresaRepository;

	@Service
	@Transactional
	public class Empresa_UsuarioService {
	    
		@Autowired
		Empresa_UsuarioRepository empresa_UsuarioRepository;
	    // public abstract UsuarioModel getUsusarioUsername(String username);

	   

	    public List<String> getUsuario_EmpresByUserID(int id){
	    	List<usuario_empresa> listado= empresa_UsuarioRepository.findAll();

	    	List<String> listadoEmpresa = new ArrayList<>();
	    	
	    	for(usuario_empresa lista : listado) {
	    		if(lista.getUser().getId()==id) {
	    			listadoEmpresa.add(lista.getEmpresa().getNombre());
	    		}
	    	}
			return listadoEmpresa;
	    }
	    

	    public boolean getUsuario_EmpresByUserID_EmpresaID(int user_id, int empresa_id ){
	    	List<usuario_empresa> listado= empresa_UsuarioRepository.findAll();
	    		
	    	boolean respuesta =false;
	    	for(usuario_empresa lista : listado) {
	    		if(lista.getUser().getId()==user_id && lista.getEmpresa().getId()==empresa_id) {
	    			respuesta=true;
	    		}
	    	}
			return respuesta;
	    }
	    
	    public List<usuario_empresa> listado(){
	    	return empresa_UsuarioRepository.findAll();
	    }
	    
	    
	    
	    public boolean save(usuario_empresa empr)
	    { boolean rta=false;
	    try {
	    	if(empr!=null) {
	    		
	    	
	    	empresa_UsuarioRepository.save(empr);
	    	rta=true;
	    	
	    	}
		} catch (Exception e) {
			// TODO: handle exception
		}
	    return rta;
	    }
	    
	
}
