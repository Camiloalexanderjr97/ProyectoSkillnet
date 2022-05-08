package com.example.demo.Controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Usuario.Entity.Rol;
import com.example.demo.Usuario.Entity.Usuario;
import com.example.demo.Usuario.Entity.empresa;
import com.example.demo.Usuario.Login.RolNombre;
import com.example.demo.Usuario.Service.EmpresaService;
import com.example.demo.dto.EmpresaModel;
import com.example.demo.dto.Mensaje;




@CrossOrigin
@RestController
@RequestMapping("/auth/empresa")
public class EmpresaController {
	
	
	

	@Autowired
	private EmpresaService empresaServ;
	
	@RequestMapping(value = "/nuevo", method = RequestMethod.POST)
	public ResponseEntity<?> nuevaEmpresa(@Valid @RequestBody EmpresaModel empresa, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("Campos mal puestos o invalidos"), HttpStatus.BAD_REQUEST);
		if(empresaServ.getEmpresaByNombre(empresa.getNombre()))
			return new ResponseEntity(new Mensaje("Esa Empresa ya existe"), HttpStatus.BAD_REQUEST);
		
		empresa empre = new empresa(empresa.getNombre());
		
		empresaServ.save(empre);
				return new ResponseEntity(new Mensaje("Empresa Guardada"), HttpStatus.CREATED);

	}
	
	
	

	
	
	
	

}
