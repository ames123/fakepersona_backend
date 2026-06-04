package uj.mechaniki.fakepersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uj.mechaniki.fakepersona.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona,Long> {
}
