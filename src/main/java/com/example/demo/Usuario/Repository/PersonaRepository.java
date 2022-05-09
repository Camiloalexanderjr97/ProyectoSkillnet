package com.example.demo.Usuario.Repository;

import com.example.demo.Usuario.Entity.Persona;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("personaRepository")
public interface PersonaRepository extends JpaRepository<Persona, Long> {


    Persona findByNombre(String nombre);
    
    

    
}
