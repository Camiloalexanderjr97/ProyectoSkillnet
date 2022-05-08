 package com.example.demo.Controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.example.demo.Usuario.Entity.Rol;
import com.example.demo.Usuario.Entity.Usuario;
import com.example.demo.Usuario.Entity.empresa;
import com.example.demo.Usuario.Entity.usuario_empresa;
import com.example.demo.Usuario.Jwt.JwtProvider;
import com.example.demo.Usuario.Login.RolNombre;
import com.example.demo.Usuario.Service.EmpresaService;
import com.example.demo.Usuario.Service.Empresa_UsuarioService;
import com.example.demo.Usuario.Service.RolService;
import com.example.demo.Usuario.Service.UsuarioService;
import com.example.demo.dto.EmpresaModel;
import com.example.demo.dto.Empresa_UsuarioModel;
import com.example.demo.dto.JwtDto;
import com.example.demo.dto.Mensaje;
import com.example.demo.dto.NuevoUsuario;
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
@RequestMapping("/auth")
public class UsuarioController {
	
	


	@Autowired
	private EmpresaService empresaServ;
	
	@Autowired
	private Empresa_UsuarioService empresa_userService;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	RolService rolService;
	
	
	@Autowired
	JwtProvider jwtProvider ;
	

	@Autowired
	private UsuarioService usuarioService;
	

	public static Log LOG = LogFactory.getLog(UsuarioController.class);
	public static Gson gson = new Gson();
	
	Usuario u = new Usuario();

	
	@RequestMapping(value = "/nuevo", method = RequestMethod.POST)
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("Campos mal puestos o invalidos"), HttpStatus.BAD_REQUEST);
		if(usuarioService.loadUserByUsername(nuevoUsuario.getUsername()))
			return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		
		Usuario user = new Usuario(nuevoUsuario.getName(), nuevoUsuario.getUsername(), passwordEncoder.encode(nuevoUsuario.getPassword()));
		
		Set<Rol> roles = new HashSet<>();
		System.out.print(("---------------"+nuevoUsuario.getRol()));
		
		roles.add(rolService.getRolByName(RolNombre.ROLE_USER).get());
		if(nuevoUsuario.getRol().equalsIgnoreCase("admin")) 
			roles.add(rolService.getRolByName(RolNombre.ROLE_ADMIN).get());
			user.setRoles( roles);
			
		usuarioService.crearUsuario(user);
	    return new ResponseEntity(new Mensaje("Usuario Guardado"), HttpStatus.CREATED);

		
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<JwtDto> login(@Valid @RequestBody loginUsuario login, BindingResult binding){
		if(binding.hasErrors())
			return new ResponseEntity(new Mensaje("Campos mal puestos o invalidos"), HttpStatus.BAD_REQUEST);
		Authentication auth =
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(),login.getPassword()));
SecurityContextHolder.getContext().setAuthentication(auth);
String jwt = jwtProvider.generateToken(auth);
		UserDetails userDe = (UserDetails) auth.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDe.getUsername(), userDe.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Usuario>> getAllReHospitalario() {
		List<Usuario> listaReHoReturn = null;
		try {
			listaReHoReturn = usuarioService.getUsuarios();
			return new ResponseEntity<>(listaReHoReturn, HttpStatus.OK);
		} catch (HibernateException e) {
			LOG.info(" Error : " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario user) {
		HashMap<String, String> msg = new HashMap<>();
		Usuario usuario = null;
		LOG.info("ESTA ENTRANDO AL CONTROLLER DE RESIDUOS PROFESIONALES");
		try {
			
			usuario = usuarioService.crearUsuario(user);
			
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (HibernateException e) {
			LOG.error("Error: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/users/buscar/{username}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Optional<Usuario>> addUsuario(@PathVariable String username) {
		HashMap<String, String> msg = new HashMap<>();
		Optional<Usuario> usuario = null;
		LOG.info("ESTA ENTRANDO AL CONTROLLER DE RESIDUOS PROFESIONALES");
		try {
			
			usuario =  null;
			
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (HibernateException e) {
			LOG.error("Error: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/users/eliminar/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteUsuario(@PathVariable long id) {
		boolean resultado =false;
        try {
			resultado=usuarioService.deleteUsuario(id);
			
			
        } catch (HibernateException e) {
            LOG.error(" Error : " + e.getMessage());
        }
        return resultado;
	}

	@RequestMapping(value = "/empresa/user", method = RequestMethod.POST)
	public ResponseEntity<List<String>> nuevaEmpresa(@Valid @RequestBody Empresa_UsuarioModel empresa, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("Campos mal puestos o invalidos"), HttpStatus.BAD_REQUEST);
			List<String> listado =empresa_userService.getUsuario_EmpresByUserID(empresa.getUsuario_Id());
			
				try {
					if(listado.size()==0) {
						return new ResponseEntity(new Mensaje("Listado Vacio, Posiblemente no se encontró algun dato"), HttpStatus.BAD_REQUEST);
				
					}else {
						
				return new ResponseEntity<>(listado, HttpStatus.OK);
					}
			} catch (HibernateException e) {
				LOG.info(" Error : " + e.getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
				@RequestMapping(value = "/nuevo/EmpresaUser", method = RequestMethod.POST)
				public ResponseEntity<?> nuevoEmpresaUser(@Valid @RequestBody Empresa_UsuarioModel empre_userModel, BindingResult bindingResult){
					if(bindingResult.hasErrors())
						return new ResponseEntity(new Mensaje("Campos mal puestos o invalidos"), HttpStatus.BAD_REQUEST);
					if(empresa_userService.getUsuario_EmpresByUserID_EmpresaID(empre_userModel.getUsuario_Id(),empre_userModel.getEmpresa_Id() ))
						return new ResponseEntity(new Mensaje("Esa Relación ya Existe"), HttpStatus.BAD_REQUEST);
					

					empresa empr=empresaServ.buscarEmpresaById(empre_userModel.getEmpresa_Id());
					Usuario user =usuarioService.buscarUserById(empre_userModel.getUsuario_Id());
					usuario_empresa user_empr = new usuario_empresa();
					
						if(empr==null && user==null) 
							return new ResponseEntity(new Mensaje("Puede que no exista informacion de ese usuario con esa empresa"), HttpStatus.BAD_REQUEST);

						
						
						
						
						
						
							user_empr.setUser(user);
							user_empr.setEmpresa(empr);
							empresa_userService.save(user_empr);
						return	new ResponseEntity(new Mensaje("Relacion Creada Exitosamente"), HttpStatus.CREATED);
				}	
					
					
						
	






	@RequestMapping(value ="prueba")
	public String prueba(){

		return "asdasdasd";
	}

}
