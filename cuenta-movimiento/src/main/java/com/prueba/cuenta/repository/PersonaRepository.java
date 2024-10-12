package com.prueba.cuenta.repository;


import com.prueba.cuenta.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    Optional<Persona> findByIdentificacion(String identificacion);
}
