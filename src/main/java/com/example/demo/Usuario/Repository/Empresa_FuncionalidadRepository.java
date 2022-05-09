package com.example.demo.Usuario.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Usuario.Entity.Rol;
import com.example.demo.Usuario.Entity.Empresa;
import com.example.demo.Usuario.Entity.Empresa_Funcionalidad;
import com.example.demo.Usuario.Entity.Usuario_empresa;
import com.example.demo.Usuario.Login.RolNombre;

@Repository("empresa_FuncionalidadRepository")
public interface Empresa_FuncionalidadRepository extends JpaRepository<Empresa_Funcionalidad, Long> {

}
