 package com.example.demo.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.example.demo.Usuario.Entity.Rol;
import com.example.demo.Usuario.Entity.Usuario;
import com.example.demo.Usuario.Entity.Empresa;
import com.example.demo.Usuario.Entity.Persona;
import com.example.demo.Usuario.Entity.Usuario_empresa;
import com.example.demo.Usuario.Jwt.JwtProvider;
import com.example.demo.Usuario.Login.RolNombre;
import com.example.demo.Usuario.Service.EmpresaService;
import com.example.demo.Usuario.Service.Empresa_UsuarioService;
import com.example.demo.Usuario.Service.PersonaService;
import com.example.demo.Usuario.Service.RolService;
import com.example.demo.Usuario.Service.UsuarioService;
import com.example.demo.dto.EmpresaModel;
import com.example.demo.dto.Empresa_UsuarioModel;
import com.example.demo.dto.JwtDto;
import com.example.demo.dto.Mensaje;
import com.example.demo.dto.PersonaModel;
import com.example.demo.dto.UsuarioModel;
import com.example.demo.dto.loginUsuario;
import com.google.gson.Gson;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/auth/persona")
public class PersonaController {
	
	


	
	
	@Autowired
	JwtProvider jwtProvider ;
	

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PersonaService personaService;
	

	public static Log LOG = LogFactory.getLog(UsuarioController.class);
	public static Gson gson = new Gson();
	
	Usuario u = new Usuario();

	
	@RequestMapping(value = "/nuevo", method = RequestMethod.POST)
	public ResponseEntity<?> nuevo(@Valid @RequestBody PersonaModel per, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("Campos mal puestos o invalidos"), HttpStatus.BAD_REQUEST);
		if(personaService.loadByDocumento(per.getDocumento())!=null)
			return new ResponseEntity(new Mensaje("Esa Persona ya existe"), HttpStatus.BAD_REQUEST);
		
		
		Usuario user =	usuarioService.buscarUserById(per.getCreador());
		Persona persona = new Persona(per.getName(),per.getDocumento(), per.getTipoDocumento(), new Date(), user);
		
		
		personaService.crearPersona(persona);
	    return new ResponseEntity(new Mensaje("Persona Guardada"), HttpStatus.CREATED);

		
	}
	
	

	 
	@RequestMapping(value = "/list/persona", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Persona>> getAllPersonas() {
		List<Persona> listado = null;
		try {
			listado = personaService.getPersonas();
			return new ResponseEntity<>(listado, HttpStatus.OK);
		} catch (HibernateException e) {
			LOG.info(" Error : " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Persona> editarPersona(@RequestBody PersonaModel per) {
		HashMap<String, String> msg = new HashMap<>();
	
		try {
			Persona persona = personaService.loadByDocumento(per.getDocumento());
			if(persona!=null){
	
				persona.setDocumento(per.getDocumento());
				persona.setName(per.getName());
				persona.setTipoDocumento(per.getTipoDocumento());
			}else{
				return new ResponseEntity(new Mensaje("Persona No encontrada"), HttpStatus.CREATED);
	
			}
	
		
		personaService.editUsuario(persona);
			return new ResponseEntity<>(persona, HttpStatus.OK);


		} catch (HibernateException e) {
			LOG.error("Error: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}






	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteUsuario(@PathVariable long id) {
		boolean resultado =false;
        try {
			resultado=personaService.deletePersona(id);
			
			
        } catch (HibernateException e) {
            LOG.error(" Error : " + e.getMessage());
        }
        return resultado;
	}


}
