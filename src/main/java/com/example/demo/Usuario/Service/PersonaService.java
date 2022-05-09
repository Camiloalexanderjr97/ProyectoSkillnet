package com.example.demo.Usuario.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Usuario.Entity.Persona;
import com.example.demo.Usuario.Entity.Usuario;
import com.example.demo.Usuario.Repository.PersonaRepository;
import com.example.demo.Usuario.Repository.UsuarioRepository;

@Service
@Transactional
public class PersonaService {

    @Autowired
    @Qualifier("personaRepository")
    private PersonaRepository personaRepository;

    // public  UsuarioModel getUsusarioUsername(String username);

	public List<Persona> getPersonas() {
	        List<Persona> listadoPersona = personaRepository.findAll();

	      

	        return listadoPersona;
	    }

	    public boolean deletePersona(String docum) {
	        boolean resultado =false;
	        try {
				List<Persona> listado = personaRepository.findAll();

				for(Persona per: listado){
					if(per.getDocumento().equalsIgnoreCase(docum)){
						personaRepository.deleteById(per.getId());  
						resultado=true;

					}
				}
	        } catch (Exception e) {
	            //TODO: handle exception
	        }
	         return resultado;
	    }

		public  Persona crearPersona(Persona per) {
			return personaRepository.save(per);
		}

		public  Persona editUsuario(Persona per) {
			return personaRepository.save(per);
		}
		

		public  Persona loadByDocumento(String documento) {
	        List<Persona> listadoPersona = personaRepository.findAll();
			Persona per =null;
			for(Persona lista: listadoPersona){
				if(lista.getDocumento().equalsIgnoreCase(documento)){
					per=lista;
				}
			}
			
			return per;
		}
		public  Persona loadByNombre(String nombre) {
	
			
			return personaRepository.findByNombre(nombre);
		}
	public  Persona loadByIdPersona(long id) {
		
		return personaRepository.findById(id).get();
	}

    
}
