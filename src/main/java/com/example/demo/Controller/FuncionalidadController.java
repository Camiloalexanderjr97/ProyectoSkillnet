package com.example.demo.Controller;

import java.util.HashSet;
import java.util.List;
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
import com.example.demo.Usuario.Entity.Empresa;
import com.example.demo.Usuario.Entity.Empresa_Funcionalidad;
import com.example.demo.Usuario.Entity.Funcionalidad;
import com.example.demo.Usuario.Login.RolNombre;
import com.example.demo.Usuario.Service.EmpresaService;
import com.example.demo.Usuario.Service.Empresa_FuncionalidadService;
import com.example.demo.Usuario.Service.FuncionalidadService;
import com.example.demo.dto.EmpresaModel;
import com.example.demo.dto.FuncionalidadModel;
import com.example.demo.dto.Funcionalidad_EmpresaModel;
import com.example.demo.dto.Mensaje;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;

@CrossOrigin
@RestController
@RequestMapping("/auth/funcionalidad")
public class FuncionalidadController {

	@Autowired
	private Empresa_FuncionalidadService empresa_FuncionalidadService;

	@Autowired
	private FuncionalidadService funcionalidadService;

	@Autowired
	private EmpresaService empresaService;

	public static Log LOG = LogFactory.getLog(FuncionalidadController.class);

	@RequestMapping(value = "/nuevo", method = RequestMethod.POST)
	public ResponseEntity<?> nuevaEmpresa(@Valid @RequestBody FuncionalidadModel funcionalidad,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("Campos mal puestos o invalidos"), HttpStatus.BAD_REQUEST);
		if (funcionalidadService.getFuncionalidadByNombre(funcionalidad.getNombre()))
			return new ResponseEntity(new Mensaje("Esa Funcionalidad ya existe"), HttpStatus.BAD_REQUEST);

		Funcionalidad fun = new Funcionalidad(funcionalidad.getNombre(), funcionalidad.getDescripcion());

		funcionalidadService.save(fun);
		return new ResponseEntity(new Mensaje("Funcionalidad Guardada"), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/FunEmpresa", method = RequestMethod.POST)
	public ResponseEntity<List<Funcionalidad>> FunEmpresa(@Valid @RequestBody Funcionalidad_EmpresaModel fun,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("Campos mal puestos o invalidos"), HttpStatus.BAD_REQUEST);
		List<Funcionalidad> listado = empresa_FuncionalidadService
				.getEmpresa_FuncionalidadByIDEmpresa(fun.getEmpresa_Id());

		try {
			if (listado.size() == 0) {
				return new ResponseEntity(new Mensaje("Listado Vacio, Posiblemente no se encontró algun dato"),
						HttpStatus.BAD_REQUEST);

			} else {

				return new ResponseEntity<>(listado, HttpStatus.OK);
			}
		} catch (HibernateException e) {
			LOG.info(" Error : " + e.getMessage());

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/nuevo/FuncionalidadEmpresa", method = RequestMethod.POST)
	public ResponseEntity<?> nuevoEmpresaFuncionalidad(@Valid @RequestBody Funcionalidad_EmpresaModel fun_empre,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("Campos mal puestos o invalidos"), HttpStatus.BAD_REQUEST);
		if (empresa_FuncionalidadService.getFuncionalidad_Empresa(fun_empre.getFuncionalidad_id(),
				fun_empre.getEmpresa_Id()))
			return new ResponseEntity(new Mensaje("Esa Relación ya Existe"), HttpStatus.BAD_REQUEST);

		Empresa empr = empresaService.buscarEmpresaById(fun_empre.getEmpresa_Id());
		Funcionalidad fun = funcionalidadService.buscarFuncionalidadById(fun_empre.getFuncionalidad_id());
		Empresa_Funcionalidad empr_fun = new Empresa_Funcionalidad();

		if (empr == null && fun == null)
			return new ResponseEntity(
					new Mensaje("Puede que no exista informacion de esa Funcionalidad con esa empresa"),
					HttpStatus.BAD_REQUEST);

		empr_fun.setFuncionalidad(fun);
		empr_fun.setEmpresa(empr);
		empresa_FuncionalidadService.save(empr_fun);
		return new ResponseEntity(new Mensaje("Relacion Creada Exitosamente"), HttpStatus.CREATED);
	}

}
