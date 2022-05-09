package com.example.demo.Usuario.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Usuario.Entity.Rol;
import com.example.demo.Usuario.Entity.Empresa;
import com.example.demo.Usuario.Login.RolNombre;

@Repository("empresaRepository")
public interface empresaRepository extends JpaRepository<Empresa, Long> {

	Empresa findByNombre(String nombre);
}
