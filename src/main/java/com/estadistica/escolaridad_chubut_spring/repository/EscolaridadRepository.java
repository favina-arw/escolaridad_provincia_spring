package com.estadistica.escolaridad_chubut_spring.repository;

import com.estadistica.escolaridad_chubut_spring.entity.Escolaridad;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EscolaridadRepository extends JpaRepository<Escolaridad, Long> {
}
