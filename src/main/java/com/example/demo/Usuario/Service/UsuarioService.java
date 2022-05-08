package com.example.demo.Usuario.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Usuario.Entity.Usuario;
import com.example.demo.Usuario.Repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    @Qualifier("usuarioRepository")
    private UsuarioRepository usuarioRepository;

    // public  UsuarioModel getUsusarioUsername(String username);

	public List<Usuario> getUsuarios() {
	        List<Usuario> listadoUser = usuarioRepository.findAll();
	        List<Usuario> lista = new ArrayList<>();

	      

	        return listadoUser;
	    }

	    public boolean deleteUsuario(Long id) {
	        boolean resultado =false;
	        try {
	                 usuarioRepository.deleteById(id);  
	                 resultado=true;
	        } catch (Exception e) {
	            //TODO: handle exception
	        }
	         return resultado;
	    }

    public  Usuario crearUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

    public  boolean loadUserByUsername(String username) {
		System.out.println("-----------------------------Entra");

    	boolean resultado=false;
    	List<Usuario> user =  	usuarioRepository.findAll();
    	for(Usuario lista : user) {
    		if(lista.getUsername().equalsIgnoreCase(username)) {
    			resultado=true;
    		}
    	}
		return resultado;
	}

	public boolean existByUsername(String nombre) {
		return usuarioRepository.existsByUsername(nombre);
	}
	
	public void save(Usuario user) {
		usuarioRepository.save(user);
	}
	
	public Usuario buscarUserById(long id) {
	
		
		return usuarioRepository.findById(id).get();
	}

    
}
